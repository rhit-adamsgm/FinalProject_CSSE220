package cityRampage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.BorderLayout;


public class StartScreen extends JFrame {
	private final JPanel textPanel;
	private final JLabel label;
	private final JTextField input;
	private final JPanel buttonPanel;
	private final JButton startButton;
	
	public StartScreen() {
		//enter username panel
		textPanel = new JPanel();
		label = new JLabel("Enter Your Emporer's Name");
		input  = new JTextField("");
		input.setEditable(true);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(label, BorderLayout.NORTH);
		textPanel.add(input, BorderLayout.SOUTH);
		
		
		
		buttonPanel = new JPanel();
		startButton = new JButton();
		
		
	}
}
