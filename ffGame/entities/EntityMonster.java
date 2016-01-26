package entities;

import weapon.Weapon;
import weapon.Weapon.Modifier;
import main.GameMethods;
import main.Game.Difficulty;

import java.util.Random;

import effects.EffectPoison;
import entities.EntityBase;
public class EntityMonster extends EntityBase {
	public static int MONSTER_HEALTH = 80;
	public static int MONSTER_DAMAGE = 15;
	public static int MONSTER_DAMAGERANGE = 10;
	static String[] MONSTER_NAMES = {"Zombie","Skeleton","Wraith","Bandit","Demon","Platypus","Slime","Cyclops","Dragon","Ogre","Vampire","WereWolf","Unicorn","Ghost","Giant Spider","Deep-Space Kraken","Wendigo","The Thing","Satanic Cultist","Goblin","Weeaboo","WHATARETHOOOSE?!?","Ninja Pirate","Slenderman","Rabid Butterfly","The Blob","Giant","Orc","999","Bat","Pokeman","Alien","Barbarian","Bee Swarm","Scorpion","Wolf Pack","Rogue Wizard","ERROR 404"};
	
	
	public EntityMonster(String name,double mult,double wepSpawn) {
		  super(name,multStats(mult,MONSTER_HEALTH) ,multStats(mult,MONSTER_DAMAGE) , MONSTER_DAMAGERANGE);
		//TODO health and damage randomizer???
		this.name = getRandomName();
		if(this.shouldSpawnWeapon(wepSpawn)){
			Weapon weapon = new Weapon();
			this.giveWeapon(weapon);
		}
	}
	public boolean shouldSpawnWeapon(double wepSpawn) {
		boolean a;
		double rand = Math.random()*100;
		if(rand < wepSpawn){
			a = true;
		}
		else{
			a = false;
		}
		return a;
	}
	public EntityMonster(String name, int health,int damage,int damageRange,double wepSpawn){
		super(name,health,damage,damageRange);
		if(this.shouldSpawnWeapon(wepSpawn)){
			Weapon weapon = new Weapon();
			this.giveWeapon(weapon);
		}
	}
	public void onDeath(){
		this.alive = false;
		System.out.println(name+" died!");
		
	}
	public String getRandomName(){
		int pick = new Random().nextInt(MONSTER_NAMES.length);
	    String name = MONSTER_NAMES[pick];
	    return name;
	}
	public void onAttacked(int dmg) {
		this.health -= dmg;
		System.out.println(this.name+" was hit for "+dmg+" damage!");
		if(health <= 0){
			onDeath();
		}
		else if(health <= 20){
			System.out.println(name+"'s Health is critically low!!!");
		}
	}
	private static int multStats(double mult,int base){
		int stat = (int)Math.round(mult * base);
		return stat;
	}

	
}
