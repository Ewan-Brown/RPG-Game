package main; 

import java.util.Scanner;

import entities.EntityBase;
import entities.EntityMonster;
import entities.EntityPlayer;
import main.GameAlgorithms;
import weapon.Weapon;


public class Main{

	public enum Difficulty{
		Easy("Easy",0.5),
		Normal("Normal", 1),
		Hard("Hard", 1.5),
		Uber("Uber", 2);
		

		String mode;
		double multiplier;
		
		Difficulty(String mode, double mult){
			mode = mode;
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
	String playerName;
	boolean fastMode;
	Difficulty difficulty;
	EntityPlayer player;
	EntityMonster monster;
	boolean GameOn = true;
	Stage currentStage;
	
	public double getMult(){
		double b  = difficulty.getMult() * currentStage.getMult(); 
		return b;
	}

	
	public static void main(String[] args) {
		Main game = new Main();
		game.RunGame();
		

	}
	public void RunGame(){
		while(true){
			GameOn = true;
			gameStart();
			currentStage = new Stage();
			battleStart();
			System.out.println(getMult());
			while(GameOn == true){
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
	public Main(){
	}
	public void gameStart(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Fastmode? press 1 for fastmode");
		if (scan.nextInt() == 1){
			fastMode = true;
		}
		System.out.println("enter difficulty: 1 easy, 2 normal, 3 hard, 4 uber");
		int a = scan.nextInt();
		difficulty = Difficulty.getDifficulty(a);
		System.out.println("enter Player's name");
		playerName = scan.next();

		
	}
	
	public void battleStart(){
		//initialize monsters/player
		//begin fight!
		

		GameAlgorithms.sleep(fastMode);
		player = GameAlgorithms.PlayerSpawn(playerName);
		monster = GameAlgorithms.RandomMonster(getMult());
		player.PrintStats();
		monster.PrintStats();
		GameAlgorithms.sleep(fastMode);
		
	}
	public void battleActive(){
		currentStage.printStats();
		//loop of turn based attacks, X hits Y, Y hits X etc.
		GameAlgorithms.sleep(fastMode);
		
		if(!monster.isAlive()){
			monster = GameAlgorithms.RandomMonster(getMult());
			monster.PrintStats();
			GameAlgorithms.sleep(fastMode);
		}
		System.out.println("------FIGHT!------");
		boolean f1 = true;
		boolean f2 = true;
		do{
			GameAlgorithms.tryAttack(player, monster);
			GameAlgorithms.sleep(fastMode);
			GameAlgorithms.tryAttack(monster, player);
			GameAlgorithms.sleep(fastMode);
			f1 = player.isAlive();
			f2 = monster.isAlive();
			
		}while(f1 && f2);
	}
	public boolean battleEnd(){
		boolean a = true;
		if(player.isAlive()){
			a = true;
		}
		if(!(player.isAlive())){
			a = false;
		}
		return a;
	}
	public void onPlayerLose(){
		System.out.println("Game Over!");
		GameAlgorithms.PlayerStats(player, monster,currentStage);
		GameAlgorithms.sleep(fastMode);
//		main(null);
	}
	public void onPlayerWin(){
		currentStage.onMonsterKilled(player);
		Print.printDefeated(player,monster);
		player.onKillMonster(monster);
	}

}
