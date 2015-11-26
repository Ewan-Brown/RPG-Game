package entities;

import main.GameMethods;
import weapon.Weapon;

public class EntityPlayer extends EntityBase{
	
	public static int PLAYER_HEALTH = 100;
	public static int PLAYER_DAMAGE = 20;
	public static int PLAYER_DAMAGERANGE = 10;
	public static int EXPERIENCE_DROPS = 25;
	public static int EXP_PER_LEVEL = 100;

	public static int baseHealth = PLAYER_HEALTH ;
	static int damage = PLAYER_DAMAGE;
	static int damageRange = PLAYER_DAMAGERANGE;;
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
			if (xp >= EXP_PER_LEVEL){
				level = level + 1;
				xp = xp - 100;
				System.out.println("----LEVEL UP!!!----");
				baseHealth = baseHealth + 10;
				damage = damage + 5;
				health = baseHealth;
				System.out.println(baseHealth+" BaseHealth");
			}
			System.out.println("Experience "+xp+"/"+EXP_PER_LEVEL);
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
