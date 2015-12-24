package weapon;

import java.util.Random;

public class Weapon {

    private int damage;
    private String name;
    private String type;
    private Modifier modifier;
	private Poison poisonStats;
	
	private int poisonDamage = 5;
	private int totalTime = 2;
	private final int CONFUSE = WeaponConstants.TYPE_CONFUSE;
	private final int POISON = WeaponConstants.TYPE_POISON;
	private final int FLAME = WeaponConstants.TYPE_FLAME;
	private boolean[] attribute = new boolean[3];
	public static String[] WEAPON_NAME = {"Sword","Rat on a Stick","Spear","Dagger","War Hammer","Battle Axe","Stick","Crossbow","Bow","Slingshot","BAZOOKA!","Damascus Steel Karambit","Javelin","Great Sword","Long Bow","Big Sharp Thing","Toothpick"}; 
	public Weapon(){
		this.type = getRandomType();
		this.modifier = getRandomModifier();
		this.damage = (int) (WeaponConstants.BASE_WEAPON_DAMAGE * modifier.multiplier);
		this.name = modifier.name+" "+type;
		System.out.println(name + " Created!");
	}
	public void setType(int type,boolean setting){
		attribute[type] = setting;
		if(type == POISON){
			poisonStats = new Poison(poisonDamage,totalTime);
		}
	}
	public boolean hasType(int type){
		return attribute[type];
	}
	public static String getRandomType(){
		int pick = new Random().nextInt(WEAPON_NAME.length);
		return WEAPON_NAME[pick];
	}
	public int getDamage(){
		return this.damage;
	}
	
	public String getName(){
		return this.name;
	}
	public Modifier getRandomModifier(){
		int pick = new Random().nextInt(Modifier.values().length);
	    return Modifier.values()[pick];
	}
	
	public enum Modifier{
		Mod_50("Crappy",0.50),
		Mod_80("Weak",0.80),
		Mod_100("Normal",1.00),
		Mod_120("Strong",1.20),
		Mod_150("Godly",1.50);
		
		String name;
		double multiplier;
		
		Modifier(String name,double multiplier){
			this.multiplier = multiplier;
			this.name = name;
	
		}
		public double getModifier(){
			return multiplier;
			
		}
	}
	
}
