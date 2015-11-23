package entities;


public class EntityBoss extends EntityMonster {

	public EntityBoss() {
		super("Boss", EntityStats.BOSS_HEALTH, EntityStats.BOSS_DAMAGE, EntityStats.BOSS_DAMAGERANGE);
		this.PrintStats();
	}

}
