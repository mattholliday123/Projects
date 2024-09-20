/**
 * 
 */
package edu.ilstu;

import java.util.Scanner;

/**
 * 
 */
public class MainClass {

	/**
	 * main method that controls the flow of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean flag = true;

		// while loop to allow user to go again
		while (flag) {
			try {
				System.out.println("Welcome to the Polynomial Addition Program.\n");
				System.out.println("Enter your first polynomial");
				String p1 = scan.nextLine();
				Polynomial poly1 = new Polynomial(p1);
				System.out.println("Enter your second polynomial");
				String p2 = scan.nextLine();
				Polynomial poly2 = new Polynomial(p2);
				System.out.println("The sum is:");
				System.out.println(poly1.addPolynomial(poly2).toString() + "\n");
				System.out.println("Would you like to add two more polynomials?(y/n)");
				String response = scan.nextLine();
				if (response.equalsIgnoreCase("n"))
					flag = false;
			} catch (Exception e) {
				System.out.println("Error caught. Try again.");
				e.printStackTrace();
			}
		}

		System.out.println("Thank you for using the Polynomial Addition Program.");

	}
}
