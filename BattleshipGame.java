import java.awt.Point;


public class BattleshipGame extends WindowController{
	private static final long serialVersionUID = 105;
	
	//Instance variables 
	PlayerDrawer playerDrawer;
	Player playerOne, playerTwo, currentPlayer, currentOpponent;
	
	
	/**
	 * The main method. Creates an instance of the class.
	 */
	public static void main(String[] args){		
		//Starts up the game with a Human player and an Intelligent Computer player
		@SuppressWarnings("unused") //The variable b never needs to be "Used"
		BattleshipGame b = new BattleshipGame(new HumanPlayer("Human", 15,15), new IntelligentComputerPlayer("Computer", 15,15) );
	}
		
	
	/**
	 * Constructor for the BattleshipGame class
	 * @param playerOne the first player of the game 
	 * @param playerTwo the second player of the game
	 */
	public BattleshipGame(Player playerOne, Player playerTwo){
		//Call parents constructor to create a window		
		super("Battleships", 0, 32, 15, 20, true);
		
		//set instance variables
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		currentPlayer = this.playerOne;
		currentOpponent = this.playerTwo;
		playerDrawer = new PlayerDrawer(15,15,2,this.playerOne,this.playerTwo);

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
			System.exit(0);
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
			if (arg0.getKeyChar() == 37) ((HumanPlayer)playerOne).moveLeft(); //Left arrow key
			if (arg0.getKeyChar() == 38) ((HumanPlayer)playerOne).moveUp();   //Up arrow key
			if (arg0.getKeyChar() == 39) ((HumanPlayer)playerOne).moveRight();//Right arrow key
			if (arg0.getKeyChar() == 40) ((HumanPlayer)playerOne).moveDown(); //Down arrow key
			
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
