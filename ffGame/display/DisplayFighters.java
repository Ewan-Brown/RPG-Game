package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import entities.EntityMonster;
import entities.EntityPlayer;
import main.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class DisplayFighters implements ActionListener{

	public JFrame frame;
	public JLabel lblPlayer;
	public JTextPane txtHealth;
	public JTextPane txtName;
	public JTextPane txtDamage;
	public JLabel lblWeapon;
	public JTextPane txtWeaponName;
	public JTextPane txtWeaponDamage;
	public JLabel lblMonster;
	public JTextPane txtMonsterName;
	public JTextPane txtMonsterHealth;
	public JTextPane txtMonsterDamage;
	public JLabel lblMonsterWeapon;
	public JTextPane txtMonsterWeaponName;
	public JButton btnPause;
	public JProgressBar barPlayerHealth;
	public JTextPane txtMonsterWeaponDamage;
	public JProgressBar barMonsterHealth;
	public JProgressBar barKills;
	
	public Game game = null;
	private JProgressBar barExperience;
	private JTextField txtPlayerIsPoisoned;
	private JTextField txtMonsterIsPoisoned;
	private JTextField txtLevel;

	/**
	 * Launch the application.
	 */
	
	public void updateDisplay(Game currentGame){
		this.game = currentGame;
		EntityMonster monster = game.monster;
		EntityPlayer player = game.player;
		barPlayerHealth.setMinimum(0);
		barPlayerHealth.setMaximum(player.getBaseHealth());
		barPlayerHealth.setValue(player.getHealth());
		barMonsterHealth.setMinimum(0);
		barMonsterHealth.setMaximum(monster.getBaseHealth());
		barMonsterHealth.setValue(monster.getHealth());
		barExperience.setMinimum(0);
		barExperience.setMaximum(player.EXP_PER_LEVEL);
		barExperience.setValue(player.getExp());
		barKills.setMinimum(0);
		barKills.setMaximum(currentGame.currentStage.getMonstersRequired());
		barKills.setValue(currentGame.currentStage.getMonstersKilled());
		txtName.setText(player.getName());
		txtHealth.setText(player.getHealth()+"");
		txtDamage.setText(player.getDamage()+"");
		if(!(player.getWeapon() == null)){
			txtWeaponName.setText(player.getWeapon().getName());
			txtWeaponDamage.setText(player.getWeaponDamage()+"");
		}
		txtMonsterName.setText(monster.getName());
		if(monster.isAlive()){
			txtMonsterHealth.setText(monster.getHealth()+"");
			txtMonsterDamage.setText(monster.getDamage()+"");	
		}
		if(!(monster.isAlive())){
			txtMonsterHealth.setText("*DEAD*");
			txtMonsterDamage.setText("*DEAD*");	
		}
		if(!(monster.getWeapon() == null)){
			txtMonsterWeaponName.setText(monster.getWeapon().getName());
			txtMonsterWeaponDamage.setText(monster.getWeaponDamage()+"");
		}

		txtPlayerIsPoisoned.setText(player.isPoisoned()+"");
		txtMonsterIsPoisoned.setText(monster.isPoisoned()+"");
		txtLevel.setText(player.getLevel()+"");
		
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayFighters window = new DisplayFighters();
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
	public DisplayFighters() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 829, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPlayer = new JLabel("PLAYER");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayer.setBounds(110, 25, 81, 22);
		frame.getContentPane().add(lblPlayer);
		
		txtHealth = new JTextPane();
		txtHealth.setEditable(false);
		txtHealth.setText("Health");
		txtHealth.setBounds(110, 101, 116, 22);
		frame.getContentPane().add(txtHealth);
		
		txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText("Name");
		txtName.setBounds(86, 66, 154, 22);
		frame.getContentPane().add(txtName);
		
		txtDamage = new JTextPane();
		txtDamage.setEditable(false);
		txtDamage.setText("Damage");
		txtDamage.setBounds(110, 136, 116, 22);
		frame.getContentPane().add(txtDamage);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setBounds(142, 298, 56, 16);
		frame.getContentPane().add(lblWeapon);
		
		txtWeaponName = new JTextPane();
		txtWeaponName.setEditable(false);
		txtWeaponName.setText("Name");
		txtWeaponName.setBounds(74, 313, 206, 22);
		frame.getContentPane().add(txtWeaponName);
		
		txtWeaponDamage = new JTextPane();
		txtWeaponDamage.setEditable(false);
		txtWeaponDamage.setText("Damage");
		txtWeaponDamage.setBounds(116, 348, 116, 22);
		frame.getContentPane().add(txtWeaponDamage);
		
		lblMonster = new JLabel("MONSTER");
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMonster.setBounds(599, 25, 81, 22);
		frame.getContentPane().add(lblMonster);
		
		txtMonsterName = new JTextPane();
		txtMonsterName.setText("MonsterName");
		txtMonsterName.setEditable(false);
		txtMonsterName.setBounds(560, 66, 146, 22);
		frame.getContentPane().add(txtMonsterName);
		
		txtMonsterHealth = new JTextPane();
		txtMonsterHealth.setText("Health");
		txtMonsterHealth.setEditable(false);
		txtMonsterHealth.setBounds(574, 101, 116, 22);
		frame.getContentPane().add(txtMonsterHealth);
		
		txtMonsterDamage = new JTextPane();
		txtMonsterDamage.setText("Damage");
		txtMonsterDamage.setEditable(false);
		txtMonsterDamage.setBounds(574, 136, 116, 22);
		frame.getContentPane().add(txtMonsterDamage);
		
		lblMonsterWeapon = new JLabel("Weapon");
		lblMonsterWeapon.setBounds(600, 298, 56, 16);
		frame.getContentPane().add(lblMonsterWeapon);
		
		txtMonsterWeaponName = new JTextPane();
		txtMonsterWeaponName.setText("Name");
		txtMonsterWeaponName.setEditable(false);
		txtMonsterWeaponName.setBounds(524, 313, 206, 22);
		frame.getContentPane().add(txtMonsterWeaponName);
		
		txtMonsterWeaponDamage = new JTextPane();
		txtMonsterWeaponDamage.setText("Damage");
		txtMonsterWeaponDamage.setEditable(false);
		txtMonsterWeaponDamage.setBounds(574, 348, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponDamage);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(360, 49, 97, 189);
		frame.getContentPane().add(btnPause);
		btnPause.addActionListener(this);
		
		barPlayerHealth = new JProgressBar();
		barPlayerHealth.setBounds(110, 51, 116, 14);
		frame.getContentPane().add(barPlayerHealth);
		barPlayerHealth.setValue(0);
		barPlayerHealth.setStringPainted(true);
		
		barMonsterHealth = new JProgressBar();
		barMonsterHealth.setBounds(574, 51, 116, 14);
		frame.getContentPane().add(barMonsterHealth);
		barMonsterHealth.setValue(0);
		barMonsterHealth.setStringPainted(true);
		
		barExperience = new JProgressBar();
		barExperience.setValue(0);
		barExperience.setStringPainted(true);
		barExperience.setBounds(238, 144, 116, 14);
		frame.getContentPane().add(barExperience);
		
		barKills = new JProgressBar();
		barKills.setValue(0);
		barKills.setStringPainted(true);
		barKills.setBounds(278, 271, 282, 14);
		frame.getContentPane().add(barKills);
		
		JLabel lblPlayerPoisoned = new JLabel("Poisoned");
		lblPlayerPoisoned.setBounds(136, 171, 56, 16);
		frame.getContentPane().add(lblPlayerPoisoned);
		
		txtPlayerIsPoisoned = new JTextField();
		txtPlayerIsPoisoned.setBounds(110, 189, 116, 22);
		frame.getContentPane().add(txtPlayerIsPoisoned);
		txtPlayerIsPoisoned.setColumns(10);
		
		txtMonsterIsPoisoned = new JTextField();
		txtMonsterIsPoisoned.setColumns(10);
		txtMonsterIsPoisoned.setBounds(574, 189, 116, 22);
		frame.getContentPane().add(txtMonsterIsPoisoned);
		
		JLabel lblMonsterPoisoned = new JLabel("Poisoned");
		lblMonsterPoisoned.setBounds(600, 171, 56, 22);
		frame.getContentPane().add(lblMonsterPoisoned);
		
		txtLevel = new JTextField();
		txtLevel.setBounds(238, 109, 116, 22);
		frame.getContentPane().add(txtLevel);
		txtLevel.setColumns(10);


	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.pauseGame();
		
	}
}


