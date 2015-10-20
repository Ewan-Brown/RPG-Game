package main;

import entities.EntityBase;
import entities.EntityPlayer;

public class Print {
	
	// fighterX did Y damage!
	public static void printAttack(EntityBase fighter1,int damage){
		System.out.println(fighter1.getName()+" did "+damage+" damage!");
	}
	// fighterX Missed!
	public static void printMissed(EntityBase fighter1){
		System.out.println(fighter1+"'s attack missed!");
	}
	// fighter1 defeated fighter2!
	public static void printDefeated(EntityBase winner, EntityBase loser){
		System.out.println(winner.getName()+" has defeated "+loser.getName()+"!");
		System.out.println("----Fight Over!---");
	}


}
