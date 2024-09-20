package edu.ilstu;

import java.util.*;

public class ExpressionTree {
	// instance variable
	private BinTreeNode root;

	/**
	 * constructor that takes a string expression in postfix notation and calls the
	 * buildTree method that returns a node, i.e., the root
	 * 
	 * @param expression
	 */
	public ExpressionTree(String expression) {
		root = buildTree(expression);
	}

	/**
	 * method that calls the private recursive method and returns the string of the
	 * expression in prefix notation
	 * 
	 * @return sb.toString()
	 */
	public String getPrefixNotation() {
		StringBuilder notation = new StringBuilder();

		getPrefixNotationHelper(root, notation);
		return notation.toString();
	}

	/**
	 * method that calls the private recursive method and returns the string of the
	 * expression in infix notation
	 * 
	 * @return
	 */
	public String getInfixNotation() {
		StringBuilder notation = new StringBuilder();

		getInfixNotationHelper(root, notation);
		return notation.toString();

	}
	/**
	 * method that calls the private recursive method and returns value of calculated expression
	 * @return
	 */
	public int getValue() {
		return getValueHelper(root);
	}

	/**
	 * private recursive method for the getPrefixNotation method 
	 * takes the node and a stringbuilder to build the notation
	 * @param node
	 * @param sb
	 */
	private void getPrefixNotationHelper(BinTreeNode node, StringBuilder notation) {
		if (node != null) {
			// check if node is operator
			if (node.isOperator)
				notation.append(node.symbol);
			else
				notation.append(node.value);
			notation.append(" ");
			getPrefixNotationHelper(node.left, notation);
			getPrefixNotationHelper(node.right, notation);
		}

	}

	/**
	 * private recursive method for the getInfixNotation method
	 * takes node and stringbuilder to build the notation
	 * @param node
	 * @param sb
	 */
	private void getInfixNotationHelper(BinTreeNode node, StringBuilder notation) {
		char symbol = ' ';
		if (node != null) {
			// check if operator
			if (node.isOperator) {
				// variable to hold the symbol
				symbol = node.symbol;
				notation.append("(");
				getInfixNotationHelper(node.left, notation);
			}else
				notation.append(node.value);
			notation.append(symbol);
			getInfixNotationHelper(node.right, notation);
			if (node.isOperator)
				notation.append(")");

		}

	}
	/**
	 * private recursive method for the getValue method 
	 * evaluates the expression given the node
	 * @param node
	 * @return
	 */
	private int getValueHelper(BinTreeNode node) {
		//variables to hold symbol(operator) and value
		char symbol = ' ';
		int value = 0;
		if (node != null) {
			// check for operator
			if (node.isOperator) {
				symbol = node.symbol;
				// grab the values
				int leftNum = getValueHelper(node.left);
				int rightNum = getValueHelper(node.right);
				// switch statement for each operator
				switch (symbol) {

				case '+':
					value = leftNum + rightNum;
					break;
				case '-':
					value = leftNum - rightNum;
					break;
				case '*':
					value = leftNum * rightNum;
					break;
				case '/':
					value = leftNum / rightNum;
					break;
				}
			} else
				return node.value;
		}
		return value;
	}
	

	/**
	 * checks to see if the given character of the expression is an operator or not
	 * 
	 * @param c
	 * @return
	 */
	private boolean isOperator(char c) {
		if (c == '+' || c =='-' || c == '/' || c == '*')
			return true;
		else
			return false;
	}
	/**
	 * builds the tree and returns the root
	 * 
	 * @param expression
	 * @return
	 */
	private BinTreeNode buildTree(String expression) {
		// create stack
		Stack<BinTreeNode> tree = new Stack<>();
		BinTreeNode node;

		// may need to split expression for numbers with > 1 digit
		String[] characters = expression.split(" ");
		for (String character : characters) {
				// if not operator, push
				if (!isOperator(character.charAt(0))) {
					node = new BinTreeNode(Integer.parseInt(character), false);
					tree.push(node);
					// if operator pop right and left and push operator making the popped nodes its children
				} else {
					node = new BinTreeNode(character.charAt(0), true);
					node.right = tree.pop();
					node.left = tree.pop();
					tree.push(node);
				}
			}
		//return the root
		return tree.pop();
	}

}
