import java.util.Scanner;
import java.io.*;
import java.awt.Point;

/**
 * Singleton class to represent a location system that allows for items, enemies, the player, and empty spaces to be located
 * @author Nathan Taylor
 */
public class Map {

  /**
   * Instance of this singleton
   */
  private static Map instance = null;

  /**
   * The 2 dimensional array containing the actual items on the map
   */
  private char[][] map;

  /**
   * The 2 dimensional array that determines what has been revealed on the map. For any i and j such that 0 <= i < 5 and 0 <= j < 5, revealed[i][j] = false if that point on the map will show up as an x. revealed[i][j] = true if the player has already been at that coordinate, allowing the map to show what's on that spot. 
   */
  private boolean[][] revealed;

  /**
   * Constructor for Map class
   * Initializing the map and revealed arrays, then calls the loadMap function to load the first map
   * to fill the map array
   */
  private Map() {
    map = new char[5][5];
    revealed = new boolean[5][5];
    loadMap(1);
  }

  /**
   * Creates an instance of Map if the object has not yet been created and returns the instance of this class
   * @return A Map object that is supposed to be the only one in the program
   */
  public static Map getInstance() {
    if(instance == null) {
      instance = new Map();
    }
    return instance;
  }

  /**
   * Reads in Map#.txt characters to map[][] array and unreveals all points except start
   * @param mapNum number of map in which to read in
   */
  public void loadMap(int mapNum){
    Scanner inFile = null;
    try {
      inFile = new Scanner(new File("Map" + Integer.toString(mapNum) + ".txt"));
    }
    catch(FileNotFoundException x) {
      System.out.println("Map file not found. Exiting");
      System.exit(1);
    }
    while(inFile.hasNextLine()){
      for(int y = 4; y >= 0; y--){
        for(int x = 0; x < 5; x++){
          map[x][y] = inFile.next().charAt(0);
          if(map[x][y] != 's') {
            revealed[x][y] = false;
          }
        }
        if(inFile.hasNextLine()){
          inFile.nextLine();
        } 
      }
    }
    inFile.close();
  }

  /**
   * Retrieves character from map array based on Point p param
   * @param p point of location to retrieve char
   * @return retrieved char at location
   */
  public char getCharAtLoc(Point p){
    return map[(int)p.getX()][(int)p.getY()];
  }

  /**
   * Shows all points on the map and the location of the player
   * @param p The location of the player on the map
   */
  public void displayMap(Point p) {
    for(int y = 4; y >= 0; y--){
      for(int x = 0; x < 5; x++){
        if(x == (int)p.getX() && y == (int)p.getY()){ //Displaying the player
          System.out.print("*");
        } 
        else if(revealed[x][y] == true) { //Show revealed coordinate
          System.out.print(map[x][y]);
        }
        else { //Unreavealed coordinate
          System.out.print("x");
        }
        System.out.print(" ");
      }
      System.out.println();      
    }    
  }

  /**
   * Retrieves the location of start coordinate on map
   * @return Point of start location
   */
  public Point findStart(){
    Point start = new Point(0, 0);
    for(int y = 4; y >= 0; y--){
      for(int x = 0; x < 5; x++){
        if(map[x][y] == 's') {
          start.setLocation(x,y);
          revealed[x][y] = true;
          break;
        }
      }
    }
    return start;
  }

  /**
   * Sets the reveal status of the location given by p to true
   * @param p The point that is being revealed
   */
  public void reveal(Point p){
    revealed[(int)p.getX()][(int)p.getY()] = true;
  }

  /**
   * Removes anything at the coordinate given and replaces with 'n'
   * @param p The point that is being set to 'n'
   */
  public void removeCharAtLoc(Point p){
    map[(int)p.getX()][(int)p.getY()] = 'n';
  }

}