/**
 * Represents an item that a Hero or an Enemy can hold in their inventory
 * @author Nathan Taylor
 */
public class Item {
  /**
   * The name of the item
   */
  private String name;
  
  /**
   * The amount of gold the item is worth at the store
   */
  private int value;

  /**
   * What type of item this item is. This determines the behavior of the item
   * Example: a potion is of type p which allows the hero to consume the item for HP
   */
  private char type;

  /**
   * Assigns the name of an instance of this item when it is created
   * @param n Name of the item to be created
   * @param v The value in gold of this item
   * @param t The type of this item
   */
  public Item(String n, int v, char t) {
    this.value = v;
    this.type = t;
    this.name = n;
  }

  /**
   * Copy constructor used to create an object that has the same member variables as i
   * @param i The item that is being cloned
   */
  public Item(Item i) {
    if(i != null) {
      this.value = i.value;
      this.type = i.type;
      this.name = i.name;
    }
  }

  /**
   * Used to create a new object of this class with the same member variable values as the object that called this method
   * @return A new Item with the same member variables as the object that this method was called from
   */
  public Item clone() {
    return new Item(this);
  }

  /**
   * Gets the name of this particular item
   * @return Name of this item
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the value of this item in gold
   * @return value of this item in gold
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Gets the type of this item, for example, a key is of type 'k'
   * @return the type of this item
   */
  public char getType() {
    return this.type;
  }

}