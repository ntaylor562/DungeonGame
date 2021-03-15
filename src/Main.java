import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * Used to run the game
 * @author Nathan Taylor
 */
class Main {

  /**
   * Gives the user the option to purchase items or to sell items from their inventory
   * @param h The player object
   */
  public static void store(Hero h) {
    System.out.println("You have encountered a store. Would you like to go in? (Y/N)");
    Scanner input = new Scanner(System.in);
    boolean exit = false;
    String userChoice = input.next().toLowerCase();
    while(true) {
      if(!userChoice.equals("y") && !userChoice.equals("n")) {
        System.out.println("Input invalid");
        userChoice = input.next().toLowerCase();
        continue;
      }
      break;
    }
    
    if(userChoice.equals("y")) { //Enter store
      System.out.println("Welcome to the item store!\nHere you can buy something or sell anything you've got.");

      while(!exit) { //Player is in the store until they choose to exit

        System.out.println("You have " + h.getGold() + " gold. What will it be?\n1. Purchase\n2. Sell\n3. Exit");

        int choice = 0;
        while (choice < 1 || choice > 3) { //Input checking
          while(!input.hasNextInt()) {
            input.next();
            System.out.println("Input Invalid");
          }
          choice = input.nextInt();
          if(choice < 1 || choice > 3) {
            System.out.println("Input Invalid");
          }
        }
        System.out.println();

        switch(choice) {
          case 1: //Buying something
            if(h.getNumItems() >= 5) { //Inventory full. Cannot buy items
              System.out.println("Whoa there! It looks like your pockets are full.\nGo ahead and sell something if you want to make room for one of my items.");
              break;
            }

            if(h.getGold() <= 0) { //Player is broke
              System.out.println("Looks like you don't have any gold on you. Come back when you get some gold.");
              break;
            }

            System.out.printf("Check out what I've got:\n1. Health Potion\t\t(%d gold)\n2. Key\t\t\t\t\t(%d gold)\n", ItemGenerator.getInstance().getPotion().getValue(), ItemGenerator.getInstance().getKey().getValue());

            choice = 0;
            while (choice < 1 || choice > 2) { //Input checking
              while(!input.hasNextInt()) {
                input.next();
                System.out.println("Input Invalid");
              }
              choice = input.nextInt();
              if(choice < 1 || choice > 2) {
                System.out.println("Input Invalid");
              }
            }
            System.out.println();

            int itemValue;
            if(choice == 1) { //Buying a Health Potion
              itemValue = ItemGenerator.getInstance().getPotion().getValue();
              if(h.getGold() < itemValue) {
                System.out.println("Sorry! You don't have enough gold to purchase this item.");
                break;
              }
              h.spendGold(itemValue);
              System.out.println(h.getName() + " spent " + itemValue + " gold.\n");
              h.pickUpItem(ItemGenerator.getInstance().getPotion());

            } else if(choice == 2) { //Buying a Key
              itemValue = ItemGenerator.getInstance().getKey().getValue();
              if(h.getGold() < itemValue) {
                System.out.println("Sorry! You don't have enough gold to purchase this item.");
                break;
              }
              h.spendGold(itemValue);
              System.out.println(h.getName() + " spent " + itemValue + " gold.\n");
              h.pickUpItem(ItemGenerator.getInstance().getKey());

            }

            break;

          case 2: //Selling something
            if(h.getNumItems() <= 0) { //Empty inventory
              System.out.println("Sorry! It looks like you have nothing to sell. Come back when you've collected something.");
              break;
            }

            System.out.println("What would you like to sell?\n" + h.itemsToString());

            choice = 0;
            while (choice < 1 || choice > h.getNumItems()) { //Input checking
              while(!input.hasNextInt()) {
                input.next();
                System.out.println("Input Invalid");
              }
              choice = input.nextInt();
              if(choice < 1 || choice > h.getNumItems()) {
                System.out.println("Input Invalid");
              }
            }
            System.out.println();

            Item droppedItem = h.dropItem(choice - 1);
            h.collectGold(droppedItem.getValue());
            System.out.println(h.getName() + " sold their " + droppedItem.getName() + " for " + Integer.toString(droppedItem.getValue()) + " gold.\n");

            break;

          case 3: //Exit store
            exit = true;
            break;
        }
      }

    } else if(userChoice.equals("n")) { //Didn't enter store
      return;
    }
  }

  /**
   * Called whenever the player enters a room with a monster
   * Continuously gives the player the option of attacking, running away, or if the player has a health potion, drinking the potion, until the player dies, defeats the enemy, or runs away
   * @param h The player object
   * @param m The level map
   * @param level The level the player is option
   * @return True if the player survives and false if the player is defeated
   */
  public static boolean monsterRoom(Hero h, Map m, int level) {
    Scanner input = new Scanner(System.in); //Do not close or it breaks because closing it also closes the scanner in main
    Enemy enemy = EnemyGenerator.getInstance().generateEnemy(level);
    System.out.println("You've encountered a " + enemy.getName() + "\n");
    boolean fighting = true;
    while(fighting) {
      System.out.println(h);
      System.out.println(enemy);
      System.out.println();
      System.out.println("1. Fight\n2. Run Away");
      if(h.hasPotion()) System.out.println("3. Drink Health Potion");
        
      int userChoice = 0;
      while (userChoice < 1 || userChoice > 3 || (!h.hasPotion() && userChoice == 3)) {
        while(!input.hasNextInt()) {
          input.next();
          System.out.println("Input Invalid");
        }
        userChoice = input.nextInt();
        if(userChoice < 1 || userChoice > 3 || (!h.hasPotion() && userChoice == 3)) {
          System.out.println("Input Invalid");
        }
      }
      System.out.println();

      switch(userChoice) {
        case 1: //Player chooses to fight enemy
          boolean fightWon = fight(h, enemy);
          if(fightWon || h.getHP() <= 0) { //Player won the battle or player dies
            fighting = false;
            if(fightWon) {
              m.removeCharAtLoc(h.getLocation()); //Remove enemy from map
            }
          }
          break;

        case 2: //Run away to random location
          int randomPosition = 0;
          boolean validPosition = false;
          while(!validPosition) {
            randomPosition = (int)(Math.random() * 4 + 1);
            switch(randomPosition) {
              case 1:
                if(h.getLocation().getX() == 4) {
                  continue;
                }
                break;
              case 2:
                if(h.getLocation().getX() == 0) {
                  continue;
                }
                break;
              case 3:
                if(h.getLocation().getY() == 4) {
                  continue;
                }
                break;
              case 4:
                if(h.getLocation().getY() == 0) {
                  continue;
                }
                break;
            }
            validPosition = true;
          }
          switch (randomPosition) { //Moving player to the new position
            case 1:
              h.goEast();
              break;
            case 2:
              h.goWest();
              break;
            case 3:
              h.goNorth();
              break;
            case 4:
              h.goSouth();
              break;
          }
          fighting = false;
          System.out.println("You ran away safely");
          break;
        case 3:
          if(h.hasPotion()) {
            h.drinkPotion();
            System.out.println("You've been healed!\n");
          } else {
            System.out.println("Input Invalid");
          }
          break;
        default:
          System.out.println("Input Invalid");
          break;
      }
    }

    if(h.getHP() <= 0) return false; //Player is dead
    return true; //Player survives    
  }

  /**
   * Plays out one turn of the fight
   * Initiates the player's attack method then, if the enemy is not defeated, the enemy retaliates with their attack method
   * If the enemy is defeated by player's attack, the enemy drops their item for the player to pick up
   * If the player's HP drops to zero, the game ends
   * @param h The player object
   * @param e The enemy object the player is fighting
   * @return True if the player defeats the enemy, false otherwise
   */
  public static boolean fight(Hero h, Enemy e) {

    Scanner input = new Scanner(System.in);

    System.out.println("1. Physical Attack\n2. Magical Attack");
    
    int userChoice = 0;
    while (userChoice < 1 || userChoice > 2) { //Input checking
      while(!input.hasNextInt()) {
        input.next();
        System.out.println("Input Invalid");
      }
      userChoice = input.nextInt();
      if(userChoice < 1 || userChoice > 2) {
        System.out.println("Input Invalid");
      }
    }
    System.out.println();

    String output = "";

    if(userChoice == 1) { //Physical attack
      output += h.attack(e);
    } else { //Magical Attack
      System.out.println(Magical.MAGIC_MENU);
      
      userChoice = 0;
      while (userChoice < 1 || userChoice > 3) { //Input checking
        while(!input.hasNextInt()) {
          input.next();
          System.out.println("Input Invalid");
        }
        userChoice = input.nextInt();
        if(userChoice < 1 || userChoice > 3) {
          System.out.println("Input Invalid");
        }
      }
      System.out.println();

      switch(userChoice) {
        case 1:
          output += h.magicMissile(e);
          break;
        case 2:
          output += h.fireball(e);
          break;
        case 3:
          output += h.thunderClap(e);
          break;
      }

    }
    System.out.println(output);
    System.out.println();

    if(e.getHP() <= 0) { //Player defeats enemy
      System.out.println("You defeated the " + e.getName() + "!");
      System.out.println("You received " + e.getItem().getName() + " from its corpse.\n");
      if(!h.pickUpItem(e.getItem())) { //Inventory is full, cannot pick up item. Giving option to replace item in inventory or drop the item
        System.out.println("Your inventory is full. You cannot pick up " + e.getItem().getName());
        System.out.println("1. Replace an item in your inventory\n2. Drop " + e.getItem().getName());

        userChoice = 0;
        while (userChoice < 1 || userChoice > 2) { //Input checking
          while(!input.hasNextInt()) {
            input.next();
            System.out.println("Input Invalid");
          }
          userChoice = input.nextInt();
          if(userChoice < 1 || userChoice > 2) {
            System.out.println("Input Invalid");
          }
        }
        System.out.println();

        switch(userChoice) {
          case 1: //Replacing an item from inventory
            System.out.println(h.itemsToString());
            System.out.println("Which item would you like to replace? ");
            
            userChoice = 0;
            while (userChoice < 1 || userChoice > h.getNumItems()) { //Input checking
              while(!input.hasNextInt()) {
                input.next();
                System.out.println("Input Invalid");
              }
              userChoice = input.nextInt();
              if(userChoice < 1 || userChoice > h.getNumItems()) {
                System.out.println("Input Invalid");
              }
            }
            System.out.println();

            System.out.println(h.getName() + " dropped their " + h.dropItem(userChoice - 1).getName() + "\n");
            h.pickUpItem(e.getItem());
            break;
          case 2:
            System.out.println(e.getItem().getName() + " has been dropped.");
        }

      }
      return true; //Player defeated enemy

    } else { //Player did not defeat the enemy yet so the enemy attacks
      if(h.hasArmorItem() != -1) {
        System.out.println(h.getName() + " blocked " + e.getName() + "\'s attack using their " + h.dropItem(h.hasArmorItem()).getName());
      } else
        System.out.println(e.attack(h));
      System.out.println();
    }
    return false;
  }

  /**
   * Called when the player encounters a room containing an item
   * Generates a random item that the player picks up if their inventory is not full
   * If the player decides not to pick up the item, the item is left on the map. If the player picks up the item, the item is removed from the map
   * @param h The player object picking up the item in the room
   * @param m The player's map passed in so that we can remove the item from the map if needed
   */
  public static void itemRoom(Hero h, Map m){
    Scanner input = new Scanner(System.in);
    int userChoice;
    Item roomItem = ItemGenerator.getInstance().generateItem();
    System.out.println("You found " + roomItem.getName() + "!");
    if(!h.pickUpItem(roomItem)) { //Inventory is full, cannot pick up item. Giving option to replace item in inventory or drop the item
      System.out.println("Your inventory is full. You cannot pick up " + roomItem.getName());
      System.out.println("1. Replace an item in your inventory\n2. Drop " + roomItem.getName());

      userChoice = 0;
      while (userChoice < 1 || userChoice > 2) { //Input checking
        while(!input.hasNextInt()) {
          input.next();
          System.out.println("Input Invalid");
        }
        userChoice = input.nextInt();
        if(userChoice < 1 || userChoice > 2) {
          System.out.println("Input Invalid");
        }
      }
      System.out.println();

      switch(userChoice) {
        case 1:
          System.out.println(h.itemsToString());
          System.out.println("Which item would you like to replace? ");
          
          userChoice = 0;
          while (userChoice < 1 || userChoice > h.getNumItems()) { //Input checking
            while(!input.hasNextInt()) {
              input.next();
              System.out.println("Input Invalid");
            }
            userChoice = input.nextInt();
            if(userChoice < 1 || userChoice > h.getNumItems()) {
              System.out.println("Input Invalid");
            }
          }
          System.out.println();

          System.out.println(h.getName() + " dropped their " + h.dropItem(userChoice - 1).getName() + "\n");
          h.pickUpItem(roomItem);
          m.removeCharAtLoc(h.getLocation());
          break;
        case 2:
          System.out.println(roomItem.getName() + " has been dropped.");
      }
    } else {
        m.removeCharAtLoc(h.getLocation());
    }
  }



  /**
   * Running the game, allowing the player to move around the map and interact with whatever the player encounters (item on the ground to pick up, an enemy to fight, etc.)
   * @param args Arguments unused
   */
  public static void main(String[] args ) {
    Scanner s = new Scanner(System.in);
    Map m = Map.getInstance();
    ItemGenerator ig = ItemGenerator.getInstance();
    EnemyGenerator eg = EnemyGenerator.getInstance();

    System.out.print("What is your name, traveler? ");
    String name = s.next();
    Hero h = new Hero(name, m);
    System.out.println();

    int level = 1;
    char mapChar = m.getCharAtLoc(h.getLocation()); //The char occupying the position the player is at (or 'd' for ending the game)

    while(mapChar != 'd') {
      System.out.println(h);
      m.displayMap(h.getLocation());
      System.out.println();
      System.out.println("Choose A Direction\n1. North\n2. South\n3. East\n4. West\n5. Quit");

      int ans = 0;
      while (ans < 1 || ans > 5) {
        while(!s.hasNextInt()) {
          s.next();
          System.out.println("Input Invalid");
        }
        ans = s.nextInt();
        if(ans < 1 || ans > 5) {
          System.out.println("Input Invalid");
        }
      }
      System.out.println();
  
      switch(ans){
        case 1: 
          mapChar = h.goNorth();
          break;

        case 2:
          mapChar = h.goSouth();
          break;

        case 3:
          mapChar = h.goEast();
          break;

        case 4:
          mapChar = h.goWest();
          break;

        case 5:
          mapChar = 'd';
          break;
        default:
          System.out.println("Input Invalid");
          break;
      }
        
      switch(mapChar){
        case 'm': //Player enters a monster room
          if(!monsterRoom(h, m, level)) { //If the player was defeated
            mapChar = 'd';
            System.out.print("You have been defeated. ");
          }
          break;

        case 'i': //Player enters an item room
          itemRoom(h, m);
          break;

        case 'n': //Player moved to an empty space
          System.out.println("There is nothing here.");
          break;

        case 'f': //Player found the exit
          if(h.hasKey()) {
            h.useKey();
            System.out.println("Congratulations, you have found the exit! You're now on level " + Integer.toString(++level));
            h.heal(10000); //Heal player to full health
            m.loadMap(level - 3 * ((level - 1) / 3)); //Makes the level being played be looped through the different maps (3)
            store(h);
          } else {
            System.out.println("Looks like the exit is locked. You will need a Key to go through.");
          }
          break;

        case 's': //Player found the start position
          System.out.println("You're back at the start");
          store(h);
          break;
      }
      
      if(mapChar == 'd') { //If the player dies or they choose to quit
        System.out.println("Game Over");
        return;
      }


      //Doing this to make it easier to follow what's happening when playing the game
      System.out.println("\nPress Enter to continue...");
      s.nextLine();
      s.nextLine();

      System.out.println("----------------------------------\n");
    }
  }

}