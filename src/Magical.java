/**
 * Used to implement an entity that can use Magical attacks
 * Magical attacks include Magic Missile, Fireball, and Thunderclap
 * @author Nathan Taylor
 */
interface Magical {

  /**
   * Menu for displaying the options the player has when choosing a Magical attack
   */
  public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";

  /**
   * Used to attack an entity with the Magical attack "Magic Missile"
   * @param e Entity being attacked with Magic Missile
   * @return String detailing the attacker, type of attack (Magic Missile), amount of damage, and the entity being attacked
   */
  public String magicMissile(Entity e);
  
  /**
   * Used to attack an entity with the Magical attack "Fireball"
   * @param e Entity being attacked with Fireball
   * @return String detailing the attacker, type of attack (Fireball), amount of damage, and the entity being attacked
   */
  public String fireball(Entity e);
  
  /**
   * Used to attack an entity with the Magical attack "Thunderclap"
   * @param e Entity being attacked with Thunderclap
   * @return String detailing the attacker, type of attack (Thunderclap), amount of damage, and the entity being attacked
   */
  public String thunderClap(Entity e);

}