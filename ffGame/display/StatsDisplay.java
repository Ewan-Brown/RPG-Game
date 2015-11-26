package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import main.Game;

public class StatsDisplay {

	public JFrame frame;
	JLabel lblWeaponSpawn;
	JLabel lblMissChance;
	JLabel lblTotalMiss;
	JLabel lblPlayer;
	JLabel lblMonsters;
	JTextPane textWeaponSpawn;
	JTextPane textMissChance;
	JTextPane textMissPlayer;
	JTextPane textMissMonster;
	
	public void updateDisplay(Game game){
		textWeaponSpawn.setText(game.getWeaponSpawn()+"");
		//TODO MISS GETTER AND COUNTER
		textMissChance.setText(game.getMissChance()+"");
		textMissPlayer.setText(game.getPMisses()+" %");
		textMissMonster.setText(game.getMMisses()+" %");
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
		
		lblWeaponSpawn = new JLabel("Weapon Spawn %");
		lblWeaponSpawn.setBounds(12, 13, 106, 16);
		frame.getContentPane().add(lblWeaponSpawn);
		
		lblMissChance = new JLabel("Miss Chance");
		lblMissChance.setBounds(12, 65, 106, 16);
		frame.getContentPane().add(lblMissChance);
		
		lblTotalMiss = new JLabel("Actual Miss #");
		lblTotalMiss.setBounds(12, 117, 106, 16);
		frame.getContentPane().add(lblTotalMiss);
		
		lblPlayer = new JLabel("Player");
		lblPlayer.setBounds(12, 141, 106, 16);
		frame.getContentPane().add(lblPlayer);
		
		lblMonsters = new JLabel("Monsters");
		lblMonsters.setBounds(12, 199, 106, 16);
		frame.getContentPane().add(lblMonsters);
		
		textWeaponSpawn = new JTextPane();
		textWeaponSpawn.setBounds(12, 30, 106, 22);
		frame.getContentPane().add(textWeaponSpawn);
		
		textMissChance = new JTextPane();
		textMissChance.setBounds(12, 82, 106, 22);
		frame.getContentPane().add(textMissChance);
		
		textMissPlayer = new JTextPane();
		textMissPlayer.setBounds(12, 160, 106, 22);
		frame.getContentPane().add(textMissPlayer);
		
		textMissMonster = new JTextPane();
		textMissMonster.setBounds(11, 218, 106, 22);
		frame.getContentPane().add(textMissMonster);
	}
}
