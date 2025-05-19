package cityRampage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.geom.AffineTransform;

/**
 * Class: MapPanel
 * @author Kobe Stoudemire
 * <br>Purpose: Used for panels, buttons, and labels that are added to the frame initialized in Viewer
 */
public class MapPanel extends JPanel {
	// BufferedImage normalCity;
	private JLabel label;
	private JButton conquer;
	private JPanel bottomPanel;
	private JButton cityButton;
	BufferedImage damagedCity;
	private BufferedImage usMap;
	private JButton imageButton;
	private ArrayList<JButton> cityButtons = new ArrayList<>();
	private City chicago = new City(1000, 300, "Chiraq", "King Von Lurks");
	private City denver = new City(550, 325, "Denver", "The Hazy City");
	private City atl = new City(1150, 500, "Atlanta", "Hawks in 5");
	private City seattle = new City(250, 50, "Seattle", "The Emerald City");
	private City newyork = new City(1350, 245, "New York", "The Big Apple");
	private City kansascity = new City(750, 550, "Kansas City", "Home of the Chiefs");
	private ArrayList<City> cityArray = new ArrayList<>();
	private City selectedCity = null;
	private Runnable onConquerCallback;

	private String cityName;
	private String cityNickname;
	private final int cityX = 300;
	private final int cityY = 350;

	/**
	 * ensures: That the buttons show up on the map, bottom panel appears after button clicked,
	 * and once other button is clicked that the frame is disposed
	 * 
	 */
	public MapPanel() {
		setPreferredSize(new Dimension(200,500));
		setLayout(new BorderLayout());
		
		
		// Add cities to the array
		cityArray.add(denver);
		cityArray.add(chicago);
		cityArray.add(atl);
		cityArray.add(seattle);
		cityArray.add(newyork);
		cityArray.add(kansascity);

		//New Panel for bottom of screen
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setPreferredSize(new Dimension(getWidth(), 40));

		//Label for bottom panel
		label = new JLabel(" ", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 25));
		label.setOpaque(false);
		label.setForeground(Color.BLACK);
		
		//button for next part of game
		conquer = new JButton("Conquer");
		conquer.setFocusPainted(false);
		conquer.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		
		//add the new panel and label
		bottomPanel.add(label, BorderLayout.CENTER);
		bottomPanel.add(conquer, BorderLayout.EAST);
		
		
		
		//Map image
		try {
			usMap = ImageIO.read(new File("src/images/Map Background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			usMap = null;
		}
		
		//Damaged city image
		try {
			damagedCity = ImageIO.read(new File("src/images/Map City Smashed Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			damagedCity = null;
		}
		
		// Conquer button action listener
		conquer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedCity != null) {
					System.out.println("Conquering " + selectedCity.getname() + "!");
					
					new CityView(selectedCity);

				// Get the parent frame and dispose it
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MapPanel.this);
					if (frame != null) {
						frame.dispose();
					}
				} else {
					System.out.println("No city selected to conquer!");
					label.setText("Please select a city first!");
				}
			}
		});
		

		
		//City button
		ImageIcon originalIcon = new ImageIcon("src/images/Map City Icon.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(originalIcon.getIconWidth()/12, originalIcon.getIconHeight()/12, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
//		imageButton = new JButton(scaledIcon);
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
//		imageButton.setOpaque(false);
		
		
		//iterate through array of cities and set each image to independent button
		for (City city : cityArray) {
			int x = city.getXMapCoord();
			int y = city.getYMapCoord();
			
			
			JButton cityButton = new JButton(scaledIcon);
			cityButton.setBorderPainted(false);
			cityButton.setContentAreaFilled(false);
			cityButton.setFocusPainted(false);
			cityButton.setOpaque(false);
			cityButton.setBounds(x, y, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
			
			
			// ActionListener for individual buttons
			cityButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedCity = city;
					label.setText(city.getname() + ": " + city.getNickname());
					label.setFont(new Font("SansSerif", Font.BOLD, 25));
					label.setOpaque(true);
					label.setBackground(Color.WHITE);
					label.setPreferredSize(new Dimension(getWidth(), 40));
					add(bottomPanel, BorderLayout.SOUTH);
					revalidate();
					repaint();
				}
			});
			
			
			//Add buttons to the panel
			cityButtons.add(cityButton);
			add(cityButton);
		}
	}
	

//	private void positionButton() {
//		int panelWidth = getWidth();
//		int panelHeight = getHeight();
//
//		double scaleX = (double) panelWidth / 1200;
//		double scaleY = (double) panelHeight / 1000;
//
//		int newX = (int) (cityX * scaleX);
//		int newY = (int) (cityY * scaleY);
//
//		ImageIcon icon = (ImageIcon) imageButton.getIcon();
//		int iconWidth = icon.getIconWidth();
//		int iconHeight = icon.getIconHeight();
//
//		imageButton.setBounds(newX, newY, iconWidth, iconHeight);
//	}

	
	// Set the map as the background
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (usMap != null) {
			g.drawImage(usMap, 0, 0, getWidth(), getHeight(), this);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.MAGENTA);
			g.drawString("Background not Found", 50, 50);
		}
	}
}
