/**
 * Represents an enemy which can be fought by the player
 * Enemies hold a random item that will be given to the player if the player defeats it
 * This enemy has no magical attacks and has a base damage of 5. A random number between 0 and 3 inclusive is added to the damage value
 * @author Nathan Taylor
 */
public abstract class Enemy extends Entity {
 
  /**
   * Represents the random item the enemy holds
   */
  private Item item;
  
  /**
   * Constructor that calls the parent constructor to initialize name and health, then stores the random item this enemy holds
   * @param n The name of this enemy
   * @param mHp The maximum health of this enemy
   * @param i The random item this enemy holds then drops when defeated
   */
  public Enemy(String n, int mHp, Item i) {
    super(n, mHp);
    this.item = i;
  }

  /**
   * Gets the random item this enemy is holding so that it can be picked up by the player when defeated
   * @return The random item this enemy holds
   */
  public Item getItem() {
    return this.item;
  }

  /**
   * Deals damage to entity passed as parameter
   * There is a random number from 0 to 3 added to the amount of damage dealt
   * damage = 5 + rand(0, 3) where rand(0, 3) is a random number from 0 to 3, inclusive
   * @param e The entity taking damage in the attack
   * @return String detailing the name of attacker, name of entity attacked, and the amount of damage
   */
  public String attack(Entity e) {
    int damage = 5; //Base damage to be added on to by a random number
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
    return this.getName() + " " + verb + " " + e.getName() + " for " + damage + " damage.";
  }

}