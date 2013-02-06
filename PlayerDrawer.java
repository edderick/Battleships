import java.awt.Point;


public class PlayerDrawer extends DrawableObject{

	//Instance variables
	private DrawablePlayerInterface player, opponent;
	private int width, height, centreColumnWidth;
	
	
	/**
	 * Constructor for the IntelligentComputerPlayer class
	 * @param name The name of the player
	 * @param width The width of the player's grid
	 * @param height This height of the player's grid
	 */
	public PlayerDrawer(int width, int height, int centreColumnWidth, Player player, Player opponent){
		//Calls the constructor for DrawableObject
		super();
		
		//Set instance variables
		this.width = width;
		this.height = height;
		this.centreColumnWidth = centreColumnWidth;
		this.player = player;
		this.opponent = opponent;
	}
	
	
	/**
	 * Colours the grid square in the left grid the given colour
	 * @param position The point of the grid square to be coloured
	 * @param colour The colour to colour the grid square
	 * @param gc The graphics controller to draw to
	 */
	public void drawLeftGrid(Point position, int colour, GraphicsController gc){
		gc.setGridColour(position.x, position.y, colour);
	}
	
	
	/**
	 * Colours the grid square in the right grid the given colour
	 * @param position The point of the grid square to be coloured
	 * @param colour The colour to colour the grid square
	 * @param gc The graphics controller to draw to
	 */
	public void drawRightGrid(Point position, int colour, GraphicsController gc){
		//Offset by the width and Central column
		gc.setGridColour(position.x + width + centreColumnWidth, position.y, colour);
	}
	
	
	/**
	 * Colours the grid square inbetween the two girds
	 * @param colour The colour to colour the grid squares
	 * @param gc The graphics controller to draw to
	 */
	public void setCentralColumn(int colour, GraphicsController gc){
		for(int i = width; i < centreColumnWidth+width; i ++){
			for(int j = 0; j < height; j++){
				gc.setGridColour(i, j, colour);
			}
		}
	}
	
	
	@Override
	/**
	 * Draws the various elements of the game to the grid
	 * @param gc The graphics controller to draw to
	 */
	public void draw(GraphicsController gc) {
		//Fill the whole grid black
		for(int i = 0; i< width; i++){
			for(int j = 0; j< height; j++){
				drawLeftGrid(new Point(i,j), GraphicsController.BLACK , gc);
				drawRightGrid(new Point(i,j), GraphicsController.BLACK , gc);
			}
		}
		
		//Fill the central column
		setCentralColumn(GraphicsController.WHITE, gc);
		
		//Fill the left grid with the places that the player has shot at
		for(GridSquare g: player.getPlacesShotAt()){
			if(g.getIsShip()) drawLeftGrid(g.getPosition(), GraphicsController.RED, gc);
			else drawLeftGrid(g.getPosition(), GraphicsController.BLUE, gc);	
		}
		
		//Fill the right grid with the players own battleships
		for(Battleship b: player.getBattleships()){
			for(Point p: b.getLocations()){
				drawRightGrid(p, GraphicsController.GREEN, gc);
			}
		}
		
		//Fill the right grid with the shots the opponent has made
		for(GridSquare g: opponent.getPlacesShotAt()){
			if(g.getIsShip()) drawRightGrid(g.getPosition(), GraphicsController.RED, gc);
			else drawRightGrid(g.getPosition(), GraphicsController.BLUE, gc);	
		}
		
		//Display the cursor in the left grid
		if(player instanceof HumanPlayer){
			drawLeftGrid(((HumanPlayer) player).getCursorPosition(), GraphicsController.YELLOW, gc);
		}
	}
	
	
}
