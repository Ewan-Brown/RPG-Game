package main;

import main.Main.Difficulty;
import weapon.Weapon;

import java.util.Random;

import entities.EntityBase;
import entities.EntityMonster;
import entities.EntityPlayer;

public class GameAlgorithms {
	
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static EntityPlayer PlayerSpawn(String name){
		EntityPlayer player = new EntityPlayer(name);
		return player;
	}
	public static EntityMonster RandomMonster(Difficulty difficulty){
		EntityMonster monster = new EntityMonster("NULL_MONSTER_NAME",difficulty);
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
	public static void PlayerStats(EntityPlayer player){
		Weapon weapon = player.getWeapon();
		int xp = player.xp;
		int kills = player.kills;
		int level = player.level;
		System.out.println(player.name +" Died"+ ((weapon == null) ? "!" : " Wielding his"+weapon.getName()));
		System.out.println("with "+level+" Levels and "+xp+" Experience");
		System.out.println("after brutally slaying "+kills+" Monsters!");
		
	}
}
