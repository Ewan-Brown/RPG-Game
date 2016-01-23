package display;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import main.Game;
import javax.swing.JToggleButton;

public class DisplayDylan implements ActionListener{

	public JFrame frame;
	Game game;
	public JToggleButton tglbtnDylan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayDylan window = new DisplayDylan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayDylan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 616, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tglbtnDylan = new JToggleButton("Dylan");
		frame.getContentPane().add(tglbtnDylan, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
