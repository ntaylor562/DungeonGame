/**
 * Used to decorate an Enemy, increasing its max HP by 1 and giving it magical attacks
 * Performs an additional attack after normal attack that is magical
 */
public class Warlock extends EnemyDecorator implements Magical {

  /**
   * Calls super constructor giving the enemy passed in a new name and increased health
   * @param e The enemy object getting decorated
   */
  public Warlock(Enemy e) {
    super(e, e.getName() + ((e.getName().endsWith(" Warlock")) ? "" : " Warlock"), e.getMaxHP() + 1);
  }

  /**
   * Attacks the entity using the super's attack method then picks a random magic attack to use on the entity
   * @param e Entity to be attacked
   * @return A string detailing the attacker, the type of attack, the entity being attacked and the damage dealt to said entity
   */
  @Override
  public String attack(Entity e) {
    String output = "";
    switch((int)(Math.random() * 3)) { //Picking a random attack out of 3 available attacks
      case 0:
        output += magicMissile(e);
        break;
      case 1:
        output += fireball(e);
        break;
      case 2:
        output += thunderClap(e);
        break;
    }
    return super.attack(e) + "\n" + output;
  }

  /**
   * Magical attack type called Magic Missile that has deals to an entity a random amount of damage between 5 and 8, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Magic Missile), amount of damage, and entity being attacked
   */
  @Override
  public String magicMissile(Entity e) {
    int damage = 5; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " hits " + e.getName() + " with a Magic Missile for " + damage + " damage.";
  }

  /**
   * Magical attack type called Fireball that has deals to an entity a random amount of damage between 5 and 8, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Fireball), amount of damage, and entity being attacked
   */
  @Override
  public String fireball(Entity e) {
    int damage = 5; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " hits " + e.getName() + " with a Fireball for " + damage + " damage.";
  }

  /**
   * Magical attack type called Thunderclap that has deals to an entity a random amount of damage between 5 and 8, inclusive
   * @param e The entity being attacked
   * @return String detailing attacker, type of attack (Thunderclap), amount of damage, and entity being attacked
   */
  @Override
  public String thunderClap(Entity e) {
    int damage = 5; //Base damage to be added on to by a random number
    damage += (int)(Math.random() * 4);
    e.takeDamage(damage);
    return this.getName() + " zaps " + e.getName() + " with a Thunderclap for " + damage + " damage.";
  }
}