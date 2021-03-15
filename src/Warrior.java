/**
 * Used to decorate an Enemy, increasing its max HP by 2 and giving it an additional attack
 */
public class Warrior extends EnemyDecorator {

  /**
   * Calls super constructor giving the enemy passed in a new name and increased health
   * @param e The enemy object getting decorated
   */
  public Warrior(Enemy e) {
    super(e, e.getName() + ((e.getName().endsWith(" Warrior")) ? "" : " Warrior"), e.getMaxHP() + 2);
  }

  /**
   * Calls the super's attack method twice and returns the output
   * @param e The entity being attacked
   */
  public String attack(Entity e) {
    return super.attack(e) + "\n" + super.attack(e);
  }
}