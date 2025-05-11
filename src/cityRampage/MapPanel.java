package cityRampage;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.geom.AffineTransform;


public class MapPanel extends JPanel {
	BufferedImage normalCity;
	BufferedImage damagedCity;
	BufferedImage usMap;
	List<City> cityMarkers = new ArrayList<>();

	
	
	public MapPanel() {
		JPanel panel = new JPanel();
		setPreferredSize(new Dimension(200,500));
		try {
			normalCity = ImageIO.read(new File("src/images/Map City Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			normalCity = null;
		}
		
		try {
			damagedCity = ImageIO.read(new File("src/images/Map City Smashed Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			damagedCity = null;
		}
		
		try {
			usMap = ImageIO.read(new File("src/images/Map Background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			usMap = null;
		}
		
		cityMarkers.add(new City())
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		if (usMap != null) {
			double scaleX = (double) getWidth() / usMap.getWidth();
			double scaleY = (double) getHeight() / usMap.getHeight();

			// Draw background scaled to fit the panel
			AffineTransform bgTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
			g2d.drawImage(usMap, bgTransform, this);

			// Set base coordinates for the normalCity
			int originalX = 150;
			int originalY = 100;
			int originalWidth = 100;
			int originalHeight = 100;

			// Apply the same scaling to position and size
			double cityScaler = 0.5;
			double x = originalX * scaleX;
			double y = originalY * scaleY;
			double width = (originalWidth * scaleX) *cityScaler;
			double height = (originalHeight * scaleY) * cityScaler;

			// Scale and position the normalCity image accordingly
			AffineTransform cityTransform = new AffineTransform();
			cityTransform.translate(x, y);
			cityTransform.scale(width / normalCity.getWidth(), height / normalCity.getHeight());

			g2d.drawImage(normalCity, cityTransform, this);
		} else {
			g2d.setColor(Color.RED);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			g2d.setColor(Color.MAGENTA);
			g2d.drawString("Background not Found", 50, 50);
		}
		g2d.dispose();
	}
}
