package main;

public class Stage {
	
	int stageNum;
	int monstersKilled;
	int monstersRequired;
	int monstersReqIncrement = 5;
	double mult;
	
	public double getMult(){
		return this.mult;
	}
	public Stage(){
		int stageNum = 1;
		int monstersRequired = 5;
	}
	public void onMonsterKilled(){
		monstersKilled += 1;
		
	}
	public void onStageUp(){
		
	}
	public void printStats(){
		System.out.println("Monsters Killed "+monstersKilled+"/"+monstersRequired);
		System.out.println("Stage: "+stageNum);
	}

}
