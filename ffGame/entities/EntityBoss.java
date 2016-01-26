package entities;


public class EntityBoss extends EntityMonster {
	
	public static int BOSS_HEALTH = 300;
	public static int BOSS_DAMAGE = 25;
	public static int BOSS_DAMAGERANGE = 15;

	public EntityBoss() {
		super("Boss", BOSS_HEALTH, BOSS_DAMAGE, BOSS_DAMAGERANGE,0);
	}
	public EntityBoss(String name,int health,int damage,int damageRange){
		super(name,health,damage,damageRange,0);
	}

}
