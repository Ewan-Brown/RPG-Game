package main;

import main.Game.Difficulty;
import weapon.Weapon;

import java.util.Random;

import entities.EntityBase;
import entities.EntityMonster;
import entities.EntityPlayer;

public class GameMethods {
	
	public static String [] DeathLines = {"was ripped apart by","was torn to pieces by","was eaten by","had his innards turned into outtards by"};
	
	
	public static EntityPlayer PlayerSpawn(String name){
		EntityPlayer player = new EntityPlayer(name);
		return player;
	}
	public static EntityMonster RandomMonster(double mult,double wepSpawn){
		EntityMonster monster = new EntityMonster("NULL_MONSTER_NAME",mult,wepSpawn);
		return monster;
	}
	public static int randomRangedInt(int range){
		//double between -1 and +1
		double rand = (Math.random() - 0.5) * 2;
		
		return (int)Math.round(range * rand);
		
	}
	public static String getRandomDeathString(EntityPlayer player, EntityMonster monster){
		String playername = player.getName();
		String monstername = monster.getName();
		int pick = new Random().nextInt(DeathLines.length);
		String line = DeathLines[pick];
		return playername+" "+line+" "+monstername;
	}
	public static void playerWinStats(EntityPlayer player){
		System.out.println(player.getName()+" Has won the game and slayed the boss with his "+player.getWeapon()+"!");
	}
	public static void playerLossStats(EntityPlayer player,EntityMonster monster,Stage currentStage){
		Weapon weapon = player.getWeapon();
		int xp = player.xp;
		int kills = player.kills;
		int level = player.level;
		System.out.println(getRandomDeathString(player,monster)+((weapon == null) ? "!" : ", Wielding his "+weapon.getName()));
		System.out.println("with "+level+" Levels + "+xp+" Experience and a damage level of "+player.getDamage()+" On stage "+currentStage.stageNum);
		System.out.println("after brutally slaying "+kills+" Monsters!");
		
	}
}
