package cityRampage;

public class Game {
	//fields----------------------------------------//
		//fields for startup------------------------//
	private static String username;
	private static StartScreen startScreen;
	//----------------------------------------------//

	public static void main(String[] args) {
		startUp();
	}
	
	/**
	 * This is basically a main, but callable from within the game. It will restart the game.
	 */
	public static void startUp() {
		startScreen = new StartScreen(() -> onStartScreenClose());
	}
	
	/**
	 * This pushes the code to the true start of the game after the startup screen is closed
	 */
	private static void onStartScreenClose() {
		username = startScreen.getReturnedUsername();
		System.out.println(username);//test code here
	}

}
