package entities;

import main.GameAlgorithms;
import weapon.Weapon;

public class EntityBase implements EntityInterface{

	public int health;
	public int damage;
	public int damageRange;
	public String name;
	public boolean alive;
	private Weapon weapon;
	
	EntityBase(String name,int health,int damage, int damageRange){
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.damageRange = damageRange;
	}
	public void onDeath(){
		this.alive = false;
		System.out.println(name+" died!");
	}
	@Override
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
	
	public int getDamage(){
		return (this.weapon == null) ? this.damage : this.damage + getWeapon().getDamage();
		//return this.damage + getWeapon().getDamage();
	}
	public Weapon getWeapon() {
		// TODO Auto-generated method stub
		return this.weapon;
	}
	@Override
	public int onAttacking() {
		int randRange = GameAlgorithms.randomRangedInt(damageRange);
		return getDamage() + randRange;
	}
	public void PrintStats(){
		System.out.println(name+" HP:"+health+" DMG:"+damage+((weapon == null) ? "": " WEAPON: "+weapon.getName()+" WDMG:"+weapon.getDamage()) );
	}
	public boolean isAlive(){
		return alive;
	}
	public String getName(){
		return this.name;
	}
	public void giveWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
	
}
