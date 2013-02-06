import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

//"Implements Serializable" added to allow saving
public abstract class Player implements DrawablePlayerInterface, Serializable{
	private static final long serialVersionUID = 106;

	//Instance variables
	protected String name;
	protected int width;
	protected int height;
	protected ArrayList<GridSquare> placesShotAt = new ArrayList<GridSquare>();
	protected ArrayList<Battleship> battleships = new ArrayList<Battleship>();
	
	
	/**
	 * Constructor for Player
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public Player(String name, int width, int height){
		
		//set instance variables
		this.name = name;
		this.width = width;
		this.height = height;
		
	}
	
	
	/**
	 * Returns the players battleships
	 * @return The players battleships
	 */
	public ArrayList<Battleship> getBattleships(){
		return battleships;
	}
	
	
	/**
	 * Returns the grid squares the player has shot at
	 * @return The grid squares the player has shot at
	 */
	public ArrayList<GridSquare> getPlacesShotAt(){
		return placesShotAt;
	}
	
	
	/**
	 * Returns whether the given position contains one of the players battleships
	 * @param p The position to test
	 * @return True if the p 
	 */
	public boolean isBattleship(Point p){
		//loops through all battleships checking that its is at the given point
		for(Battleship b: battleships){
			if(b.isAtPoint(p)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Attempts to place a battleship on the gird
	 * @param Battleship to be placed
	 */
	public void placeBattleship(Battleship battleship) throws BattleshipOutOfBoundsException, DuplicateLocationException {
		//Check the batteship is in a valid position
		if(!battleship.isWithinBounds(width, height)){
			throw new BattleshipOutOfBoundsException();	
		}
		
		for(Battleship b: battleships){
			if(battleship.inSameLocation(b)){
				throw  new DuplicateLocationException();
			}
		}
		
		//Store battleship
		battleships.add(battleship);
	
	}
	
	
	/**
	 * Tells the player they have been shot at
	 * @param point the point being shot at
	 * @return True if it hits a ship
	 */
	public boolean shotAt(Point point){
		//Sets flag as false
		boolean hit = false;
		for(Battleship b: battleships){
			if(b.shotAt(point)){
				//Set flag as true
				hit = true;
			}
		}
		
		if(!hit) System.out.println("Miss!");
		
		//return flag
		return hit;
	}
	
	
	/**
	 * Called if the player hit a battleship at a given point
	 * @param point The point that was correct
	 */
	public void hitBattleship(Point point){
		placesShotAt.add(new GridSquare((Point)point.clone(), true));
	}
	
	
	/**
	 * Called if the players shot hit nothing
	 * @param point The point that was empty
	 */
	public void hitNothing(Point point){
		placesShotAt.add(new GridSquare((Point)point.clone(), false));
	}
	
	
	/**
	 * Checks if the user has shot at this square before
	 * @return True if point has been shot before
	 */
	public boolean shotBefore(Point point){
		GridSquare checkSquare = new GridSquare(point);
		
		//Loops through all places shot at checking if they have the same point as the parameter
		for(GridSquare g: placesShotAt){
			if(g.equals(checkSquare)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks if all of the battleships have been destroyed
	 * @return True if all battleships are destroyed
	 */
	public boolean allShipsDestroyed(){
		//Loops through all battleships checking if they have been destroyed
		for(Battleship b: battleships){
			if(!b.isDestroyed()){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Allows public access to name
	 * @return the string name
	 */
	public String getName(){
		return name;
	}

	
	/**
	 * Allows public access to width
	 * @return the int width
	 */
	public int getWidth(){
		return width;
	}
	
	
	/**
	 * Allows public access to height
	 * @return the int height
	 */
	public int getHeight(){
		return height;
	}
	
}
