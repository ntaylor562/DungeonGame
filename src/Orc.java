/**
 * Represents an enemy called Orc
 */
public class Orc extends Enemy {
  
  /**
   * Calls the super constructor giving itself the name "Orc", maxHP of 4, and a random item
   */
  public Orc () {
    super("Orc", 4, ItemGenerator.getInstance().generateItem());
  }
  
}