package effects;

public class EffectBase {

	boolean active;
	int timeLeft;
	
	EffectBase(int time){
		this.timeLeft = time;
	}
	
	public void tick(){
		if(timeLeft > 0){
			timeLeft = timeLeft - 1;
		}
		else{
			active = false;
		}
	}
	public int getTimeLeft(){
		return timeLeft;
	}
	public void setTime(int time){
		this.timeLeft = time;
		this.active = true;
	}
	public boolean isActive(){
		return active;
	}
	
}
