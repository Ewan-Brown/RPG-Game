package entities;

import main.GameAlgorithms;
import weapon.Weapon;

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
		if(heal + this.health < EntityPlayer.baseHealth){
			this.health = this.health + heal;;
		}
	}
	public void onDeath(){
		this.alive = false;
		GameAlgorithms.PlayerStats(this);
	}
	public void onKillMonster(EntityMonster monster){
		
		this.healPlayer(20);
		this.kills =+ 1;
		giveExp();
		tryGiveWeapon(monster.getWeapon());
	}
	public void tryGiveWeapon(Weapon monsterWeapon){
		Weapon playerWeapon = this.getWeapon();
		if(monsterWeapon != null){
			if(playerWeapon != null){
				if(monsterWeapon.getDamage() > playerWeapon.getDamage()){
					this.giveWeapon(monsterWeapon);
				}
			}
			else{
				this.giveWeapon(monsterWeapon);
			}
		}
	}
	public void giveExp(){
		this.xp = this.xp + 25;
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
