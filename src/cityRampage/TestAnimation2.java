package cityRampage;
 
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
 
public class TestAnimation2 {
 
    public static void main(String[] args) {
    	ArrayList<TestEnemy> enemies = new ArrayList<>();
    	for (int i = 0; i < 3; i++) {
    		TestEnemy enemy = new TestEnemy();
    		enemy.xpos = 500 + i * 300;
    		enemies.add(enemy);
    	}
        JFrame frame = new JFrame("Enemy Animation Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
 
        Emperor emperor = new Emperor();
        emperor.imageinit();
        AnimationPanel panel = new AnimationPanel(enemies, emperor);
        frame.add(panel);
        frame.setVisible(true);
        
        HashMap<TestEnemy, Long> lastHealthTick = new HashMap<>();
 
        // Timer to update the enemy and repaint the panel
		Timer timer = new Timer(100, e -> {
			for (TestEnemy enemy : enemies) {
				if (enemy.xpos > 80) {
					enemy.update();
				} else {
					long now = System.currentTimeMillis();
					long lastTick = lastHealthTick.getOrDefault(enemy, 0L);
					if (now - lastTick >= 1000) {
						emperor.reduceHealth(10);
						lastHealthTick.put(enemy, now);
					}
				}
			}
			panel.updateHealthBar();
			panel.repaint();
		});
		timer.start();
	}
}
 
// Concrete Enemy implementation
class TestEnemy extends Enemy {
    private double speed = 20.0; // Movement speed for enemy
    public int direction = -1;  // Start moving left
    
    public TestEnemy() {
        super();
        try {
            image1 = ImageIO.read(new File("src/images/enemy_frame1.png"));
            image2 = ImageIO.read(new File("src/images/enemy_frame2.png"));
            image3 = ImageIO.read(new File("src/images/enemy_frame3.png"));
            image4 = ImageIO.read(new File("src/images/enemy_frame4.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        xpos = 550;  // Start from right side
        ypos = 200;
    }
    
    @Override
    public void update() {
        // Update position first
        xpos += speed * direction;
        
        // Reset to right side when it goes off the left edge
        if (xpos < -50) {  
            xpos = 600;    // Reset to right side of screen
        }
        
        // Always animate when moving
        super.update();
    }

	@Override
	public void imageInit() {
		// TODO Auto-generated method stub
		
	}
}
 
// Panel to draw the enemy
class AnimationPanel extends JPanel {
    private ArrayList<TestEnemy> enemies;
    private Emperor emperor;
    private JProgressBar healthBar;
 
    public AnimationPanel(ArrayList<TestEnemy> enemies, Emperor emperor) {
        this.enemies = enemies;
        this.emperor = emperor;
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        
        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(emperor.getHealth());
        healthBar.setStringPainted(true);
        healthBar.setBounds(10, 10, 200, 25);
        add(healthBar);
    }
    
    public void updateHealthBar() {
    	healthBar.setValue(emperor.getHealth());
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(emperor.emperor != null) {
        	g.drawImage(emperor.emperor, 0, 150, 125, 200, null);
        }
        
        for (TestEnemy enemy : enemies) {
            if (enemy.image != null) {
                Graphics2D g2d = (Graphics2D) g;
                if (enemy.direction == 1) {
                    g2d.drawImage(enemy.image, (int) enemy.xpos + enemy.image.getWidth(), (int) enemy.ypos,
                            -enemy.image.getWidth(), enemy.image.getHeight(), null);
                } else {
                    g2d.drawImage(enemy.image, (int) enemy.xpos, (int) enemy.ypos, null);
                }
            }
        }
    }
}