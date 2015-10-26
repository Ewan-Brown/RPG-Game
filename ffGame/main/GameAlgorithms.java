package main;

import main.Main.Difficulty;
import weapon.Weapon;

import java.util.Random;

import entities.EntityBase;
import entities.EntityMonster;
import entities.EntityPlayer;

public class GameAlgorithms {
	
	public static String [] DeathLines = {"was ripped apart by","was torn to pieces by","was eaten by","had his innards turned into outtards by"};
	
	public static void sleep(boolean fastMode){
		if(!fastMode){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static EntityPlayer PlayerSpawn(String name){
		EntityPlayer player = new EntityPlayer(name);
		return player;
	}
	public static EntityMonster RandomMonster(double mult){
		EntityMonster monster = new EntityMonster("NULL_MONSTER_NAME",mult);
		return monster;
	}
	public static void tryAttack(EntityBase attacker, EntityBase victim){
		if(attacker.isAlive()){
			if (!didMiss()){
				int damage = attacker.onAttacking();
				victim.onAttacked(damage);
			}
			else{
				System.out.println(""+attacker.name+" missed!");
			}
		}
	}
	public static boolean didMiss(){
		double a = Math.random();
		if (a < 0.2){
			return true;
		}
		else{
			return false;
		}
	}
	public static int randomRangedInt(int range){
		//double between -1 and +1
		double rand = (Math.random() - 0.5) * 2;
		
		return (int)Math.round(range * rand);
		
	}

	public static boolean shouldSpawnWeapon(){
//		double rand = Math.random();
//		if(rand < 0.9){
//			return true;
//		}
//		else{
//			return false;
//		}
		return true;
	}
	public static String getRandomDeathString(EntityPlayer player, EntityMonster monster){
		String playername = player.getName();
		String monstername = monster.getName();
		int pick = new Random().nextInt(DeathLines.length);
		String line = DeathLines[pick];
		return playername+" "+line+" "+monstername;
	}
	public static void PlayerStats(EntityPlayer player,EntityMonster monster,Stage currentStage){
		Weapon weapon = player.getWeapon();
		int xp = player.xp;
		int kills = player.kills;
		int level = player.level;
		System.out.println(getRandomDeathString(player,monster)+((weapon == null) ? "!" : ", Wielding his "+weapon.getName()));
		System.out.println("with "+level+" Levels + "+xp+" Experience and a damage level of "+player.getDamage()+" On stage "+currentStage.stageNum);
		System.out.println("after brutally slaying "+kills+" Monsters!");
		
	}
}
