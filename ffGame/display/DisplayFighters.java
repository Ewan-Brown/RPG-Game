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
import java.awt.Component;
import javax.swing.Box;

public class DisplayFighters implements ActionListener{

	public JFrame frame;
	private JLabel lblPlayer;
	private JTextPane txtHealth;
	private JTextPane txtName;
	private JTextPane txtDamage;
	private JLabel lblWeapon;
	private JTextPane txtWeaponName;
	private JTextPane txtWeaponDamage;
	private JLabel lblMonster;
	private JTextPane txtMonsterName;
	private JTextPane txtMonsterHealth;
	private JTextPane txtMonsterDamage;
	private JLabel lblMonsterWeapon;
	private JTextPane txtMonsterWeaponName;
	private JButton btnPause;
	private JProgressBar barPlayerHP;
	private JTextPane txtMonsterWeaponDamage;
	private JProgressBar barMonsterHP;
	private JProgressBar barKills;
	
	public Game game = null;
	private JProgressBar barExperience;
	private JTextField txtPlayerIsPoisoned;
	private JTextField txtMonsterIsPoisoned;
	private JTextField txtLevel;
	private JLabel lblMonsterPoisoned;
	private JLabel lblLevel;
	private JTextPane txtPlayerMaxHP;
	private JTextPane txtMonsterMaxHP;
	private JTextField txtStage;
	private JTextField txtMult;
	private JLabel lblMultiplier ;
	private JLabel lblStage;
	private JTextField txtPlayerIsConfused;
	private JTextField txtMonsterIsConfused;
	private JLabel lblMonsterIsConfused;
	private JLabel lblPlayerIsConfused;

	/**
	 * Launch the application.
	 */
	
	public void updateDisplay(Game currentGame){
		this.game = currentGame;
		EntityMonster monster = game.monster;
		EntityPlayer player = game.player;
		txtPlayerMaxHP.setText(player.getBaseHealth()+"");
		txtMonsterMaxHP.setText(player.getBaseHealth()+"");
		barPlayerHP.setMinimum(0);
		barPlayerHP.setMaximum(player.getBaseHealth());
		barPlayerHP.setValue(player.getHealth());
		barMonsterHP.setMinimum(0);
		barMonsterHP.setMaximum(monster.getBaseHealth());
		barMonsterHP.setValue(monster.getHealth());
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
		txtStage.setText(game.currentStage.getStageNum()+"");
		txtMult.setText(game.currentStage.getMult()+"");

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
		lblPlayer.setBounds(66, 25, 81, 22);
		frame.getContentPane().add(lblPlayer);
		
		txtHealth = new JTextPane();
		txtHealth.setEditable(false);
		txtHealth.setText("Health");
		txtHealth.setBounds(12, 84, 90, 22);
		frame.getContentPane().add(txtHealth);
		
		txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText("Name");
		txtName.setBounds(44, 49, 154, 22);
		frame.getContentPane().add(txtName);
		
		txtDamage = new JTextPane();
		txtDamage.setEditable(false);
		txtDamage.setText("Damage");
		txtDamage.setBounds(64, 200, 116, 22);
		frame.getContentPane().add(txtDamage);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setBounds(96, 362, 56, 16);
		frame.getContentPane().add(lblWeapon);
		
		txtWeaponName = new JTextPane();
		txtWeaponName.setEditable(false);
		txtWeaponName.setText("Name");
		txtWeaponName.setBounds(28, 377, 206, 22);
		frame.getContentPane().add(txtWeaponName);
		
		txtWeaponDamage = new JTextPane();
		txtWeaponDamage.setEditable(false);
		txtWeaponDamage.setText("Damage");
		txtWeaponDamage.setBounds(70, 412, 116, 22);
		frame.getContentPane().add(txtWeaponDamage);
		
		lblMonster = new JLabel("MONSTER");
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMonster.setBounds(599, 25, 81, 22);
		frame.getContentPane().add(lblMonster);
		
		txtMonsterName = new JTextPane();
		txtMonsterName.setText("Name");
		txtMonsterName.setEditable(false);
		txtMonsterName.setBounds(558, 49, 146, 22);
		frame.getContentPane().add(txtMonsterName);
		
		txtMonsterHealth = new JTextPane();
		txtMonsterHealth.setText("Health");
		txtMonsterHealth.setEditable(false);
		txtMonsterHealth.setBounds(524, 84, 90, 22);
		frame.getContentPane().add(txtMonsterHealth);
		
		txtMonsterDamage = new JTextPane();
		txtMonsterDamage.setText("Damage");
		txtMonsterDamage.setEditable(false);
		txtMonsterDamage.setBounds(572, 200, 116, 22);
		frame.getContentPane().add(txtMonsterDamage);
		
		lblMonsterWeapon = new JLabel("Weapon");
		lblMonsterWeapon.setBounds(598, 362, 56, 16);
		frame.getContentPane().add(lblMonsterWeapon);
		
		txtMonsterWeaponName = new JTextPane();
		txtMonsterWeaponName.setText("Name");
		txtMonsterWeaponName.setEditable(false);
		txtMonsterWeaponName.setBounds(522, 377, 206, 22);
		frame.getContentPane().add(txtMonsterWeaponName);
		
		txtMonsterWeaponDamage = new JTextPane();
		txtMonsterWeaponDamage.setText("Damage");
		txtMonsterWeaponDamage.setEditable(false);
		txtMonsterWeaponDamage.setBounds(572, 412, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponDamage);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(360, 49, 97, 189);
		frame.getContentPane().add(btnPause);
		btnPause.addActionListener(this);
		
		barPlayerHP = new JProgressBar();
		barPlayerHP.setBounds(64, 138, 116, 14);
		frame.getContentPane().add(barPlayerHP);
		barPlayerHP.setValue(0);
		barPlayerHP.setStringPainted(true);
		
		barMonsterHP = new JProgressBar();
		barMonsterHP.setBounds(564, 122, 116, 14);
		frame.getContentPane().add(barMonsterHP);
		barMonsterHP.setValue(0);
		barMonsterHP.setStringPainted(true);
		
		barExperience = new JProgressBar();
		barExperience.setValue(0);
		barExperience.setStringPainted(true);
		barExperience.setBounds(206, 173, 116, 14);
		frame.getContentPane().add(barExperience);
		
		barKills = new JProgressBar();
		barKills.setValue(0);
		barKills.setStringPainted(true);
		barKills.setBounds(278, 271, 282, 14);
		frame.getContentPane().add(barKills);
		
		JLabel lblPlayerPoisoned = new JLabel("Poisoned");
		lblPlayerPoisoned.setBounds(90, 235, 56, 16);
		frame.getContentPane().add(lblPlayerPoisoned);
		
		txtPlayerIsPoisoned = new JTextField();
		txtPlayerIsPoisoned.setBounds(64, 253, 116, 22);
		frame.getContentPane().add(txtPlayerIsPoisoned);
		txtPlayerIsPoisoned.setColumns(10);
		
		txtMonsterIsPoisoned = new JTextField();
		txtMonsterIsPoisoned.setColumns(10);
		txtMonsterIsPoisoned.setBounds(572, 253, 116, 22);
		frame.getContentPane().add(txtMonsterIsPoisoned);
		
		lblMonsterPoisoned = new JLabel("Poisoned");
		lblMonsterPoisoned.setBounds(598, 235, 56, 22);
		frame.getContentPane().add(lblMonsterPoisoned);
		
		txtLevel = new JTextField();
		txtLevel.setBounds(206, 138, 116, 22);
		frame.getContentPane().add(txtLevel);
		txtLevel.setColumns(10);
		
		lblLevel = new JLabel("Level");
		lblLevel.setBounds(246, 119, 41, 16);
		frame.getContentPane().add(lblLevel);

		txtPlayerMaxHP = new JTextPane();
		txtPlayerMaxHP.setText("MaxHealth");
		txtPlayerMaxHP.setEditable(false);
		txtPlayerMaxHP.setBounds(108, 84, 90, 22);
		frame.getContentPane().add(txtPlayerMaxHP);
		
		txtMonsterMaxHP = new JTextPane();
		txtMonsterMaxHP.setText("MaxHealth");
		txtMonsterMaxHP.setEditable(false);
		txtMonsterMaxHP.setBounds(626, 84, 90, 22);
		frame.getContentPane().add(txtMonsterMaxHP);
		
		txtStage = new JTextField();
		txtStage.setBounds(349, 306, 116, 22);
		frame.getContentPane().add(txtStage);
		txtStage.setColumns(10);
		
		lblStage = new JLabel("Stage");
		lblStage.setBounds(383, 287, 56, 16);
		frame.getContentPane().add(lblStage);
		
		lblMultiplier = new JLabel("Multiplier");
		lblMultiplier.setBounds(383, 333, 56, 16);
		frame.getContentPane().add(lblMultiplier);
		
		txtMult = new JTextField();
		txtMult.setBounds(349, 359, 116, 22);
		frame.getContentPane().add(txtMult);
		txtMult.setColumns(10);
		
		lblPlayerIsConfused = new JLabel("Confused");
		lblPlayerIsConfused.setBounds(90, 287, 56, 16);
		frame.getContentPane().add(lblPlayerIsConfused);
		
		lblMonsterIsConfused = new JLabel("Confused");
		lblMonsterIsConfused.setBounds(598, 287, 56, 16);
		frame.getContentPane().add(lblMonsterIsConfused);
		
		txtPlayerIsConfused = new JTextField();
		txtPlayerIsConfused.setBounds(64, 306, 116, 22);
		frame.getContentPane().add(txtPlayerIsConfused);
		txtPlayerIsConfused.setColumns(10);
		
		txtMonsterIsConfused = new JTextField();
		txtMonsterIsConfused.setColumns(10);
		txtMonsterIsConfused.setBounds(572, 306, 116, 22);
		frame.getContentPane().add(txtMonsterIsConfused);


	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.pauseGame();
		
	}
}


