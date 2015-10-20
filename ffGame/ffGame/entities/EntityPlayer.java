package entities;

import main.GameAlgorithms;

public class EntityPlayer extends EntityBase{

	static int baseHealth = entities.EntityStats.PLAYER_HEALTH ;
	static int damage = entities.EntityStats.PLAYER_DAMAGE;
	static int damageRange = entities.EntityStats.PLAYER_DAMAGERANGE;;
	public int kills = 0;
	public int xp = 0;
	public int level = 0;
	
	public EntityPlayer(String name){
		super(name,baseHealth,damage,damageRange);
		//TODO health and damage randomizer
		this.alive = true;

	}
//	public void onEnemyKilled(){
//		this.kills =+ 1;
//		giveExp();
//		
//	}
	public void healPlayer(int heal){
		if(heal + this.health > EntityPlayer.baseHealth){
			this.health =+ heal;;
		}
	}
	public void onDeath(){
		this.alive = false;
		GameAlgorithms.PlayerStats(this);
	}
	public void giveExp(){
		System.out.println("XPED");
		this.xp =+ 25;
		//this.xp =+ EntityStats.EXPERIENCE_DROPS;
		if (this.xp >= EntityStats.EXP_PER_LEVEL){
			this.level =+ 1;
			System.out.println("----LEVEL UP!!!----");
			EntityPlayer.baseHealth =+ 10;
			this.health = baseHealth;
			System.out.println(baseHealth+" BaseHealth");
		}
		System.out.println(xp + " CURRENT XP");
	}
	
}
