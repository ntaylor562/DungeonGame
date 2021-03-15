import java.util.ArrayList;
import java.awt.Point;

/**
 * Represents the player which has the ability to move around the map and attack enemies with physical and magical attacks. The player can also pick up and drop items
 * The player has an inventory which can hold up to 5 items
 * When the player drinks a potion, the player is healed
 * @author Nathan Taylor
 */
public class Hero extends Entity implements Magical {

  /**
   * The amount of currency the player currently holds which can be used to purchase items from the store
   */
  private int gold;
 
  /**
   * Player's inventory
   */
  private ArrayList<Item> items;

  /**
   * Player's personal map that is revealed as the player moves
   */
  private Map map;

  /**
   * Point the player is at on the map
   */
  private Point location; 

  /**
   * Constructor that simply calls the parent "Entity" constructor then initializes the player's map
   * Gives the player a maxHp of 25
   * Player's inventory size is 5
   * @param n The name of this hero
   * @param m The map the player is on
   */
  public Hero(String n, Map m){
    super(n, 25);
    this.gold = 0;
    this.location = new Point((int)m.findStart().getX(), (int)m.findStart().getY());
    this.items = new ArrayList<Item>(5);
    this.map = m;
  }

  /**
   * Gives the current amount of gold the player has
   * @return Integer representing the amount of gold the player holds
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Adds a specified amount of gold to the player's gold
   * @param g The amount of gold to be added to player's gold
   */
  public void collectGold(int g) {
    this.gold += g;
  }

  /**
   * Subtracts a specified amount of gold from the player's gold
   * Precondition: Check that the amount being spent is not greater than the amount of gold the player has
   * @param g The amount of gold to be subtracted from player's gold
   */
  public void spendGold(int g) {
    this.gold -= g;
  }

  /**
   * Used to determine if the player is holding an armor item
   * @return The index of the first armor item the player is holding. Returns -1 if the player is not holding an armor item
   */
  public int hasArmorItem(){
    for(int i = 0; i < this.getNumItems(); ++i) {
      if(items.get(i).getType() == 'a') return i;
    }
    return -1;
  }

  /**
   * Used to determine if the hero has a key in their inventory
   * @return True if the player has a key in their inventory, false otherwise
   */
  public boolean hasKey() {
    for(Item x : this.items) {
      if(x.getName().equals("Key")) return true;
    }
    return false;
  }

  /**
   * Used to remove a key from the player's inventory
   */
  public void useKey() {
    for(int i = 0; i < this.items.size(); ++i) {
      if(this.items.get(i).getName().equals("Key")) {
        System.out.println(this.getName() + " has used their Key.");
        this.dropItem(i);
        return;
      }
    }

  }

  /**
   * Displays player's information such as name, hp, and inventory
   * @return a string that contains the Heros name, hp, max hp, and items on hand
   */
  public String toString() {
    return super.toString() + "\n" + this.itemsToString(); 
  }

  /**
   * Creates a list of items in the class' ArrayList "items"
   * @return a string representing all the items in the ArrayList
   */
  public String itemsToString(){
    String list = "Inventory:\n";
    int x = 1;
		for(Item i : items){ // goes through item arrayList
			list += (x + ". " + i.getName() + "\n");
			x++;
		}
    return list;
  }

  /**
   * Gets the number of items the player currently holds
   * @return The amount of items the hero is holding
   */
  public int getNumItems(){
    return items.size();
  }

  /**
   * The hero picks up an item
   * @param i Item to add to items arraylist
   * @return True if the item was added to the player's inventory, false otherwise
   */
  public boolean pickUpItem(Item i){
    if(this.getNumItems() < 5) { //If inventory is not full, add the item to inventory
      items.add(i);
      System.out.println(i.getName() + " has been added to your inventory.");
      return true;
    }
    return false;
  }

  /**
   * The hero drinks a health potion to heal 25 HP then drops the potion
   */
  public void drinkPotion(){    
     int index;
     for(index = 0; index < items.size(); ++index) {
       if(items.get(index).getName().equals("Health Potion")) {
         break;
       }
     }
     if(index < items.size()) {
      this.dropItem(index);
      System.out.println(this.getName() + " used their Health Potion\n");
      this.heal(25);
     }
  }

  /**
   * The item given by the index is removed from inventory and outputs a message saying who dropped what
   * @param i The inventory index of the item to be dropped
   * @return The item being dropped
   */
  public Item dropItem(int i){
    Item item = items.get(i);
    items.remove(i);
    return item;
  }

  /**
  * Checks if the hero has a health potion
  * @return True if the player has a health potion, false otherwise
  */
 public boolean hasPotion(){
    for(Item i : items) {
      if(i.getName().equals("Health Potion")){
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the location of the player on the map
   * @return A Point on map where hero is at
   */
  public Point getLocation(){
    return location;
  }

  /**
   * Moves the hero up on the map
   * @return char at location hero moved to
   */
  public char goNorth() {
    if((int)this.location.getY() + 1 > 4) {
      System.out.println("You've hit a wall");
      return map.getCharAtLoc(location);
    }
    location.setLocation((int)location.getX(), (int)location.getY() + 1);
    map.reveal(this.getLocation());
    return map.getCharAtLoc(location);
  }

  /**
   * Moves the hero down on the map
   * @return char at location hero moved to
   */
  public char goSouth() {
    if((int)location.getY() - 1 < 0) {
      System.out.println("You've hit a wall");
      return map.getCharAtLoc(location);
    }
    location.setLocation((int)location.getX(),(int)location.getY() - 1);
    map.reveal(this.getLocation());
    return map.getCharAtLoc(location);
  }

  /**
   * Moves the hero right on the map
   * @return char at location hero moved to
   */
  public char goEast() {
    if((int)location.getX() + 1 > 4) {
      System.out.println("You've hit a wall");
      return map.getCharAtLoc(location);
    }
    location.setLocation((int)location.getX() + 1,(int)location.getY());
    map.reveal(this.getLocation());
    return map.getCharAtLoc(location);
  }
   /**
    * Moves the hero left on the map
    * @return char at location hero moved to
    */
  public char goWest() {
    if((int)location.getX() - 1 < 0) {
      System.out.println("You've hit a wall");
      return map.getCharAtLoc(location);
    }
    location.setLocation((int)location.getX() - 1,(int)location.getY());
    map.reveal(this.getLocation());
    return map.getCharAtLoc(location);
  }

  /**
   * Physical attack to deal damage to entity passed as parameter
   * There is a random number from 0 to 3 added to the amount of damage dealt
   * damage = 3 + rand(0, 3) where rand(0, 3) is a random number from 0 to 3, inclusive
   * @param e The entity taking damage in the attack
   * @return String detailing the name of attacker, name of entity attacked, and the amount of damage
   */
  public String attack(Entity e) {
    int damage = 3; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    String verb = ""; //Randomized word to add diversity to physical attacks
    e.takeDamage(damage);
    switch((int)(Math.random() * 7)) {
      case 0:
        verb = "attacks";
        break;
      case 1:
        verb = "slashes";
        break;
      case 2:
        verb = "stabs";
        break;
      case 3:
        verb = "punches";
        break;
      case 4:
        verb = "kicks";
        break;
      case 5:
        verb = "slaps";
        break;
      case 6:
        verb = "whacks";
        break;
    }
    return this.getName() + " " + verb + " " + e.getName() + " for " + damage + " damage";
  }

  /**
   * Magical attack type called Magic Missile that has deals to an entity a random amount of damage between 3 and 6, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Magic Missile), amount of damage, and entity being attacked
   */
  @Override
  public String magicMissile(Entity e) {
    int damage = 3; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " hits " + e.getName() + " with a Magic Missile for " + damage + " damage.";
  }

  /**
   * Magical attack type called Fireball that has deals to an entity a random amount of damage between 3 and 6, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Fireball), amount of damage, and entity being attacked
   */
  @Override
  public String fireball(Entity e) {
    int damage = 3; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " hits " + e.getName() + " with a Fireball for " + damage + " damage.";
  }

  /**
   * Magical attack type called Thunderclap that has deals to an entity a random amount of damage between 3 and 6, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Thunderclap), amount of damage, and entity being attacked
   */
  @Override
  public String thunderClap(Entity e) {
    int damage = 3; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " zaps " + e.getName() + " with a Thunderclap for " + damage + " damage.";
  }

}