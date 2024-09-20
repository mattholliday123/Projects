package edu.ilstu;

public class BinTreeNode {

	// instance variables
	public boolean isOperator;
	public char symbol;
	public int value;
	public BinTreeNode left;
	public BinTreeNode right;

	// constructors
	/**
	 * constructor that takes a symbol and isOperator boolean
	 * symbol represents operators in the expression
	 * This constructor is used for nodes created with an operator
	 * @param symbol
	 * @param isOperator
	 */
	public BinTreeNode(char symbol, boolean isOperator) {
		this.symbol = symbol;
		this.isOperator = isOperator;
		left=null;
		right=null;
	}
	/**
	 *constructor that takes value and isOperator boolean
	 * value represents the numbers in the expression 
	 * This constructor is used for nodes created with numbers
	 * @param value
	 * @param isOperator
	 */
	public BinTreeNode(int value, boolean isOperator) {
		this.value =value;
		left=null;
		right=null;
	}
	

}
