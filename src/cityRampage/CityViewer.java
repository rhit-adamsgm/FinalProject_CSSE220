package cityRampage;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Class: CityView
 * @author Kobe Stoudemire
 * <br>Purpose: Used to show the background after conquer button is pressed
 */
public class CityViewer {
	private JFrame frame;
	private CityPanel cityPanel;
	private InfoPanel infoPanel;
	private City selectedCity;
	
	

	private int frameWidth, frameHeight;
	private int infoPHeight, cityPHeight;
	private Runnable timerCallback;
	
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
			setPreferredSize(new Dimension(frameWidth, infoPHeight));
			
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
	public CityViewer(City city) {
		this.selectedCity = city;
		
		//store the size of the panels
		frameWidth = 1200;
		frameHeight = 800;
		infoPHeight = 80;
		cityPHeight = frameHeight - infoPHeight;
		
		//give the city the width and height of the frame it is in
		selectedCity.setFrameSize(frameWidth, cityPHeight);
		
		//Connect city to cityviewer
		selectedCity.setCityViewer(this);
		
		//new frame that will hold next part of game
		frame = new JFrame("City: " + selectedCity.getname());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(new BorderLayout());
		
		cityPanel = new CityPanel(selectedCity);
		frame.add(cityPanel, BorderLayout.CENTER);
		
		infoPanel = new InfoPanel(selectedCity);
		frame.add(infoPanel, BorderLayout.SOUTH);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//set the timer call back function
		selectedCity.setTimerCallback(() -> viewerUpdate());
		
		//set the key listener that will route to the mech
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KeyEvent Registered in CityViewer");
				selectedCity.passKeyPress2Mech(e);
			}
		});
		
		 
		//start the game play
		selectedCity.startRaid();
		
	}
	
	/**
	 * Is what is called every time the timer triggers - makes the screen update and requests focus
	 */
	public void viewerUpdate() {
		cityPanel.repaint();
		frame.requestFocusInWindow();
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
		private Graphics2D g2;
		
		public CityPanel(City city) {
			this.city = city;
			backgroundImage = city.getImage();
			setPreferredSize(new Dimension(frameWidth, cityPHeight));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g2 = (Graphics2D) g;
			
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
			
			city.draw(g2);
		}
	}
}
