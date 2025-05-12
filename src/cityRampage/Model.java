package cityRampage;

import javax.swing.Timer;

public class Model {
	
	private Timer baseUnitTimer;
	
	public Model() {
		int baseDelay = 64; //64 milliseconds will be the base frame rate of our game
		baseUnitTimer = new Timer(baseDelay, e -> handleBaseFrame());
		baseUnitTimer.start();
		
	}

	private void handleBaseFrame() {
		
	}

}
