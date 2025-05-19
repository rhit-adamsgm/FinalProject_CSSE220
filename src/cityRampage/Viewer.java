package cityRampage;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class: Viewer
 * @author Kobe Stoudemire
 * <br>Purpose: Used to create frame that the map background sits on
 *  example: Viewer viewer = new Viewer();
 */
public class Viewer {
	
	public Viewer() {
		SwingUtilities.invokeLater(()-> createGUI());
	}
	
	/**
	 * ensures: frame is set to proper dimensions and is visible to the user
	 */
	private static void createGUI() {
		double aspectRatio = 2.5;
		int xWidth = 850;
		JFrame frame = new JFrame("Map");
		frame.setSize((int) aspectRatio*xWidth,xWidth);
		frame.add(new MapPanel());
		frame.setPreferredSize(new Dimension((int) aspectRatio*xWidth,xWidth));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
}
