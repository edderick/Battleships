import java.awt.Point;
import java.util.Random;


public class IntelligentComputerPlayer extends ComputerPlayer{
	
	//Instance variables
	private Point hitLast;
	
	/**
	 * Constructor for the IntelligentComputerPlayer class
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public IntelligentComputerPlayer(String name, int width, int height) {
		//Calls constructor of Computer player
		super(name, width, height);
	}

	
	/**
	 * Action taken when the computer player takes its turn
	 * Shoots at the player based on previous shots
	 * @return the Point that will be shot at
	 */
	@Override
	public Point takeTurn() {
				
		if(hitLast != null){
			
			if(isValidPoint(new Point(hitLast.x, hitLast.y-1))) return new Point(hitLast.x, hitLast.y-1); //Up
			if(isValidPoint(new Point(hitLast.x, hitLast.y+1))) return new Point(hitLast.x, hitLast.y+1); //Down
			if(isValidPoint(new Point(hitLast.x-1, hitLast.y))) return new Point(hitLast.x-1, hitLast.y); //Left
			if(isValidPoint(new Point(hitLast.x+1, hitLast.y))) return new Point(hitLast.x+1, hitLast.y); //Right
			
		}
		else{
			//Uses all the previous hits
			for (GridSquare g: placesShotAt){
				if(g.getIsShip()){
					Point hitSquare = g.getPosition();
					if(isValidPoint(new Point(hitSquare.x, hitSquare.y-1))) return new Point(hitSquare.x, hitSquare.y-1); //Up
					if(isValidPoint(new Point(hitSquare.x, hitSquare.y+1))) return new Point(hitSquare.x, hitSquare.y+1); //Down
					if(isValidPoint(new Point(hitSquare.x-1, hitSquare.y))) return new Point(hitSquare.x-1, hitSquare.y); //Left
					if(isValidPoint(new Point(hitSquare.x+1, hitSquare.y))) return new Point(hitSquare.x+1, hitSquare.y); //Right
				}
			}
		}	
		return randomPoint();
	}
	
	
	/**
	 * Generates a random point within the gird
	 * @return a random point
	 */
	public Point randomPoint(){
		Random r = new Random();
		return new Point(r.nextInt(width),r.nextInt(height));
	}
	
	
	/**
	 * Checks if the point is within the limits of the grid
	 * @return True if the point it is within the grid
	 */
	public boolean inGrid(Point point){
		if( (point.x < width ) && (point.x>= 0) && (point.y < height) && (point.y >= 0) ){
			return true;
		}
		return false;
			
	}
	
	
	/**
	 * Checks if the point is valid to be shot at: 
	 * Hasn't been shot at and is within grid
	 * @return True if the point is valid
	 */
	public boolean isValidPoint(Point point){
		if(inGrid(point) && !shotBefore(point)) return true;
		return false;
	}
	
	
	/**
	 * Called if the player hit a battleship at a given point
	 * @param point The point that was correct
	 */
	@Override
	public void hitBattleship(Point point){
		placesShotAt.add(new GridSquare(point, true));
		hitLast = (Point) point.clone();
	}
	
	
	/**
	 * Called if the players shot hit nothing
	 * @param point The point that was empty
	 */
	@Override
	public void hitNothing(Point point){
		placesShotAt.add(new GridSquare(point, false));
		hitLast = null;
	}
	
	

}
