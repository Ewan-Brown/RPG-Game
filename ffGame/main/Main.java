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
			this.mode = mode;
			this.multiplier = mult;
		}
		public double getMult(){
			return multiplier;
		}
		public int mult(int a){
			return (int)Math.round(this.getMult()*a);
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
	static String playerName;
	static boolean fastMode;
	static EntityBase loser;
	static EntityBase winner;

	
	public static void main(String[] args) {
		gameStart();
		
	}
	public static void gameStart(){
		//select difficulty
		//enter playername
		//enter battleType
		Scanner scan = new Scanner(System.in);
		System.out.println("enter difficulty: 1 easy, 2 normal, 3 hard, 4 uber");
		int a = scan.nextInt();
		Difficulty difficulty = Difficulty.getDifficulty(a);
		GameAlgorithms.sleep(fastMode);
		System.out.println("enter Player's name");
		playerName = scan.next();
		System.out.println("Fastmode?");
		if (scan.nextInt() == 1){
			fastMode = true;
		}
		battleStart(difficulty);
		
	}
	
	public static void battleStart(Difficulty difficulty){
		//initialize monsters/player
		//begin fight!
		
		EntityPlayer player;
		EntityMonster monster;
		GameAlgorithms.sleep(fastMode);
			player = GameAlgorithms.PlayerSpawn(playerName);
			monster = GameAlgorithms.RandomMonster(difficulty);
			player.PrintStats();
			monster.PrintStats();
			GameAlgorithms.sleep(fastMode);
			battleActive(player,monster,difficulty);
		
	}
	public static void battleActive(EntityPlayer player, EntityMonster monster,Difficulty difficulty){
		//loop of turn based attacks, X hits Y, Y hits X etc.
		GameAlgorithms.sleep(fastMode);
		
		if(!monster.isAlive()){
			monster = GameAlgorithms.RandomMonster(difficulty);
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
			GameAlgorithms.sleep(fastMode);
			f2 = monster.isAlive();
			GameAlgorithms.sleep(fastMode);
			
		}while(f1 && f2);
		
		battleEnd(player, monster, f1, f2,difficulty);
	}
	public static void battleEnd(EntityPlayer player, EntityMonster monster, boolean f1, boolean f2,Difficulty difficulty){
		//player vs monster
		//give player drops!
		if(f1){
			onPlayerWin(player,monster,difficulty);
		}
		if(f2){
			onPlayerLose(player,monster);
		}
	}
	public static void onPlayerLose(EntityPlayer player,EntityMonster monster){
		System.out.println("Game Over!");
//		Print.printEndStats(player);
		GameAlgorithms.sleep(fastMode);
		main(null);
	}
	public static void onPlayerWin(EntityPlayer player,EntityMonster monster,Difficulty difficulty){
		Print.printDefeated(player,monster);
		player.onKillMonster(monster);
		battleActive(player, monster,difficulty);
	}

}
