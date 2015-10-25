package main;

import entities.EntityPlayer;

public class Stage {
	
	int stageNum = 0;
	int monstersKilled;
	int monstersRequired;
//	int monstersReqIncrement; TO BE USED LATER
	
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
	public void onMonsterKilled(EntityPlayer player){
		monstersKilled += 1;
		printStats();
		if(monstersKilled == monstersRequired){
			this.onStageUp(player);
		}
		
	}
	public void onStageUp(EntityPlayer player){
		monstersKilled = 0;
		stageNum += 1;
		System.out.println("Stage Up!"+stageNum);
		player.onStageUp();
	}
	public void printStats(){
		System.out.println("Monsters Killed "+monstersKilled+"/"+monstersRequired);
		System.out.println("Stage: "+stageNum);
	}

}
