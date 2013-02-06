import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

//"Implements Serializable" added to allow saving
public class Battleship implements Serializable {
	private static final long serialVersionUID = 104;

	//Instance Variables
	private ArrayList<Point> locations = new ArrayList<Point>();
	private ArrayList<Point> hitLocations = new ArrayList<Point>();
	//Instance variables containing the constructor parameters
	private Point topLeft;
	private boolean isHorizontal;
	private int size;
	
	
	/**
	 * Main constructor for the battleship objects.
	 * Calculates the points of the battle ship based on the parameters
	 * @param point the topLeft corner of the battleship
	 * @param isHorizontal true when the battle ship is horizontal
	 * @param size the number of squares the battleship occupies 
	 */
	public Battleship(Point point, Boolean isHorizontal, int size){
		
		//Calculate the points occupied
		for(int i = 0; i < size; i++){
			if(isHorizontal){
				locations.add(new Point(point.x + i, point.y));
			}	
			else{ //i.e. is vertical 
				locations.add(new Point(point.x, point.y + i));
			}
		}
		
		//Set instance variables
		this.topLeft = (Point) point.clone();
		this.size = size;
		this.isHorizontal = isHorizontal;
	}
	
	
	/**
	 * Checks whether a given point is occupied
	 * @param point the point we are enquiring about
	 * @return true if the ship is at this point
	 */
	public boolean isAtPoint(Point point){
		//Loops through all locations to see if one equals point
		for(Point p : locations){
			if ((p.x == point.x) && (p.y == point.y)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks to see if a point has been hit before
	 * Loops through all hit locations, comparing them with the point
	 * @param point the point we are enquiring about
	 * @return true if this point has been hit before
	 */
	private boolean hitBefore(Point point){
		//Loops through all hitLocations to see if one equals point
		for(Point p : hitLocations){
			if ((p.x == point.x) && (p.y == point.y)){
				return true;
			}
		}
		return false;	
	}
	
	
	/**
	 * If the point given is occupied by the battleship, and hasn't
	 * already been hit, then set it as hit
	 * @param point the point we are enquiring about
	 * @return true if the point gets set
	 */
	public boolean shotAt(Point point){
		if (isAtPoint(point)){
			if (!hitBefore(point)){
				hitLocations.add((Point)point.clone());
				
				//Print "Hit!" if it is hit, print "Hit and sink!" if the ship is totally destroyed. 
				System.out.print("Hit");
				if(isDestroyed()) System.out.println(" and Sink!");
				else System.out.println("!");
				 
			    return true;
			}			
		}
		return false;
	}
	
	
	/**
	 * Checks to see if the battleship has been destroyed.
	 * i.e. All points have been hit
	 * @return True if the battleship has been destroyed
	 */
	public boolean isDestroyed(){
		if (hitLocations.containsAll(locations)){
			return true; 
		}
		else return false;
	}
	
	
	/**
	 * Checks if two battleships overlap
	 * @param point the point we are enquiring about
	 * @return true if this point has been hit before
	 */
	public boolean inSameLocation(Battleship otherShip){
		//Loops through all the locations checking if the the otherShip is at that point
		for(Point p: locations){
			if (otherShip.isAtPoint(p)) return true;
		}
		return false;
	}

	
	/**
	 * Returns the locations occupied by the battleship
	 * @return an array list of points which are occupied by the battleship
	 */
	public ArrayList<Point> getLocations(){
		return locations;
	}
	
	
	/**
	 * Attempts to move the battleship up one space
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship has been moved
	 */
	public boolean moveUp(int maxWidth, int maxHeight){
		//Checks to see if it is clear			
		if (topLeft.y - 1 < 0) return false;
				
		//Move points
		for (Point p: locations){
			p.y--;
		}
		
		topLeft.y--;
		
		return true;
	}
	
	
	/**
	 * Attempts to move the battleship down one space
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship has been moved
	 */
	public boolean moveDown(int maxWidth, int maxHeight){
		//Checks to see if it is clear:
		//If it's horizontal, then check the space below.
		//If it's vertical, then check the space below, plus height
		if (((isHorizontal) && (topLeft.y + 2  > maxHeight)) || ((!isHorizontal) && (topLeft.y + size + 1 > maxHeight))) return false;
		
		//Move points
		for (Point p: locations){
			p.y++;
		}
		
		topLeft.y++;
		
		return true;
	}
	
	
	/**
	 * Attempts to move the battleship left one space
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship has been moved
	 */
	public boolean moveLeft(int maxWidth, int maxHeight){
		//Checks to see if it is clear
		if (topLeft.x - 1 < 0) return false;
		
		//Move points
		for (Point p: locations){
			p.x--;
		}
		
		topLeft.x --;
		
		return true;
	}
	
	
	/**
	 * Attempts to move the battleship right one space
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship has been moved
	 */
	public boolean moveRight(int maxWidth, int maxHeight){
		//Checks to see if it is clear
		//If it's horizontal, then check the space below.
		//If it's vertical, then check the space below, plus height
		if (((!isHorizontal) && (topLeft.x + 2  > maxWidth)) || ((isHorizontal) && (topLeft.x + size + 1 > maxWidth))) return false;
		
		//Move points
		for (Point p: locations){
			p.x++;
		}
		
		topLeft.x++;
		
		return true;	
	}
	
	
	/**
	 * Attempts rotate the battleship
	 * (Vertical to horizontal, Top-left stays the same)
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship has been rotated
	 */
	public boolean rotate(int maxWidth, int maxHeight){
	
		if(isHorizontal){ //Then make it vertical
		
			//Checks it's clear
			if(topLeft.y + size > maxHeight){
				return false;
			}
		
			locations.clear();
			isHorizontal = false;
	
			//Rotate points
			for(int i = 0; i < size; i++){
				locations.add(new Point(topLeft.x, topLeft.y + i));
			}
		
		}
		else{ //It is vertical, so make it Horizontal
		
			//Checks it's clear
			if(topLeft.x + size > maxWidth){
				return false;
			}
		
			locations.clear();
			isHorizontal = true;
		
			//Rotate points
			for(int i = 0; i < size; i++){
				locations.add(new Point(topLeft.x + i, topLeft.y));
			}	
		}
	
		return true;
	}
	
	
	/**
	 * Method checks to see if the battleship lies within the grid
	 * @param maxWidth The maximum width of the gird
	 * @param maxHeight The maximum height of the grid
	 * @return true if the battleship is Within the grid
	 */
	public boolean isWithinBounds(int maxWidth, int maxHeight){
		if(isHorizontal){
			if( (topLeft.x >= 0) && (topLeft.x + size < maxWidth) && (topLeft.y >=0) && (topLeft.y < maxHeight) ){
				return true;
			} 
		}
		else{ //thus is vertical
			if( (topLeft.x >= 0) && (topLeft.x < maxWidth) && (topLeft.y >=0) && (topLeft.y + size  < maxHeight) ){
				return true;
			}
		}
		return false;	
	}
	
	
}

	

