
public class BattleshipOutOfBoundsException extends Exception{
	private static final long serialVersionUID = 108;

	/**
	 * Constructor for the BattleshipOutOfBoundException
	 */
	public BattleshipOutOfBoundsException(){
		//Call constructor for exception
		super();
		System.out.println("The battleship is out of bounds");
	}
}
