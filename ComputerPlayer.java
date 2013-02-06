import java.awt.Point;
import java.util.Random;


public abstract class ComputerPlayer extends Player{
	private static final long serialVersionUID = 101;


	/**
	 * Constructor for the ComputerPlayer class
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public ComputerPlayer(String name, int width, int height) {
		//Calls constructor of Player
		super(name, width, height);
		generateBattleships();
	}
	
	
	/**
	 * The action that the computer player takes on its turn
	 * @return the Point that will be shot at
	 */
	public abstract Point takeTurn();
	
	
	/**
	 * Randomly generates 5 legally placed battleships
	 */
	public void generateBattleships(){
		
		System.out.println("Placing " + name + "'s Battleships...");
		
	    Random r = new Random();
		
	    //Loops through creating one battleship of each size
		int size = 2;
		while (size <= 5){ 
			try{
				boolean isVertical;
				
				if (r.nextInt(2) == 0) isVertical = true;
				else isVertical = false;
				
				Battleship b;
				
				if(isVertical) b = new Battleship(new Point(r.nextInt(width),r.nextInt(height - size)), true, size);
				else b = new Battleship(new Point(r.nextInt(width - size),r.nextInt(height)), true, size);
				
				if(isVertical) b.rotate(width, height);
				
				placeBattleship(b);
				
				size++;
			}
			catch(Exception e){
				//Don't increment i
			}
		}
		
		size = 3;
		
		//Battleships of size three occur twice
		while (size == 3 ){ 
			try{
				boolean isVertical;
				
				if (r.nextInt(2) == 0) isVertical = true;
				else isVertical = false;
				
				Battleship b;
				
				if(isVertical) b = new Battleship(new Point(r.nextInt(width),r.nextInt(height - size)), true, size);
				else b = new Battleship(new Point(r.nextInt(width - size),r.nextInt(height)), true, size);
				
				if(isVertical) b.rotate(width, height);
				
				placeBattleship(b);
				
				size++;
			}
			catch(Exception e){
				//Don't increment i
			}
		}
		
	}
}
