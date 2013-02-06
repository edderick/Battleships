import java.awt.Point;
import java.util.ArrayList;


public class HumanPlayer extends Player{

	//Instance variables
	private Point cursorPosition;
	private boolean placementMode; //False will be game mode
	private Battleship currentBattleship = new Battleship(new Point(0,0), true, 5);
	private int noOfBattleshipsPlaced = 0;
	
	
	/**
	 * Constructor for the HumanPlayer class
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public HumanPlayer(String name, int width, int height) {
		//Calls constructor in the parent class
		super(name, width, height);
		cursorPosition = new Point(0,0);
		placementMode = true;
	}


	/**
	 * Allows public access to cursor position
	 * @return The position of the cursor
	 */
	public Point getCursorPosition(){
		return cursorPosition;
	}
	

	/**
	 * Moves the cursor, or battleship up one space
	 * @return True if successful
	 */
	public boolean moveUp(){
		if (!placementMode) return moveCursor(0,-1);
		else return currentBattleship.moveUp(width, height);
	}
	
	
	/**
	 * Moves the cursor, or battleship down one space
	 * @return True if successful
	 */
	public boolean moveDown(){
		if (!placementMode) return moveCursor(0,1);
		else return currentBattleship.moveDown(width, height);
	}
	
	
	/**
	 * Moves the cursor, or battleship left one space
	 * @return True if successful
	 */
	public boolean moveLeft(){
		if (!placementMode) return moveCursor(-1, 0);
		else return currentBattleship.moveLeft(width, height);
	}
	
	
	/**
	 * Moves the cursor, or battleship right one space
	 * @return True if successful
	 */
	public boolean moveRight(){
		if (!placementMode) return moveCursor(1,0);
		else return currentBattleship.moveRight(width, height);
	}
	
	
	/**
	 * Moves the cursor by given changes in x and y
	 * @param dx Change in x
	 * @param dy Change in y
	 * @return True if successful
	 */
	private boolean moveCursor(int dx, int dy){
		//Check that the given changes in x and y will lie within the grid
		if( (cursorPosition.x + dx < width ) && (cursorPosition.x + dx >= 0) && (cursorPosition.y + dy < height) && (cursorPosition.y + dy >= 0) ){
			//Increment x and y
			cursorPosition.x += dx;
			cursorPosition.y += dy;
			return true;
		}
		//else
		return false;
	}
	
	
	/**
	 * Places the current battleship on the gird
	 * @return True if successful
	 */
	public boolean placeCurrentBattleship(){
		try{
			placeBattleship(currentBattleship);
		}
		catch(Exception e){
			return false;
		}
		
		//if exception is thrown code below here will not be executed as the function will return false;
		
		noOfBattleshipsPlaced++;
		
		switch (noOfBattleshipsPlaced){
			case 1: currentBattleship = new Battleship(new Point(0,0), true, 4); break;
			case 2: currentBattleship = new Battleship(new Point(0,0), true, 3); break;
			case 3: currentBattleship = new Battleship(new Point(0,0), true, 3); break;
			case 4: currentBattleship = new Battleship(new Point(0,0), true, 2); break;
			case 5: endPlacementMode(); break;
		}
		return true;
	}

	
	/**
	 * Ends placement mode
	 * Sets placementMode to false
	 */
	private void endPlacementMode() {
		placementMode = false;		
	}
	
	
	/**
	 * Returns the players battleships
	 * @return The players battleships
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Battleship> getBattleships(){
		//If in placement mode, also include the current Battleship
		if(placementMode){
			//Avoid adding the currenBattleship to the battleships arrayList
			ArrayList<Battleship> tempBattleships = new ArrayList<Battleship>();
			tempBattleships = (ArrayList<Battleship>) battleships.clone();
			tempBattleships.add(currentBattleship);
			return tempBattleships;
		}
		else{
			return (ArrayList<Battleship>) battleships.clone();	
		}
	}
	
	
	/**
	 * Rotates the current Battleship
	 * @return true if successful
	 */
	public boolean rotateBattleship(){
		return currentBattleship.rotate(width, height);
	}
	
	
	/**
	 * Checks if in placement mode
	 * ie. placementMode = true
	 * @return true if in placement mode
	 */
	public boolean isPlacementMode(){
		return placementMode;
	}
	
	
}
