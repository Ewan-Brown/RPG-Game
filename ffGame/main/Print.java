package main;

import entities.EntityBase;
import entities.EntityPlayer;

public class Print {
	
	// fighter1 defeated fighter2!
	public static void printDefeated(EntityBase winner, EntityBase loser){
		System.out.println(winner.getName()+" has defeated "+loser.getName()+"!");
//		System.out.println("----Fight Over!---");
	}


}
