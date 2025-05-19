package cityRampage;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Class: CityView
 * @author Kobe Stoudemire
 * <br>Purpose: Used to show the background after conquer button is pressed
 */
public class CityView {
	private JFrame frame;
	private CityPanel cityPanel;
	private InfoPanel infoPanel;
	private City selectedCity;
	private static BufferedImage atlBack,denverBack, kcBack, newyorkBack, oblockBack, seattleBack;
	
	static {
		loadAllBackgrounds();
	}
	
	private class InfoPanel extends JPanel {
		private JLabel cityLabel;
		private JProgressBar healthBar;
		private JProgressBar progressBar;
		private JButton pauseButton;
		private City city;
		private boolean isPaused = false;
		
		private int currentHealth = 100;
		private int maxHealth = 100;
		private int currentProgress = 0;
		private int maxProgress = 100;
		
		public InfoPanel(City city) {
			this.city = city;
			initializeComponents();
			layoutComponent();
		}
		
		private void initializeComponents() {
			//current city label
			cityLabel = new JLabel("Current City: "+ city.getname());
			cityLabel.setFont(new Font("Arial", Font.BOLD, 16));
			cityLabel.setForeground(Color.BLACK);
			
			//health bar
			healthBar = new JProgressBar(0, maxHealth);
			healthBar.setValue(currentHealth);
			healthBar.setStringPainted(true);
			healthBar.setString("Health: " + currentHealth + "/" + maxHealth);
			healthBar.setForeground(getHealthColor(currentHealth, maxHealth));
			healthBar.setPreferredSize(new Dimension(200,25));
			
			//progress bar
			progressBar = new JProgressBar(0, maxProgress);
			progressBar.setValue(currentProgress);
			progressBar.setStringPainted(true);
			progressBar.setString("Progress: " + currentProgress + "%");
			progressBar.setForeground(new Color(0, 150, 0));
			progressBar.setPreferredSize(new Dimension(200, 25));
			
			//pause button
			pauseButton = new JButton("Pause");
			pauseButton.setPreferredSize(new Dimension(100,30));
			pauseButton.setFont(new Font("Arial", Font.BOLD, 14));
			pauseButton.addActionListener(e -> togglePause());
		}
		
		private void layoutComponent() {
			setLayout(new BorderLayout());
			setBackground(new Color(240, 240, 240));
			setBorder(BorderFactory.createRaisedBevelBorder());
			setPreferredSize(new Dimension(getWidth(), 80));
			
			JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			leftPanel.setBackground(new Color(240,240,240));
			leftPanel.add(cityLabel);
			
			JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			centerPanel.setBackground(new Color(240,240,240));
			
			JPanel healthPanel = new JPanel(new BorderLayout());
            healthPanel.add(new JLabel("Health", SwingConstants.CENTER), BorderLayout.NORTH);
            healthPanel.add(healthBar, BorderLayout.CENTER);
            
            JPanel progressPanel = new JPanel(new BorderLayout());
            progressPanel.add(new JLabel("Progress", SwingConstants.CENTER), BorderLayout.NORTH);
            progressPanel.add(progressBar, BorderLayout.CENTER);
            
            centerPanel.add(healthPanel);
            centerPanel.add(progressPanel);
            
            // Right side - pause button
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setBackground(new Color(240, 240, 240));
            rightPanel.add(pauseButton);
            
            add(leftPanel, BorderLayout.WEST);
            add(centerPanel, BorderLayout.CENTER);
            add(rightPanel, BorderLayout.EAST);
		}
		
		private Color getHealthColor(int current, int max) {
			double ratio = (double) current / max;
			if (ratio > 0.6) {
				return new Color(0,150,0); // Green
			} else if (ratio > 0.3) {
				return new Color(155,165,0); // Orange
			} else {
				return new Color(200, 0, 0); // Red
			}
		}
		
		private void togglePause() {
			isPaused = !isPaused;
			if (isPaused) {
				pauseButton.setText("Resume");
				pauseButton.setBackground(new Color(255,100,100));
				
				System.out.println("Game Paused");
			} else {
				pauseButton.setText("Pause");
				pauseButton.setBackground(null);
				System.out.println("Game Resumed");
			}
		}
		
		public void updateHealth(int newHealth) {
			currentHealth = Math.max(0, Math.min(newHealth, maxHealth));
			healthBar.setValue(currentHealth);
			healthBar.setString("Health: " + currentHealth + "/" + maxHealth);
			healthBar.setForeground(getHealthColor(currentHealth, maxHealth));
		}
		
		public void updateProgress(int newProgress) {
			currentProgress = Math.max(0, Math.min(newProgress, maxProgress));
			progressBar.setValue(currentProgress);
			progressBar.setString("Progress: " + currentProgress + "%");
		}
		
		public boolean isPaused() {
			return isPaused;
		}
	}
	
	/**
	 * ensures: The correct city is being displayed
	 * 
	 */
	public CityView(City city) {
		this.selectedCity = city;
		SwingUtilities.invokeLater(() -> createCityGUI());
	}
	
	/**
	 * ensures: All the background images for the different cities are available
	 * 
	 */
	private static void loadAllBackgrounds() {
		//atlanta background
		try {
			atlBack = ImageIO.read(new File("src/images/atlanta_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			atlBack = null;
		}
		
		//denver background
		try {
			denverBack = ImageIO.read(new File("src/images/denver_background.png"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			denverBack = null;
		}
		
		//kansas city background
		try {
			kcBack = ImageIO.read(new File("src/images/kansascity_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			kcBack = null;
		}
		
		//new york background
		try {
			newyorkBack = ImageIO.read(new File("src/images/new-york.jpeg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			newyorkBack = null;
		}
		
		//chicago background
		try {
			oblockBack = ImageIO.read(new File("src/images/oblock_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			oblockBack = null;
		}
		
		//seattle background
		try {
			seattleBack = ImageIO.read(new File("src/images/seattle-streets-21.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			seattleBack = null;
		}
	}
	
	/**
	 * ensures: A new frame with the selected city will be initialized once the conquer button is pressed
	 * 
	 */
	private void createCityGUI() {
		//new frame that will hold next part of game
		frame = new JFrame("City: " + selectedCity.getname());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setLayout(new BorderLayout());
		
		cityPanel = new CityPanel(selectedCity);
		frame.add(cityPanel, BorderLayout.CENTER);
		
		infoPanel = new InfoPanel(selectedCity);
		frame.add(infoPanel, BorderLayout.SOUTH);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		}
	
	public void updateHealth(int newHealth) {
		if (infoPanel != null) {
			infoPanel.updateHealth(newHealth);
		}
	}
	
	public void updateProgress(int newProgress) {
		if (infoPanel != null) {
			infoPanel.updateProgress(newProgress);
		}
	}
	
	public boolean isPaused() {
		return infoPanel != null && infoPanel.isPaused();
	}

	
	/**
	 * ensures: Background image corresponds to the correct city
	 */
	private class CityPanel extends JPanel {
		private BufferedImage backgroundImage;
		private City city;
		
		public CityPanel(City city) {
			this.city = city;
			backgroundImage = getBackgroundForCity(city.getname());
			setPreferredSize(new Dimension(1200, 800));
		}
		
		private BufferedImage getBackgroundForCity(String cityName) {
            // Return the pre-loaded image based on city name
            String name = cityName.toLowerCase();
            
            if (name.equals("chiraq") || name.equals("chicago")) {
                return oblockBack;
            } else if (name.equals("denver")) {
                return denverBack;
            } else if (name.equals("atlanta")) {
                return atlBack;
            } else if (name.equals("seattle")) {
                return seattleBack;
            } else if (name.equals("new york")) {
                return newyorkBack;
            } else if (name.equals("kansas city")) {
                return kcBack;
            } else {
                return null; 
            }
        }
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}
}
