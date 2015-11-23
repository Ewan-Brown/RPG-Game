package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import entities.EntityMonster;
import entities.EntityPlayer;
import main.Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;

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
	public JButton btnPauseGame;
	public JTextPane txtMonsterWeaponDamage;
	
	public Game game = null;

	/**
	 * Launch the application.
	 */
	
	public void updateDisplay(Game currentGame){
		this.game = currentGame;
		EntityMonster monster = game.monster;
		EntityPlayer player = game.player;
		
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
		txtHealth.setBounds(12, 75, 116, 22);
		frame.getContentPane().add(txtHealth);
		
		txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText("Name");
		txtName.setBounds(12, 40, 116, 22);
		frame.getContentPane().add(txtName);
		
		txtDamage = new JTextPane();
		txtDamage.setEditable(false);
		txtDamage.setText("Damage");
		txtDamage.setBounds(12, 110, 116, 22);
		frame.getContentPane().add(txtDamage);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setBounds(38, 140, 56, 16);
		frame.getContentPane().add(lblWeapon);
		
		txtWeaponName = new JTextPane();
		txtWeaponName.setEditable(false);
		txtWeaponName.setText("Name");
		txtWeaponName.setBounds(12, 155, 116, 22);
		frame.getContentPane().add(txtWeaponName);
		
		txtWeaponDamage = new JTextPane();
		txtWeaponDamage.setEditable(false);
		txtWeaponDamage.setText("Damage");
		txtWeaponDamage.setBounds(12, 190, 116, 22);
		frame.getContentPane().add(txtWeaponDamage);
		
		lblMonster = new JLabel("MONSTER");
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMonster.setBounds(305, 13, 81, 22);
		frame.getContentPane().add(lblMonster);
		
		txtMonsterName = new JTextPane();
		txtMonsterName.setText("MonsterName");
		txtMonsterName.setEditable(false);
		txtMonsterName.setBounds(280, 40, 116, 22);
		frame.getContentPane().add(txtMonsterName);
		
		txtMonsterHealth = new JTextPane();
		txtMonsterHealth.setText("Health");
		txtMonsterHealth.setEditable(false);
		txtMonsterHealth.setBounds(280, 75, 116, 22);
		frame.getContentPane().add(txtMonsterHealth);
		
		txtMonsterDamage = new JTextPane();
		txtMonsterDamage.setText("Damage");
		txtMonsterDamage.setEditable(false);
		txtMonsterDamage.setBounds(280, 110, 116, 22);
		frame.getContentPane().add(txtMonsterDamage);
		
		lblMonsterWeapon = new JLabel("Weapon");
		lblMonsterWeapon.setBounds(306, 140, 56, 16);
		frame.getContentPane().add(lblMonsterWeapon);
		
		txtMonsterWeaponName = new JTextPane();
		txtMonsterWeaponName.setText("Name");
		txtMonsterWeaponName.setEditable(false);
		txtMonsterWeaponName.setBounds(280, 155, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponName);
		
		txtMonsterWeaponDamage = new JTextPane();
		txtMonsterWeaponDamage.setText("Damage");
		txtMonsterWeaponDamage.setEditable(false);
		txtMonsterWeaponDamage.setBounds(280, 190, 116, 22);
		frame.getContentPane().add(txtMonsterWeaponDamage);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(150, 37, 97, 189);
		frame.getContentPane().add(btnPause);
		btnPause.addActionListener(this);


	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.pauseGame();
		
	}
}


