import java.awt.Point;
import java.util.Random;


public class RandomComputerPlayer extends ComputerPlayer{
	/**
	 * Constructor for the RandomComputerPlayer class
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public RandomComputerPlayer(String name, int width, int height) {
		//Call constructor for ComputerPlayer
		super(name, width, height);
	}

	
	/**
	 * Action taken when the computer player takes its turn
	 * Generates a random point within the gird
	 * @return the Point that will be shot at
	 */
	@Override
	public Point takeTurn() {
		Random r = new Random();
		return new Point(r.nextInt(width),r.nextInt(height));
	}

}
