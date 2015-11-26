package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.javafx.tk.Toolkit.Task;

import entities.EntityMonster;
import entities.EntityPlayer;
import main.Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class FighterDisplay implements ActionListener{

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
	
	public Game game = null;
	private JProgressBar barExperience;

	/**
	 * Launch the application.
	 */
	
	public void updateDisplay(Game currentGame){
		this.game = currentGame;
		EntityMonster monster = game.monster;
		EntityPlayer player = game.player;
		barPlayerHealth.setMinimum(0);
		barPlayerHealth.setMaximum(player.baseHealth);
		barPlayerHealth.setValue(player.health);
		barMonsterHealth.setMinimum(0);
		barMonsterHealth.setMaximum(monster.baseHealth);
		barMonsterHealth.setValue(monster.health);
		barExperience.setMinimum(0);
		barExperience.setMaximum(player.EXP_PER_LEVEL);
		barExperience.setValue(player.xp);
		
		txtName.setText(player.name);
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
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FighterDisplay window = new FighterDisplay();
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
	public FighterDisplay() {
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
		
		lblPlayer = new JLabel("PLAYER");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayer.setBounds(12, 13, 81, 22);
		frame.getContentPane().add(lblPlayer);
		
		txtHealth = new JTextPane();
		txtHealth.setEditable(false);
		txtHealth.setText("Health");
		txtHealth.setBounds(12, 89, 116, 22);
		frame.getContentPane().add(txtHealth);
		
		txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText("Name");
		txtName.setBounds(12, 54, 116, 22);
		frame.getContentPane().add(txtName);
		
		txtDamage = new JTextPane();
		txtDamage.setEditable(false);
		txtDamage.setText("Damage");
		txtDamage.setBounds(12, 124, 116, 22);
		frame.getContentPane().add(txtDamage);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setBounds(38, 154, 56, 16);
		frame.getContentPane().add(lblWeapon);
		
		txtWeaponName = new JTextPane();
		txtWeaponName.setEditable(false);
		txtWeaponName.setText("Name");
		txtWeaponName.setBounds(12, 169, 116, 22);
		frame.getContentPane().add(txtWeaponName);
		
		txtWeaponDamage = new JTextPane();
		txtWeaponDamage.setEditable(false);
		txtWeaponDamage.setText("Damage");
		txtWeaponDamage.setBounds(12, 204, 116, 22);
		frame.getContentPane().add(txtWeaponDamage);
		
		lblMonster = new JLabel("MONSTER");
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMonster.setBounds(305, 13, 81, 22);
		frame.getContentPane().add(lblMonster);
		
		txtMonsterName = new JTextPane();
		txtMonsterName.setText("MonsterName");
		txtMonsterName.setEditable(false);
		txtMonsterName.setBounds(280, 54, 116, 22);
		frame.getContentPane().add(txtMonsterName);
		
		txtMonsterHealth = new JTextPane();
		txtMonsterHealth.setText("Health");
		txtMonsterHealth.setEditable(false);
		txtMonsterHealth.setBounds(280, 89, 116, 22);
		frame.getContentPane().add(txtMonsterHealth);
		
		txtMonsterDamage = new JTextPane();
		txtMonsterDamage.setText("Damage");
		txtMonsterDamage.setEditable(false);
		txtMonsterDamage.setBounds(280, 124, 116, 22);
		frame.getContentPane().add(txtMonsterDamage);
		
		lblMonsterWeapon = new JLabel("Weapon");
		lblMonsterWeapon.setBounds(306, 154, 56, 16);
		frame.getContentPane().add(lblMonsterWeapon);
		
		txtMonsterWeaponName = new JTextPane();
		txtMonsterWeaponName.setText("Name");
		txtMonsterWeaponName.setEditable(false);
		txtMonsterWeaponName.setBounds(280, 169, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponName);
		
		txtMonsterWeaponDamage = new JTextPane();
		txtMonsterWeaponDamage.setText("Damage");
		txtMonsterWeaponDamage.setEditable(false);
		txtMonsterWeaponDamage.setBounds(280, 204, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponDamage);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(150, 37, 97, 189);
		frame.getContentPane().add(btnPause);
		btnPause.addActionListener(this);
		
		barPlayerHealth = new JProgressBar();
		barPlayerHealth.setBounds(12, 39, 116, 14);
		frame.getContentPane().add(barPlayerHealth);
		barPlayerHealth.setValue(0);
		barPlayerHealth.setStringPainted(true);
		
		barMonsterHealth = new JProgressBar();
		barMonsterHealth.setBounds(280, 39, 116, 14);
		frame.getContentPane().add(barMonsterHealth);
		barMonsterHealth.setValue(0);
		barMonsterHealth.setStringPainted(true);
		
		barExperience = new JProgressBar();
		barExperience.setValue(0);
		barExperience.setStringPainted(true);
		barExperience.setBounds(12, 226, 116, 14);
		frame.getContentPane().add(barExperience);


	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.pauseGame();
		
	}
}


