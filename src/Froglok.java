/**
 * Represents an enemy called Froglok
 */
public class Froglok extends Enemy {
  
  /**
   * Calls the super constructor giving itself the name "Froglok", maxHP of 2, and a random item
   */
  public Froglok() {
    super("Froglok", 2, ItemGenerator.getInstance().generateItem());
  }
  
}