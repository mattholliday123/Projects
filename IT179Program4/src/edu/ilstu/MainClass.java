package edu.ilstu;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Chutes and Ladders!");
		System.out.println("How many players are playing");
		//test for correct input
		while(!scan.hasNextInt()) {
			System.out.println("Enter in integer format(1,2,3,etc.)");
		}
		int numOfPlayers = scan.nextInt();
		
		Game game = new Game(numOfPlayers);
		game.play(); 
	}

}
