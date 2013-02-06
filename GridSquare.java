import java.awt.Point;
import java.io.Serializable;

//"Implements Serializable" added to allow saving
public class GridSquare implements Serializable{
	private static final long serialVersionUID = 103;

	//Instance Variables
	private Point position; 
	private boolean isShip;
	
	
	/**
	 * Constructor for the GridSquare class
	 * GridSquare contents not specified
	 * @param position The position that this GridSquare refers to
	 */
	public GridSquare(Point position){
		//Set instance variable to the value of parameter
		this.position = (Point) position.clone();
	}
	
	
	/**
	 * Constructor for the GridSquare class
	 * GridSquare contents are specified
	 * @param position //"Implements Serializable" added to allow savingThe position that this GridSquare refers to
	 * @param isShip True if the GridSquare does contain a ship
	 */
	public GridSquare(Point position, Boolean isShip){
		this.position = position;
		this.isShip = isShip;
	}
	
	
	/**
	 * Returns the position of the GridSquare
	 * @return the position of the GridSquare
	 */
	public Point getPosition(){
		return position;
	}
	
	
	/**
	 * Checks if there is a battleship in this GridSquare
	 * @return True if there is a battleship in this GridSquare
	 */
	public boolean getIsShip(){
		return isShip;
	}
	
	
	/**
	 * Checks if this GridSquare is the same as another GridSquare
	 * @param o The GridSquare to be compared with
	 * @return True if they are equal in x and y co-ordinates
	 */
	public boolean equals(Object o){
		if(o instanceof GridSquare){
			if( ( ((GridSquare)o).position.x == this.position.x) && ( ((GridSquare)o).position.y == this.position.y ) ){
				return true;
			}
		}
		return false;
	}
	
	
}
