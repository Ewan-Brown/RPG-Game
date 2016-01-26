package effects;

public class EffectPoison extends EffectBase{

	int damage;
	
	public EffectPoison(int damage, int time){
		super(time);
		this.damage = damage;
	}
	public void setDamage(int dmg){
		this.damage = dmg;
		this.active = true;
	}
	public int getDamage(){
		return damage;
	}
}
