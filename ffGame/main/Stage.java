package main;

public class Stage {
	
	int stageNum = 0;
	int monstersKilled;
	int monstersRequired;
	int monstersReqIncrement = 5;
	
	public double getMult(){
		double mult;
		//to make sure that mult equals > 1!
		mult = (stageNum * 0.5) + 1;
		return mult;
	}
	public Stage(){
		int stageNum = 1;
		int monstersRequired = 5;
	}
	public void onMonsterKilled(){
		monstersKilled += 1;
		if(monstersKilled == monstersRequired){
			this.onStageUp();
		}
		
	}
	public void onStageUp(){
		monstersKilled = 0;
		stageNum += 1;
	}
	public void printStats(){
		System.out.println("Monsters Killed "+monstersKilled+"/"+monstersRequired);
		System.out.println("Stage: "+stageNum);
	}

}
