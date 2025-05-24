package cityRampage;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class: Viewer
 * @author Kobe Stoudemire
 * <br>Purpose: Used to create frame that the map background sits on
 *  example: Viewer viewer = new Viewer();
 */
public class MapViewer {
	Model model;
	double aspectRatio;
	JFrame frame;
	Runnable onCloseCallback;
	City selectedCity = null;
	private ArrayList<City> cityArray = new ArrayList<>();
	MapPanel mapPanel;
	
	public MapViewer(Model model, Runnable onCloseCallback, ArrayList<City> cities) {
		this.model = model;
		this.onCloseCallback = onCloseCallback;
		this.cityArray = cities;
		
		int xWidth = 850;
		aspectRatio = 2.5;
		frame = new JFrame("Map");
		frame.setSize((int) aspectRatio*xWidth,xWidth);
		mapPanel = new MapPanel(() -> onConquerButtonPressed(), cityArray);
		frame.add(mapPanel);
		frame.setPreferredSize(new Dimension((int) aspectRatio*xWidth,xWidth));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
	
	private void onConquerButtonPressed() {
		frame.dispose();
		setSelectedCity();
		onCloseCallback.run();
	}
	
	private void setSelectedCity() {
		selectedCity = mapPanel.getSelectedCity();
	}
	
	public City getSelectedCity() {
		return selectedCity;
	}
}
