package main; 

import java.util.Scanner;

import entities.EntityBase;
import entities.EntityMonster;
import entities.EntityPlayer;
import main.GameAlgorithms;
import weapon.Weapon;


public class Main{

	int DELETETHIS = 8;
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
		public int mult(int num){
			return (int)Math.round(this.getMult()*num);
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
		GameAlgorithms.sleep(500L);
		System.out.println("enter Player's name");
		playerName = scan.next();
		battleStart(difficulty);
		
	}
	
	public static void battleStart(Difficulty difficulty){
		//initialize monsters/player
		//begin fight!
		
		EntityPlayer player;
		EntityMonster monster;
		GameAlgorithms.sleep(500L);
			player = GameAlgorithms.PlayerSpawn(playerName);
			monster = GameAlgorithms.RandomMonster(difficulty);
			player.PrintStats();
			monster.PrintStats();
			GameAlgorithms.sleep(1000L);
			battleActive(player,monster,difficulty);
		
	}
	public static void battleActive(EntityPlayer player, EntityMonster monster,Difficulty difficulty){
		//loop of turn based attacks, X hits Y, Y hits X etc.
		GameAlgorithms.sleep(500L);
		
		if(!monster.isAlive()){
			monster = GameAlgorithms.RandomMonster(difficulty);
			monster.PrintStats();
			GameAlgorithms.sleep(1000);
		}
		System.out.println("------FIGHT!------");
		boolean f1 = true;
		boolean f2 = true;
		do{
			GameAlgorithms.tryAttack(player, monster);
			GameAlgorithms.sleep(500L);
			GameAlgorithms.tryAttack(monster, player);
			GameAlgorithms.sleep(500L);
			f1 = player.isAlive();
			GameAlgorithms.sleep(500L);
			f2 = monster.isAlive();
			GameAlgorithms.sleep(500L);
			
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
		GameAlgorithms.sleep(2000);
		main(null);
	}
	public static void onPlayerWin(EntityPlayer player,EntityMonster monster,Difficulty difficulty){
		Print.printDefeated(player,monster);
		player.onKillMonster(monster);
		battleActive(player, monster,difficulty);
	}

}
