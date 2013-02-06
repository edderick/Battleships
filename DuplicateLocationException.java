
public class DuplicateLocationException extends Exception{
	private static final long serialVersionUID = 102;

	/**
	 * Constructor for the DuplicateLocatuonException
	 */
	public DuplicateLocationException() {
		//Calls the constructor for exception
		super();
		System.out.println("This location is already in use.");
	}
	
}
