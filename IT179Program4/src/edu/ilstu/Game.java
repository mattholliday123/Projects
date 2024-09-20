package edu.ilstu;

import java.util.*;

public class Game {

	// Square class
	private static class Square {
		private Square prev;
		private Square next;
		private int currentNumber;
		private int jump;
		/**
		 * Square constructor, instantiates variables
		 * @param currentNumber
		 * @param jump
		 * @param prev
		 */
		public Square(int currentNumber, int jump, Square prev) {
			this.currentNumber = currentNumber;
			this.jump = jump;
			this.prev = prev;
		}
	}// end square class

	// instance variables
	private Square head;
	ArrayList<Square> playerPosition;
	private int currentPlayer;

	/**
	 * instantiates the array list of player positions on squares. generates the
	 * board starts all players at the start(head)
	 * 
	 * @param numOfPlayers
	 */
	public Game(int numOfPlayers) {
		playerPosition = new ArrayList<>(numOfPlayers);
		generateBoard();
		for (int i = 0; i < numOfPlayers; i++) {
			playerPosition.add(this.head);
		}
	}

	/**
	 * method that allows the user to roll the die and calls the move method using
	 * the currentPlayer and the number that was rolled
	 */
	public void play() {
		Scanner scan = new Scanner(System.in);
		// change current player
		currentPlayer = (currentPlayer + 1) % playerPosition.size();
		System.out.println("\nPlayer number " + currentPlayer + "'s turn");
		System.out.println("Press any key and enter to roll the dice");
		scan.nextLine();
		Random rand = new Random();
		//roll die
		int num = rand.nextInt(1, 7);
		System.out.println("You rolled a " + num);
		move(num, currentPlayer);
	}

	/**
	 * 
	 * @param num
	 * @param currentPlayer
	 * @return
	 */
	public boolean move(int num, int currentPlayer) {
		// move player
		Square temp = playerPosition.get(currentPlayer);
		int start = temp.currentNumber;
		if (temp.currentNumber >= 100 || (temp.currentNumber + num) >= 100) {
			System.out.println("The winner is player number " + currentPlayer);
			return true;
		}
		//move
		for (int i = 1; i <= num; i++) {
			temp = temp.next;
		}
		// jump
		int jumpValue = temp.jump;
		//target variable to check if the jumpValue and current square is going to be > 100 or < 0
		int target = jumpValue + temp.currentNumber;
		//check for going off the board backwards, if so just go back to square 1
		if(target<0) {
			for(int i =0; i<temp.currentNumber+1;i++);
			temp = temp.prev;
		}//check if jump goes to square 100
		if (target >= 100) {
			System.out.println("You jumped to square 100");
			System.out.println("The winner is player number " + currentPlayer);
			return true;
		}
		//perform jump otherwise
		System.out.println("You have a jump value of " + jumpValue);
		if (jumpValue != 0) {
			if(jumpValue < 0) {
				for(int i =0; i > jumpValue;i--) {
					temp = temp.prev;
				}
			}else if(jumpValue>0) {
			for (int i = 0; i < jumpValue; i++) {
				temp = temp.next;
			}
			}
			System.out.println("You jumped " + jumpValue + " squares");
		}
		// print start and end location indicating jumps
		System.out.println("Starting square was " + start);
		System.out.println("End square is " + temp.currentNumber);
		playerPosition.set(currentPlayer, temp);
		play();
		return false;		
	}

	/**
	 * method that creates 100 squares and creates the connections between the
	 * squares
	 */
	private void generateBoard() {
		this.head = new Square(1, 0, null);
		Square temp = this.head;
		for (int i = 2; i < 101; i++) {
			temp.next = new Square(i, generateJumpValue(), temp);
			temp = temp.next;
		}
	}

	/**
	 * creates a jump value and makes the jump value of 0 has an 80 percent chance
	 * @return
	 */
	private int generateJumpValue() {
		Random rand = new Random();
		Random rand2 = new Random();
		int value = rand2.nextInt(101);		
		int jumpValue;
		if(value < 81) {
			jumpValue = 0;
		}else {
			jumpValue = rand.nextInt(-25, 25);
		}
		return jumpValue;
	}
}
