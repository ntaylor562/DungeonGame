import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

/**
 * Singleton Generator to create items at random
 * The items to be selected are read in from a file called ItemList.txt
 * @author Nathan Taylor
 */
public class ItemGenerator {

  /**
   * The instance of this singleton
   */
  private static ItemGenerator instance = null;
    
  /**
   * Stores the items read from the ItemList txt file
   */
  private ArrayList<Item> itemList;

  /**
   * Constructor that reads through the ItemList.txt file for possible items to generate
   * Populates the itemList ArrayList with item templates
   */
  private ItemGenerator() {
    this.itemList = new ArrayList<Item>();
    Scanner inFile = null;
    try {
      inFile = new Scanner(new File("ItemList.txt"));
    }
    catch(FileNotFoundException x) {
      System.out.println("Item Generator file not found. Exiting");
      System.exit(1);
    }
    while(inFile.hasNextLine()) { //Reading through file
      String [] itemData = inFile.nextLine().split(",");
      itemList.add(new Item(itemData[0], Integer.parseInt(itemData[1]), itemData[2].charAt(0)));
    }
    inFile.close();
  }

  /**
   * Creates an instance of ItemGenerator if the object has not yet been created and returns the instance of this class
   * @return An ItemGenerator object that is supposed to be the only one in the program
   */
  public static ItemGenerator getInstance() {
    if(instance == null) {
      instance = new ItemGenerator();
    }
    return instance;
  }
  

  /**
   * Creates a new Item that is a copy of a random Item in the itemList
   * @return New instance of an item in itemList selected at random
   */
  public Item generateItem() {
    return itemList.get((int)(Math.random() * itemList.size())).clone();
  }

  /**
   * Creates and returns a new item that represents a potion
   * @return New Item object representing a Health Potion
   */
  public Item getPotion() {
    for(int i = 0; i < itemList.size(); ++i) {
      if(itemList.get(i).getName().equals("Health Potion")) {
        return itemList.get(i).clone();
      }
    }
    return null;
  }
  
  /**
   * Creates and returns a new item that represents a Key
   * @return New Item object representing a Key
   */
  public Item getKey() {
    for(int i = 0; i < itemList.size(); ++i) {
      if(itemList.get(i).getName().equals("Key")) {
        return itemList.get(i).clone();
      }
    }
    return null;
  }
}