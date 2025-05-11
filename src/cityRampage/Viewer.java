package cityRampage;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Viewer {
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(()-> createGUI());
	}
	
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
