/**
 * Represents an enemy called Goblin
 */
public class Goblin extends Enemy {
  
  /**
   * Calls the super constructor giving itself the name "Goblin", maxHP of 2, and a random item
   */
  public Goblin () {
    super("Goblin", 2, ItemGenerator.getInstance().generateItem());
  }

}