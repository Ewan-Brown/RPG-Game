package entities;

import weapon.Weapon;
import weapon.Weapon.Modifier;
import main.GameAlgorithms;
import main.Main.Difficulty;

import java.util.Random;

import entities.EntityBase;
public class EntityMonster extends EntityBase {
	
	static int baseHealth = entities.EntityStats.MONSTER_HEALTH ;
	static int baseDamage = entities.EntityStats.MONSTER_DAMAGE;
	static int baseDamageRange = entities.EntityStats.MONSTER_DAMAGERANGE;
	static String[] MONSTER_NAMES = {"Zombie","Skeleton","Wraith","Bandit","Demon","Platypus","Slime","Cyclops","Dragon","Ogre","Vampire","WereWolf","Unicorn","Ghost","Giant Spider","Deep-Space Kraken","Wendigo","The Thing","Satanic Cultist","Goblin","Weeaboo","WHATARETHOOOSE?!?","Ninja Pirate","Slenderman","Rabid Butterfly","The Blob","Giant","Orc","999","Bat","Pokeman","Alien","Barbarian","Bee Swarm","Scorpion","Wolf Pack","Rogue Wizard","ERROR 404"};

	public EntityMonster(String name,Difficulty difficulty) {
		  super(name,difficulty.mult(baseHealth),difficulty.mult(baseDamage), baseDamageRange);
		//TODO health and damage randomizer
		this.alive = true;
		this.name = getRandomName();
		if(GameAlgorithms.shouldSpawnWeapon()){
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


	
}
