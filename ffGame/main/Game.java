package main; 

import java.awt.EventQueue;

import display.DisplayDylan;
import display.DisplayFighters;
import display.DisplayLogs;
import display.DisplayStart;
import display.DisplayStats;
import entities.EntityBase;
import entities.EntityBoss;
import entities.EntityBossGaben;
import entities.EntityMonsterGaben;
import entities.EntityMonster;
import entities.EntityPlayer;
import main.GameMethods;
import weapon.Weapon;
import weapon.WeaponConstants;


public class Game{
	
	private int CONFUSE = WeaponConstants.TYPE_CONFUSE;
	private int POISON = WeaponConstants.TYPE_POISON;
	private int FLAME = WeaponConstants.TYPE_FLAME;
	
	private int poisonTime = 5;
	private int poisonDamage = 5;
	private double poisonChance = 0.6;
	String playerName;
	boolean fastMode;
	Difficulty difficulty;
	public boolean dylanDrive;
	public EntityPlayer player;
	public EntityMonster monster;
	boolean GameOn = true;
	public Stage currentStage;
	DisplayFighters displayFighters;
	DisplayStats displayStats;
	DisplayDylan displayDylan;
	static Game game;
	//TODO TEMP VALUES FOR TESTING! >>
	double weaponSpawn = 100.0;
	boolean paused = false;
	int monsterMisses;
	int playerMisses;
	int monsterAttacks;
	int playerAttacks;
	DisplayStart displayStart;
	DisplayLogs logs;
	StartValues values = new StartValues();
	int sleepMillis = 500;
	
	public void pauseGame(){
		if(paused == false){
			System.out.println("PAUSED");
			paused = true;
		}
		else{
			System.out.println("UNPAUSED");
			paused = false;
		}
	}
	public boolean isPaused(){
		return paused;
	}
	public int getPMisses(){
		if(playerAttacks == 0){
			return 0;
		}
		else{
			int a = 100 / playerAttacks;
			a *= playerMisses; 
			
			return a;
		}
	}
	public int getMMisses(){
		if(monsterAttacks == 0){
			return 0;
		}
		else{
			int a = 100 / monsterAttacks;
			a *= monsterMisses; 
			
			return a;
		}
	}
	
	public enum Difficulty{
		Easy("Easy",0.3),
		Normal("Normal", 0.5),
		Hard("Hard", 1.3),
		Uber("Uber", 2);
		

		String mode;
		double multiplier;
		
		Difficulty(String mode, double mult){
			this.mode = mode;
			multiplier = mult;
		}
		public double getMult(){
			return multiplier;
		}
		public int mult(int a){
			return (int)Math.round(getMult()*a);
		}
		
	}
	public double getMult(){
		double b  = difficulty.getMult() * currentStage.getMult();
		return b;
	}
	public static void main(String[] args) {
		game = new Game();
		game.runGame();
	}
	public double getWeaponSpawn(){
		return this.weaponSpawn;
	}
	public void runGame(){
		
		runDisplays();

		while(true){
			GameOn = true;
			gameStart();
			currentStage = new Stage();
			battleStart();
			updateDisplays();
			System.out.println(getMult()+"");
			while(GameOn == true){
				if(currentStage.getStageNum() == currentStage.getFinalStage()){
					BossBattle();
					GameOn = false;
				}
				else{
					battleActive();
					boolean a = battleEnd();
					if(a == true){
						onPlayerWin();
					}
					if(a == false){
						onPlayerLose();
						GameOn = false;
					}
				}
			}
		}
	}
	public void runDisplays(){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						displayFighters = new DisplayFighters();
						displayFighters.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						displayDylan = new DisplayDylan();
						displayDylan.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						displayStats = new DisplayStats();
	
	//TODO taken out stats for now because it's just too many windows! also not usefull atm. might just throw stats into fighter display
	//					displayStats.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						displayStart = new DisplayStart();
						displayStart.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						logs = new DisplayLogs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	public Game(){}
	public void gameStart(){
		/* I dont know why but without this sleep, a 
		 * NullPointerException occurs on values = startDisplay.getValues();
		*/
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		values = displayStart.getValues();
		System.out.println("INFO TAKEN!");
		this.difficulty = values.difficulty;
		this.playerName = values.playerName;
		this.fastMode = values.fastMode;
		this.sleepMillis = values.sleepMillis;
	}
	
	public void battleStart(){
		//initialize monsters/player
		//begin fight!
		player = null;
		if(dylanDrive){
			player = GameMethods.PlayerSpawn("John");
			monster = new EntityMonsterGaben();
			
		}
		else{
			player = GameMethods.PlayerSpawn(playerName);
			monster = GameMethods.RandomMonster(getMult(),getWeaponSpawn());
		}
		player.PrintStats();
		sleep();
		monster.PrintStats();
		sleep();
		
	}
	public void battleActive(){
		if(monster.getWeapon() != null){
		}
		System.out.println(" ");
		sleep();
		currentStage.printStats();
		sleep();
		System.out.println(" ");
		sleep();
		//XXX 
		if(dylanDrive){
			System.out.println("DYLANNNNNNNNNNNN");
		}
		if(!(monster instanceof EntityBoss)){
			if(!monster.isAlive()){
				monster = GameMethods.RandomMonster(getMult(),getWeaponSpawn());
				if(dylanDrive){
					monster = new EntityMonsterGaben();
				}
				monster.PrintStats();
				sleep();
				displayFighters.updateDisplay(this);
			}
		}
		System.out.println("------FIGHT!------");
		boolean f1 = true;
		boolean f2 = true;
		
		do{
			//XXX 
			if(dylanDrive){
				System.out.println("DYLANNNNNNNNNNNN");
			}

			checkPaused();
			sleep();
			tryAttack(player, monster);
			updateDisplays();
			sleep();
			checkPaused();
			updateEffects();
			updateDisplays();
			sleep();
			tryAttack(monster, player);
			updateDisplays();
			sleep();
			updateEffects();
			updateDisplays();
			f1 = player.isAlive();
			f2 = monster.isAlive();
			
			
		}while(f1 && f2);
	}
	public boolean battleEnd(){
		boolean a = true;
		System.out.println("");
		if(player.isAlive()){
			a = true;
		}
		if(!(player.isAlive())){
			a = false;
		}
		return a;
	}
	public void BossBattle(){
		sleep();
		System.out.println("FINAL STAGE!");
		sleep();
		System.out.println("BOSS FIGHT!");
		sleep();
		if(dylanDrive){
			monster = new EntityBossGaben();
		}
		else{
			monster = new EntityBoss();
		}
		
		battleActive();
		if(player.isAlive()){
			onPlayerWinBoss();
		}
		else{
			onPlayerLose();
		}
		
	}
	public void onPlayerLose(){
		sleep();
		System.out.println("Game Over!");
		sleep();
		GameMethods.playerLossStats(player, monster,currentStage);
	}
	public void onPlayerWin(){
		System.out.println(player.getName()+" has defeated "+monster.getName()+"!");
		sleep();
		System.out.println(" ");
		currentStage.onMonsterKilled(player);
		System.out.println(" ");
		sleep();
		player.onKillMonster(monster);
		displayFighters.updateDisplay(this);
	}
	public void onPlayerWinBoss(){
		sleep();
		System.out.println(player.getName()+" has defeated "+monster.getName()+"!");
		sleep();
		System.out.println(player.getName()+" Has Defeated the Boss!");
		sleep();
		player.PrintStats();
	}
	
	public void sleep(){
		if(fastMode == false){
			try {
				Thread.sleep(sleepMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void checkPaused(){
		do{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(paused == true);
	}
	public void updateDisplays(){
		displayFighters.updateDisplay(this);
		displayStats.updateDisplay(this);
		displayDylan.updateDisplay(this);
	}
	public void updateEffects(){
		player.updateEffects();
		monster.updateEffects();
	}
	public void tryAttack(EntityBase attacker, EntityBase victim){
		if(attacker.isAlive()){
			if(attacker instanceof EntityPlayer){
				playerAttacks += 1;
			}
			if(attacker instanceof EntityMonster){
				monsterAttacks += 1;
			}
			int damage = attacker.onAttacking();
			if((damage > 0)){
				victim.onAttacked(damage);
				if(attacker.getWeapon() != null){
					double rand = Math.random();
					if(attacker.getWeapon().hasType(POISON)){
						if(rand < poisonChance){
							victim.givePoison(poisonDamage, poisonTime);
						}
					}
					if(attacker.getWeapon().hasType(CONFUSE)){
						victim.givePoison(poisonDamage, poisonTime);
					}
				}
			}
			else{
				System.out.println(attacker.getName()+" Missed!");
				if(attacker instanceof EntityMonster){
					monsterMisses += 1;
				}
				if(attacker instanceof EntityPlayer){
					playerMisses += 1;
				}
				
			}
		}
	}

}
