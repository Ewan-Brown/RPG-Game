package main; 

import java.awt.EventQueue;
import java.util.Scanner;

import display.FighterDisplay;
import display.StartDisplay;
import display.StatsDisplay;
import entities.EntityBase;
import entities.EntityBoss;
import entities.EntityMonster;
import entities.EntityPlayer;
import main.GameMethods;


public class Game{
	
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
		static public Difficulty getDifficulty(int a){
			Difficulty difficulty = null;
			if(a == 1){
				difficulty = Easy;
			}
			if(a == 2){
				difficulty = Normal;
			}
			if(a == 3){
				difficulty = Hard;
			}
			if(a == 4){
				difficulty = Uber;
			}
			return difficulty;
		}
	}
	
	public boolean didMiss(){
		double a = Math.random();
		if (a < this.missChance){
			return true;
		}
		else{
			return false;
		}
	}
	String playerName;
	boolean fastMode;
	Difficulty difficulty;
	public EntityPlayer player;
	public EntityMonster monster;
	boolean GameOn = true;
	public Stage currentStage;
	FighterDisplay fighterDisplay;
	StatsDisplay statsDisplay;
	static Game game;
	double weaponSpawn = 30.0;
	double missChance = 20.0;
	boolean paused = false;
	int monsterMisses;
	int playerMisses;
	int monsterAttacks;
	int playerAttacks;
	StartDisplay startDisplay;
	StartValues values = new StartValues();
	
	
	public double getMissChance(){
		return this.missChance;
	}
	public double getWeaponSpawn(){
		return this.weaponSpawn;
	}
	public double getMult(){
		double b  = difficulty.getMult() * currentStage.getMult();
		return b;
	}
	public void sleep(){
		if(fastMode == false){
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		game = new Game();
		game.runGame();
	}
	public void runDisplays(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fighterDisplay = new FighterDisplay();
					fighterDisplay.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					statsDisplay = new StatsDisplay();
					statsDisplay.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startDisplay = new StartDisplay();
					startDisplay.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void runGame(){
		
		runDisplays();

		while(true){
			GameOn = true;
			gameStart();
			currentStage = new Stage();
			battleStart();
			fighterDisplay.updateDisplay(this);
			statsDisplay.updateDisplay(this);
			System.out.println(getMult());
			while(GameOn == true){
				if(currentStage.stageNum == currentStage.finalStage){
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
	public Game(){}
	public void gameStart(){
		/* I dont know why but without this sleep, a 
		 * NullPointerException occurs on values = startDisplay.getValues();
		*/
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		values = startDisplay.getValues();
		System.out.println("INFO TAKEN!");
		this.difficulty = values.difficulty;
		this.playerName = values.playerName;
		this.fastMode = values.fastMode;
		
//		System.out.println("Fastmode? press 1 for fastmode");
//		if (scan.nextInt() == 1){
//			fastMode = true;
//		}
//		sleep();
//		System.out.println("enter difficulty: 1 easy, 2 normal, 3 hard, 4 uber");
//		int a = scan.nextInt();
//		difficulty = Difficulty.getDifficulty(a);
//		sleep();
//		System.out.println("enter Player's name");
//		playerName = scan.next();
//		sleep();
	}
	
	public void battleStart(){
		//initialize monsters/player
		//begin fight!
		player = GameMethods.PlayerSpawn(playerName);
		monster = GameMethods.RandomMonster(getMult(),getWeaponSpawn());
		player.PrintStats();
		sleep();
		monster.PrintStats();
		sleep();
		
	}
	public void battleActive(){
		System.out.println(" ");
		sleep();
		currentStage.printStats();
		sleep();
		System.out.println(" ");
		sleep();
		if(!(monster instanceof EntityBoss)){
			if(!monster.isAlive()){
				monster = GameMethods.RandomMonster(getMult(),getWeaponSpawn());
				monster.PrintStats();
				sleep();
				fighterDisplay.updateDisplay(this);
			}
		}
		System.out.println("------FIGHT!------");
		boolean f1 = true;
		boolean f2 = true;
		
		do{
			if(paused == false){
				sleep();
				tryAttack(player, monster);
				fighterDisplay.updateDisplay(this);
				statsDisplay.updateDisplay(this);
				sleep();
				tryAttack(monster, player);
				fighterDisplay.updateDisplay(this);
				statsDisplay.updateDisplay(this);
				f1 = player.isAlive();
				f2 = monster.isAlive();
			}
			else{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
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
	public void onPlayerLose(){
		sleep();
		System.out.println("Game Over!");
		sleep();
		GameMethods.playerLossStats(player, monster,currentStage);
	}
	public void onPlayerWin(){
		Print.printDefeated(player,monster);
		sleep();
		System.out.println(" ");
		currentStage.onMonsterKilled(player);
		System.out.println(" ");
		sleep();
		player.onKillMonster(monster);
		fighterDisplay.updateDisplay(this);
	}
	public void BossBattle(){
		sleep();
		System.out.println("FINAL STAGE!");
		sleep();
		System.out.println("BOSS FIGHT!");
		sleep();
		monster = new EntityBoss();
		battleActive();
		if(player.isAlive()){
			onPlayerWinBoss();
		}
		else{
			onPlayerLose();
		}
		
	}
	public void onPlayerWinBoss(){
		sleep();
		Print.printDefeated(player,monster);
		sleep();
		System.out.println(player.name+" Has Defeated the Boss!");
		sleep();
		player.PrintStats();
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
			}
			else{
				System.out.println(attacker.name+" Missed!");
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
