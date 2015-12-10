package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import main.Game;
import javax.swing.JTextField;

public class StatsDisplay {

	public JFrame frame;
	private JTextField textKills;
	private JLabel lblDamageDealt;
	private JLabel lblDamageTaken;
	private JTextField textDamageDealt;
	private JTextField textDamageTaken;
	
	public void updateDisplay(Game game){
		textKills.setText(game.player.kills+"");
		textDamageDealt.setText(game.player.damageDealt+"");
		textDamageTaken.setText(game.player.damageTaken+"");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsDisplay window = new StatsDisplay();
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
	public StatsDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayerKills = new JLabel("Player Kills");
		lblPlayerKills.setBounds(12, 13, 80, 16);
		frame.getContentPane().add(lblPlayerKills);
		
		textKills = new JTextField();
		textKills.setBounds(12, 30, 80, 22);
		frame.getContentPane().add(textKills);
		textKills.setColumns(10);
		
		lblDamageDealt = new JLabel("Damage Dealt");
		lblDamageDealt.setBounds(12, 65, 80, 16);
		frame.getContentPane().add(lblDamageDealt);
		
		lblDamageTaken = new JLabel("Damage Taken");
		lblDamageTaken.setBounds(12, 116, 96, 16);
		frame.getContentPane().add(lblDamageTaken);
		
		textDamageDealt = new JTextField();
		textDamageDealt.setColumns(10);
		textDamageDealt.setBounds(12, 81, 80, 22);
		frame.getContentPane().add(textDamageDealt);
		
		textDamageTaken = new JTextField();
		textDamageTaken.setColumns(10);
		textDamageTaken.setBounds(12, 134, 80, 22);
		frame.getContentPane().add(textDamageTaken);
	}
}
