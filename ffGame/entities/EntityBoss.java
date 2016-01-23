package entities;


public class EntityBoss extends EntityMonster {
	
	public static int BOSS_HEALTH = 300;
	public static int BOSS_DAMAGE = 25;
	public static int BOSS_DAMAGERANGE = 15;

	public EntityBoss() {
		super("Boss", BOSS_HEALTH, BOSS_DAMAGE, BOSS_DAMAGERANGE,0);
		this.PrintStats();
	}
	public EntityBoss(String str){
		super("Lord GabeN", 9999, 1, 1,10);
		this.PrintStats();
	}

}
