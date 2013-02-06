import java.awt.Point;
import java.util.ArrayList;


public interface DrawablePlayerInterface{

	/**
	 * Returns the players battleships
	 * @return The players battleships
	 */
	public ArrayList<Battleship> getBattleships();
	
	/**
	 * Returns the grid squares the player has shot at
	 * @return The grid squares the player has shot at
	 */
	public ArrayList<GridSquare> getPlacesShotAt();
	
	/**
	 * Returns whether the given position contains one of the players battleships
	 * @param p The position to test
	 * @return True if the player has a battleship in the position, false otherwise
	 */
	public boolean isBattleship(Point p);
}
