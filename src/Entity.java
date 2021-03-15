/**
 * Abstract class to describe an Entity with a name, maximum HP, HP, and is able to attack, as well as take damage
 * @author Nathan Taylor
 */
public abstract class Entity {
  /**
   * Name of the Entity
   */
  private String name;
  
  /**
   * The highest amount of HP this entity is allowed
   */
  private int maxHp;
  
  /**
   * The current hp this entity has
   */
  private int hp;

  /**
   * Constructor to assign values to name and maxHp and to set current HP to maxHP
   * @param n Name to be assigned to this entity
   * @param mHp Integer value for the maximum HP of this entity
   */
  public Entity(String n, int mHp) {
    this.name = n;
    this.maxHp = mHp;
    this.hp = maxHp;
  }
  
  /**
   * Used to deal a certain amount of damage to another entity and describes the type of damage
   * @param e The entity to be receiving the damage
   * @return String detailing the attacker, entity being attacked, and the amount of damage
   */
  public abstract String attack(Entity e);
  
  /**
   * Gets the name of the this entity
   * @return Name of this entity
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Gets the current HP this entity holds
   * @return Current HP of this entity
   */
  public int getHP() {
    return this.hp;
  }
  
  /**
   * Gets the maximum HP this entity is allowed
   * @return The maximum HP of this entity
   */
  public int getMaxHP() {
    return this.maxHp;
  }
  
  /**
   * Adds to the entity's HP without going over the entity's max HP
   * @param h Amount of points to be healed. If the entity's HP + h is greater than or equal to the max HP, the entity's HP is set to the max HP
   */
  public void heal(int h) {
    if(this.getHP() + h >= this.getMaxHP()) { //Preventing overhealing
      this.hp = this.getMaxHP();
    } else {
      this.hp += h;
    }
  }
  
  /**
   * Decreases the entity's HP a certain amount without letting HP go below 0
   * @param h Amount to be decreased from the entity's HP
   */
  public void takeDamage(int h) {
    if(this.getHP() - h < 0) { //Amount of damage is higher than the amount of HP of the entity
      this.hp = 0;
    } else { //Amount of damage is less than or equal to entity HP
      this.hp -= h;
    }
  }
  
  /**
   * Used to get the name and HP of this entity
   * @return The name and HP of this entity on two separate lines
   */
  @Override
  public String toString() {
    return this.getName() + "\nHP: " + this.getHP() + "/" + this.getMaxHP();
  }
   
}