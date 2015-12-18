package main;

import entities.EntityPlayer;

public class Stage {
	
	private int stageNum = 0;
	private int monstersKilled;
	private int monstersRequired = 3;
	int finalStage = 7;
//	int monstersReqIncrement; TO BE USED LATER
	
	public double getMult(){
		double mult;
		//to make sure that mult equals > 1!
		mult = ((stageNum-1) * 0.3) + 1;
		return mult;
	}
	public int getStageNum(){
		return stageNum;
	}
	public Stage(){
		stageNum = 1;
	}
	public void onMonsterKilled(EntityPlayer player ){
		monstersKilled += 1;
		if(monstersKilled == monstersRequired){
			this.onStageUp(player);
		}
		
	}
	public void onStageUp(EntityPlayer player){
		monstersKilled = 0;
		stageNum += 1;
		System.out.println("Stage Up! : "+stageNum);
		
		player.onStageUp();
	}
	public void printStats( ){
		System.out.println("Monsters Killed "+monstersKilled+"/"+monstersRequired);
		System.out.println("Stage: "+stageNum);
	}

}
