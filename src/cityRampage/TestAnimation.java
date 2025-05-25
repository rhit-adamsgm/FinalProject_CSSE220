package cityRampage;
 
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
 
public class TestAnimation {
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Enemy Animation Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
 
        TestEnemy enemy = new TestEnemy();
        AnimationPanel panel = new AnimationPanel(enemy);
        frame.add(panel);
        frame.setVisible(true);
 
        // Timer to update the enemy and repaint the panel every 100ms (faster for smoother movement)
        Timer timer = new Timer(100, e -> {
            enemy.update();
            panel.repaint();
        });
        timer.start();
    }
}
 
// Concrete Enemy implementation
class TestEnemy extends Enemy {
    private double speed = 5.0; // Movement speed in pixels per frame
    public int direction = 1;  // Start moving left (-1 for left, 1 for right)
    public TestEnemy() {
        super();
        xpos = 50;  // Start from right side
        ypos = 200;
    }
    public void imageInit() {
    	try {
            image1 = ImageIO.read(new File("src/images/enemy_frame1.png"));
            image2 = ImageIO.read(new File("src/images/enemy_frame2.png"));
            image3 = ImageIO.read(new File("src/images/enemy_frame3.png"));
            image4 = ImageIO.read(new File("src/images/enemy_frame4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        // Update position first
        xpos += speed * direction;
        // Reset to right side when it goes off the left edge
        if (xpos < -50) {  // Give some buffer for sprite width
            xpos = 600;    // Reset to right side of screen
        }
        // Always animate when moving
        super.update(); // Call parent update to handle frame cycling
    }
}
 
// Panel to draw the enemy
class AnimationPanel extends JPanel {
    private Enemy enemy;
 
    public AnimationPanel(Enemy enemy) {
        this.enemy = enemy;
        setBackground(Color.LIGHT_GRAY); // Optional: set background color
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (enemy.image != null) {
            // Optional: flip the image based on direction if you want
            Graphics2D g2d = (Graphics2D) g;
            if (((TestEnemy) enemy).direction == -1) {
                // Flip horizontally for left movement
                g2d.drawImage(enemy.image, 
                    (int) enemy.xpos + enemy.image.getWidth(), (int) enemy.ypos,
                    -enemy.image.getWidth(), enemy.image.getHeight(), null);
            } else {
                // Normal drawing for right movement
            	enemy.draw(g2d);
                //g2d.drawImage(enemy.image, (int) enemy.xpos, (int) enemy.ypos, null);
            }
        }
    }
}