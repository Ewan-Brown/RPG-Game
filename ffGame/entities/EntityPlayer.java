package entities;


import main.GameMethods;
import weapon.Weapon;

public class EntityPlayer extends EntityBase{
	
	public static int PLAYER_HEALTH = 100;
	public static int PLAYER_DAMAGE = 20;
	public static int PLAYER_DAMAGERANGE = 10;
	public static int EXPERIENCE_DROPS = 25;
	public static int EXP_PER_LEVEL = 100;
	

	private int kills = 0;
	private int exp = 0;
	private int level = 0;
	private int damageDealt = 0;
	private int damageTaken = 0;
	
	public int getDamageDealt(){
		return damageDealt;
	}
	public int getDamageTaken(){
		return damageTaken;
	}
	public int getKills(){
		return kills;
	}
	public int getLevel(){
		return level;
	}
	public int getExp(){
		return exp;
	}
	public EntityPlayer(String name){
		super(name,PLAYER_HEALTH,PLAYER_DAMAGE,PLAYER_DAMAGERANGE);
		this.alive = true;

	}
	public void healPlayer(int heal){
		if(heal + health <= baseHealth){
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
	public void onAttacked(int damage) {
		this.damageTaken += damage;
		this.health -= damage;
		System.out.println(this.name+" was hit for "+damage+" damage!");
		if(health <= 0){
			onDeath();
		}
		else if(health <= 20){
			System.out.println(name+"'s Health is critically low!!!");
		}
	}
	public int onAttacking() {
		if(!(this.didMiss())){
			int randRange = GameMethods.randomRangedInt(damageRange);
			int damage =getDamage() + randRange;
			this.damageDealt += damage;
			return damage;
		}
		else{
			return -1;
		}
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
			exp = exp + bonusExp();
			//xp =+ EntityStats.EXPERIENCE_DROPS;
			if (exp >= EXP_PER_LEVEL){
				level = level + 1;
				exp = exp - 100;
				System.out.println("----LEVEL UP!!!----");
				baseHealth = baseHealth + 10;
				damage = damage + 5;
				health = baseHealth;
				System.out.println(baseHealth+" BaseHealth");
			}
			System.out.println("Experience "+exp+"/"+EXP_PER_LEVEL);
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
