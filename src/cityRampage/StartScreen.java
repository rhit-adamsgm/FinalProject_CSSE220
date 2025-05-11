package cityRampage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.BorderLayout;


public class StartScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JPanel textPanel;
	private final JLabel label;
	private final JTextField input;
	private final JPanel buttonPanel;
	private final JButton startButton;
	private String returnedUsername = "";
	private Runnable onCloseCallback;
	
	public StartScreen(Runnable onCloseCallback) {
		this.onCloseCallback = onCloseCallback;

		//enter username panel
		textPanel = new JPanel();
		label = new JLabel("Enter Your Emporer's Name");
		input  = new JTextField("");
		input.setEditable(true);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(label, BorderLayout.NORTH);
		textPanel.add(input, BorderLayout.SOUTH);
		
		//start game panel
		buttonPanel = new JPanel();
		startButton = new JButton("Start City Rampage");
		startButton.addActionListener(e -> startGame());
		buttonPanel.add(startButton);
		
		//add the panels
		add(textPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//visuals of the frame
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * This method is what happens when the startGame JButton is pressed.
	 * <br>It saves the entered username, closes the screen, and runs the
	 * <br>given onCloseCallback method that the owner class gave.
	 */
	private void startGame() {
		if (!input.getText().trim().equals("")) {
			returnedUsername = input.getText().trim();
			dispose();
			onCloseCallback.run();
		}
	}
	
	/**
	 * This method is a getter for the entered username in this screen
	 * @return returnedUsername - the entered username
	 */
	public String getReturnedUsername() {
		return returnedUsername;
	}
}
