/**
 * Represents an enemy called Troll
 */
public class Troll extends Enemy {
  
  /**
   * Calls the super constructor giving itself the name "Troll", maxHP of 5, and a random item
   */
  public Troll () {
    super("Troll", 5, ItemGenerator.getInstance().generateItem());
  }
  
}