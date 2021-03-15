/**
 * Decorator class to add functionality to an Enemy
 * Allows decorators to change the name and maximum HP of the enemy
 */
public abstract class EnemyDecorator extends Enemy {
  /**
   * Calls super constructor using new name and maxHP
   * @param e The enemy we are decorating
   * @param n The new name of the enemy
   * @param mHp The new maximum HP of the enemy
   */
  public EnemyDecorator(Enemy e, String n, int mHp) {
    super(n, mHp, ItemGenerator.getInstance().generateItem());
  }
  
}