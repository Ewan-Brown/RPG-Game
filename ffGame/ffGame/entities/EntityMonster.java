package entities;

import weapon.Weapon;
import main.GameAlgorithms;
import main.Main.Difficulty;
import entities.EntityBase;
public class EntityMonster extends EntityBase {
	
	static int baseHealth = entities.EntityStats.MONSTER_HEALTH ;
	static int baseDamage = entities.EntityStats.MONSTER_DAMAGE;
	static int baseDamageRange = entities.EntityStats.MONSTER_DAMAGERANGE;
	

	public EntityMonster(String name,Difficulty difficulty) {
		  super(name,difficulty.mult(baseHealth),difficulty.mult(baseDamage), baseDamageRange);
//		super(name,100,20,10);
		//TODO health and damage randomizer
		this.alive = true;
		if(GameAlgorithms.shouldSpawnWeapon()){
			Weapon weapon = new Weapon();
			this.giveWeapon(weapon);
		}
	}
	public void onDeath(){
		this.alive = false;
		System.out.println(name+" died!");
		
	}


	
}
