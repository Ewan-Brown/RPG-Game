package entities;


import main.GameMethods;
import weapon.Weapon;

public class EntityBase{

	protected int health;
	protected int damage;
	protected int damageRange;
	protected String name;
	protected boolean alive = true;
	protected Weapon weapon;
	int missChance = 30;
	
	public EntityBase(String name,int health,int damage, int damageRange){
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.damageRange = damageRange;
	}
	public void onDeath(){
		this.alive = false;
		System.out.println(name+" died!");
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
	public int getWeaponDamage(){
		return getWeapon().getDamage();
	}
	public int getRawDamage(){
		return this.damage;
	}
	public int getHealth(){
		return health;
	}
	public int getDamage(){
		return (getWeapon() == null) ? getRawDamage() : getRawDamage() + getWeaponDamage();
	}
	public Weapon getWeapon() {
		// TODO Auto-generated method stub
		return this.weapon;
	}
	
	public int onAttacking() {
		if(!(this.didMiss())){
			int randRange = GameMethods.randomRangedInt(damageRange);
			return getDamage() + randRange;
		}
		else{
			return -1;
		}
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
	public boolean didMiss(){
		double a = Math.random() * 100;
		if(a < missChance){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
