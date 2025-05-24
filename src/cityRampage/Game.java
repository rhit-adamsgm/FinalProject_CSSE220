package cityRampage;

public class Game {
	//fields----------------------------------------//
		//fields for startup------------------------//
	private String username;
	private StartScreen startScreen;
	private Viewer viewer;
	private Model model;
	private Controller controller;
    private int gameState;  // example stat
	//----------------------------------------------//

	public static void main(String[] args) {
		new Game();
	}
	
	/**
	 * This is basically a main, but callable from within the game. It will restart the game.
	 */
	public Game() {
		model = new Model();
		controller = new Controller(model, viewer);
		//Starts the game by opening the start screen
		startScreen = new StartScreen(() -> onStartScreenClose());
	}
	
	/**
	 * This pushes the code to the true start of the game after the startup screen is closed
	 */
	private void onStartScreenClose() {
		username = startScreen.getReturnedUsername();
		viewer = new Viewer(model);
	}

}
