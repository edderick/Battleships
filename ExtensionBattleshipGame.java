import java.awt.Point;
import java.io.*;


public class ExtensionBattleshipGame extends WindowController{
	private static final long serialVersionUID = 107;

	//Instance variables 
	PlayerDrawer playerDrawer;
	Player playerOne, playerTwo, currentPlayer, currentOpponent;
	boolean saveMode;
	
	
	/**
	 * The main method. Shows the Main menu
	 */
	public static void main(String[] args){		
		
		switch(showMainMenu()){
			case 1: showNewGameMenu(); break;
			case 2: showLoadGameMenu(); break;
			case 0: System.exit(0); break;
		}
	}
		
	
	/**
	 * Waits for the user to input a number within a range
	 * @param min The minimum accepted number
	 * @param max The maximum accepted number
	 * @return The users input
	 */
	public static int getNumericalUserInput(int min, int max){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = null;
		int option = 0;
		
		//Will repeat until a valid input is given
		while(input == null){	
			
			//Attempts to read a line from the user
			try {
				input = br.readLine();
			}
			catch(IOException ioe){
				System.out.println("Please try again");
				input = null;
				continue;
			}
			
			//Attempts converting the string to an int
			try{
				option = Integer.parseInt(input);
			}
			catch(NumberFormatException nfe){
				System.out.println("Please enter a valid integer");
				input = null;
				continue;
			}
			
			//Checks within range
			if((option > max) || (option < min)){ 
				System.out.println("Please enter an integer within the range");
				input = null;
				continue;
			}
		}
		return option;
	}
	
	
	/**
	 * Outputs the main menu to the console and waits for a response
	 * @return The users input
	 */
	public static int showMainMenu(){
		
		System.out.println("Please choose and option:");
		System.out.println("1. New game");
		System.out.println("2. Load game");
		System.out.println("0. Exit");
		
		return getNumericalUserInput(0,2);
	}
	
	/**
	 * Shows the menu for a new game,
	 * Collects information about the game from the user,
	 * And starts the game.
	 */
	public static void showNewGameMenu(){
		//Find player one details
		System.out.println("What is player one?");
		System.out.println("1. Human Player");
		System.out.println("2. Random Computer Player");
		System.out.println("3. Intelligent Computer Player");
		int p1 = getNumericalUserInput(1,3);
		
		//Find player two details
		System.out.println("What is player two?");
		System.out.println("1. Random Computer Player");
		System.out.println("2. Intelligent Computer Player");
		int p2 = getNumericalUserInput(1,2);
		
		//Find grid width
		System.out.print("How wide is the grid? (Minimum 10, Maximum 40)");
		int width = getNumericalUserInput(10, 40);
		
		//Find grid height
		System.out.print("How tall is the grid? (Minimum 10, Maximum 40)");
		int height = getNumericalUserInput(10, 40);
		
		Player playerOne, playerTwo;
		
		switch(p1){
			case 1 : playerOne = new HumanPlayer("Player One", width, height); break;
			case 2 : playerOne = new RandomComputerPlayer("Player One", width, height); break;
			case 3 : playerOne = new IntelligentComputerPlayer("Player One", width, height); break;
			default : playerOne = new HumanPlayer("Player One", width, height); break;
		}
		
		switch(p2){
			case 1 : playerTwo = new RandomComputerPlayer("Player Two", width, height); break;
			case 2 : playerTwo = new IntelligentComputerPlayer("Player Two", width, height); break;
			default :  playerTwo = new RandomComputerPlayer("Player Two", width, height); break;
		}
		
		//Starts the game with the given conditions
		@SuppressWarnings("unused") //b never needs to be "used"
		ExtensionBattleshipGame b = new ExtensionBattleshipGame(playerOne, playerTwo, width, height);
		
	}
	
	/**
	 * Shows the menu for loading a game
	 * Loads in the appropriate objects
	 * And starts the game
	 */
	public static void showLoadGameMenu(){
		boolean fileOpen = false;
		ObjectInputStream objectIn;
		Player playerOne = null, playerTwo = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName;
		
		while(!fileOpen){
			System.out.print("Name the file you wish to load (.sav is automatically added to the end): ");
		
			//Attempt to get a user input, if the user does not give an input, try again
			try {
				fileName = br.readLine();
			} catch (IOException e1) {
				continue;
			}
			
			//Attempt to open the given file
			try {
				objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName + ".sav")));
				playerOne = (Player) objectIn.readObject();
				playerTwo = (Player) objectIn.readObject();
				fileOpen = true;
			} 
			catch (Exception e) {
				System.out.println("Failed to open");
			}
		}
		
		//Start the game with the given objects
		@SuppressWarnings("unused") //b never needs to be "used"
		ExtensionBattleshipGame b = new ExtensionBattleshipGame(playerOne, playerTwo, playerOne.getWidth(), playerOne.getHeight());
	}
	
	
	/**
	 * Attempts to save the game to a file
	 */
	public void saveGame(Player playerOne, Player playerTwo) {
		
		//Prevents the keyboard inputs manipulating the battleships
		saveMode = true;
		
		System.out.print("Name your file (.sav is automatically added to the end): ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName;
		ObjectOutputStream objectOut;
		
		//Read in a name for the file, If there isn't one present, default to DefaultGame
		try {
			fileName = br.readLine();
		} catch (IOException e1) {
			fileName = "DefaultGame";
		}
		
		//Try opening/creating the file with the given name
		try {
			objectOut = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream(fileName + ".sav")));
		} 
		catch (Exception e){
			System.err.println("Failed to save");
			return;
		}
		
		//Try Writing the objects to this file
		try {
			objectOut.writeObject(playerOne);
			objectOut.writeObject(playerTwo);
			objectOut.close();
		} catch (IOException e) {
			System.err.println("Failed to save Objects");
			return;
		}

		System.out.println("The file "+ fileName + ".sav has been saved.");
		
		//Allow the keyboard inputs again
		saveMode = false;
	}
	                            
	
	/**
	 * Constructor for the ExtensionBattleshipGame 
	 * @param playerOne the first player of the game 
	 * @param playerTwo the second player of the game
	 */
	public ExtensionBattleshipGame(Player playerOne, Player playerTwo, int width, int height){
		//Call parents constructor to create a window		
		super("Battleships", 0, (width * 2 + 2), height, 20, true);
		
		//set instance variables
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		currentPlayer = this.playerOne;
		currentOpponent = this.playerTwo;
		playerDrawer = new PlayerDrawer(height,width,2,this.playerOne,this.playerTwo);

		//Get the ball rolling (make the update method start occurring)
		this.start();
	}
	
		
	/**
	 * Handles the shooting from one player to the other
	 * @param currentPlayer The player who's turn it is
	 * @param currentOpponent The player who is being shot at
	 */
	public void handleShoot(Player currentPlayer, Player currentOpponent){
		//In a real game of battleships, the opponent will tell you if it is a hit or a miss
		System.out.print(currentOpponent.getName()+": ");
		
		Point pointShotAt = null;
		
		//Check if the current player is a person or a Computer
		if(currentPlayer instanceof ComputerPlayer){
			pointShotAt = ((ComputerPlayer) currentPlayer).takeTurn();
		}
		else if (currentPlayer instanceof HumanPlayer){
			pointShotAt = ((HumanPlayer)currentPlayer).getCursorPosition();
			
		}
		
		//"If that player has already shot at the position they request to shoot at this time, the method should do nothing and exit." - The spec.
		
		//Makes sure this place hasn't been hit before		
		if(currentPlayer.shotBefore(pointShotAt)) {
			//Does nothing and exits; Seems reasonable to let the player know that it was shot at before.
			System.out.println("This square has been shot at before");
			return;
		}
		else{
			if(currentOpponent.shotAt(pointShotAt)){
				currentPlayer.hitBattleship(pointShotAt);
				//Current Player stays the same
			}
			else{
				currentPlayer.hitNothing(pointShotAt);
				//Switch current player
				if(this.currentPlayer == playerOne){ 
					this.currentPlayer = playerTwo; 
					this.currentOpponent = playerOne;
					}
				else if(this.currentPlayer == playerTwo){ 
					this.currentPlayer = playerOne; 
					this.currentOpponent = playerTwo;
					}
			}
			
		}
		//Check to see if game is over
		if (currentOpponent.allShipsDestroyed()){
			System.out.println("All ships have been destroyed!");
			this.dispose();
		}
	}
	
	
	/**
	 * Checks if the it is the computer's turn and if it is calls the handleShoot method
	 */
	public void computerTakeTurn() {
		if (currentPlayer instanceof ComputerPlayer){
			handleShoot(currentPlayer, currentOpponent);
		}
	}
	
	
	@Override
	//Method used when there are buttons on screen,
	//Eclipse likes this to be here
	protected void buttonPressed(int arg0) {		
	}

	
	/**
	 * Handles the keyboard input from the user 
	 * @param arg0 The key that triggered this method
	 */
	@Override
	protected void keyPressed(Key arg0) {
				
		if(playerOne instanceof HumanPlayer){
			if(saveMode != true){
				if (arg0.getKeyChar() == 37) ((HumanPlayer)playerOne).moveLeft(); //Left arrow key
				if (arg0.getKeyChar() == 38) ((HumanPlayer)playerOne).moveUp();   //Up arrow key
				if (arg0.getKeyChar() == 39) ((HumanPlayer)playerOne).moveRight();//Right arrow key
				if (arg0.getKeyChar() == 40) ((HumanPlayer)playerOne).moveDown(); //Down arrow key
			
				if(arg0.getKeyChar() == 83) saveGame(playerOne, playerTwo); //S key
			
				if (arg0.getKeyChar() == 32){ //Space bar
					if(((HumanPlayer)playerOne).isPlacementMode()){
						((HumanPlayer)playerOne).placeCurrentBattleship();
					}
					else if (currentPlayer == playerOne) handleShoot(currentPlayer, currentOpponent);
				
				}
			
				if(arg0.getKeyChar() == 82){ //R key
					if(((HumanPlayer)playerOne).isPlacementMode()){
						((HumanPlayer)playerOne).rotateBattleship();
					}
				}
			}
		}
	}
	
	@Override
	//Method used when key release is important
	//Eclipse likes this to be here
	protected void keyReleased(Key arg0) {
	}

	/**
	 * Called every 0.1 seconds, ensures the computer takes its go, and draws to the grid 
	 */
	@Override
	protected void update() {
		computerTakeTurn();
		draw(playerDrawer);
	}

}
