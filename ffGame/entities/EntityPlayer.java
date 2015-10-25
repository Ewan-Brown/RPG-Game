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
		if(heal + health <= EntityPlayer.baseHealth){
			health = health + heal;;
			System.out.println(name+" was healed for "+heal+"HP!");
		}
	}
	public void onDeath(){
		alive = false;
	}
	public void onKillMonster(EntityMonster monster){
		
		healPlayer(20);
		kills = kills + 1;
		giveExp();
		tryGiveWeapon(monster.getWeapon());
	}
	public void tryGiveWeapon(Weapon monsterWeapon){
		Weapon playerWeapon = getWeapon();
		if(monsterWeapon != null){
			if(playerWeapon != null){
				if(monsterWeapon.getDamage() > playerWeapon.getDamage()){
					giveWeapon(monsterWeapon);
				}
			}
			else{
				giveWeapon(monsterWeapon);
			}
		}
	}
	public void onStageUp(){
		healPlayer(15);
		giveExp();
	}
	public void giveExp(){
		if(level < 5){
			xp = xp + bonusExp();
			//xp =+ EntityStats.EXPERIENCE_DROPS;
			if (xp >= EntityStats.EXP_PER_LEVEL){
				level = level + 1;
				xp = xp - 100;
				System.out.println("----LEVEL UP!!!----");
				baseHealth = baseHealth + 10;
				damage = damage + 5;
				health = baseHealth;
				System.out.println(baseHealth+" BaseHealth");
			}
			System.out.println(xp + " CURRENT XP");
		}
		else{
			System.out.println("player has reached Max level!");
		}
	}
	public int bonusExp(){
		int Base = 25;
		int Rand = (int) (Math.round(Math.random()) * 5);
		return Base + Rand;
	}
	
}
