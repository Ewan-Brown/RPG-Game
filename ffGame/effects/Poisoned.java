package effects;

public class Poisoned {

	boolean active;
	int damage;
	int timeLeft;
	
	public Poisoned(int damage, int time){
		this.damage = damage;
		this.timeLeft = time;
	}
	public boolean isActive(){
		return active;
	}
	public void setDamage(int dmg){
		this.damage = dmg;
	}
	public void setTime(int time){
		
	}
	public void tick(){
		if(timeLeft < 0){
			timeLeft = timeLeft - 1;
		}
		else{
			active = false;
		}
	}
	public int getDamage(){
		return damage;
	}
	public int getTimeLeft(){
		return timeLeft;
	}
}
