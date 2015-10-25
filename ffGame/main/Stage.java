package main;

public class Stage {
	
	int stageNum = 0;
	int monstersKilled;
	int monstersRequired;
//	int monstersReqIncrement; TO BE USED LATER FOR MONSTERKILLSREQUIRED INCREMENTS!
	
	public double getMult(){
		double mult;
		//to make sure that mult equals > 1!
		mult = ((stageNum-1) * 0.5) + 1;
		return mult;
	}
	public Stage(){
		stageNum = 1;
		monstersRequired = 3;
	}
	public void onMonsterKilled(){
		monstersKilled += 1;
		printStats();
		if(monstersKilled == monstersRequired){
			this.onStageUp();
		}
		
	}
	public void onStageUp(){
		monstersKilled = 0;
		stageNum += 1;
		System.out.println("Stage Up!"+stageNum);
	}
	public void printStats(){
		System.out.println("Monsters Killed "+monstersKilled+"/"+monstersRequired);
		System.out.println("Stage: "+stageNum);
	}

}
