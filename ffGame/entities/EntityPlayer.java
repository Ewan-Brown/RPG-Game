package entities;

import main.GameAlgorithms;
import weapon.Weapon;

public class EntityPlayer extends EntityBase{

	public static int baseHealth = entities.EntityStats.PLAYER_HEALTH ;
	static int damage = entities.EntityStats.PLAYER_DAMAGE;
	static int damageRange = entities.EntityStats.PLAYER_DAMAGERANGE;;
	public int kills = 0;
	public int xp = 0;
	public int level = 0;
	
	public EntityPlayer(String name){
		super(name,baseHealth,damage,damageRange);
		this.alive = true;

	}
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
		this.kills = kills + 1;
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
			this.level = level + 1;
			this.xp = 0;
			System.out.println("----LEVEL UP!!!----");
			this.baseHealth = this.baseHealth + 10;
			this.damage = this.damage + 5;
			this.health = baseHealth;
			System.out.println(baseHealth+" BaseHealth");
		}
		System.out.println(xp + " CURRENT XP");
	}
	
}
