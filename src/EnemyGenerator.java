import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

/**
 * A singleton generator class to generate enemies defined by the EnemyList.txt file
 * Enemies generated past level 1 are decorated with either a Warlock or Warrior and are decorated multiple times depending on the level (a Warrior decorated enemy is decorated with Warrior twice on level 3 and three times on level 4)
 * @author Nathan Taylor
 */
public class EnemyGenerator {

  /**
   * Instance of this singleton
   * null at first but initialized when getInstance is called
   */
  private static EnemyGenerator instance = null;

  /**
   * Private constructor to prevent instantiation
   */
  private EnemyGenerator() {}

  /**
   * Creates an instance of EnemyGenerator if the object has not yet been created and returns the instance of this class
   * @return An EnemyGenerator object that is supposed to be the only one in the program
   */
  public static EnemyGenerator getInstance() {
    if(instance == null) {
      instance = new EnemyGenerator();
    }
    return instance;
  }

  /**
   * Creates a new enemy object
   * @param level The level the player is on. Used to decorate the enemy if level greater than 1
   * @return A new Enemy object that is decorated with Warrior or Warlock if level greater than 1
   */
  public Enemy generateEnemy(int level) {
    Enemy returnedEnemy = null;

    switch((int)(Math.random() * 4)) { //Picking which enemy we're generating
      case 0:
        returnedEnemy = new Froglok();
        break;
      case 1:
        returnedEnemy = new Goblin();
        break;
      case 2:
        returnedEnemy = new Orc();
        break;
      case 3:
        returnedEnemy = new Troll();
        break;
    }

    if(level > 1) { //Decorating an enemy with Warrior or Warlock level - 1 times
      switch((int)(Math.random() * 2)) {
        case 0:
          while (level > 1) {
            returnedEnemy = new Warlock(returnedEnemy);
            --level;
          }
          break;
        case 1:
          while (level > 1) {
            returnedEnemy = new Warrior(returnedEnemy);
            --level;
          }
          break;
      }
    }
    return returnedEnemy;

  }
}