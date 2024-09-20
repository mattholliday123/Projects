package edu.ilstu;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		//ask for expression in postfix notation
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter an expression in postfix notation");
		String expression = scan.nextLine();
		//create tree
		ExpressionTree tree = new ExpressionTree(expression);
		//prefix notation
		System.out.println("Prefix: " + tree.getPrefixNotation());	
		//infix notation
		System.out.println("Infix: " + tree.getInfixNotation());
		//evaluate expression
		System.out.println("Result: " + tree.getValue());
		
	}

}
