/**
 * 
 */
package edu.ilstu;

/**
 * 
 */
public class Polynomial {
	// data fields
	private Node termsHead;
	private Node termsTail;

	/**
	 * Default constructor
	 */
	public Polynomial() {
		this.termsHead = null;
		this.termsTail = null;
	}

	/**
	 * constructor that splits up the the polynomial passed into terms separated by
	 * + to split the polynomial into terms
	 * 
	 * @param polynomial
	 */
	public Polynomial(String polynomial) {
		this.termsHead = null;
		this.termsTail = null;
		int coefficient = 0;
		int exponent = 0;
		int xIndex = 0;
		//replace minus signs with +- to make sure we get correctly get negative terms when splitting 
		polynomial = polynomial.replaceAll("-", "+-");
		String[] term = polynomial.split("[+]");
		for (String s : term) {
			s = s.trim();
			
			if (!s.isEmpty()) {
				// check for coefficients
				if (s.contains("x")) {
					if (s.charAt(0) == 'x') {
						coefficient = 1;
					} else {
						xIndex = s.indexOf('x');
						//parse coefficient
						coefficient = Integer.parseInt(s.substring(0, xIndex).replace(" ", ""));
					}
					// check for exponents
					if (s.contains("^")) {
						int cIndex = s.indexOf('^');
						//parse coefficient
						exponent = Integer.parseInt(s.substring(cIndex + 1).replace(" ", ""));
					} else {
						exponent = 1;
					}
				} else {
					//parse coefficient
					coefficient = Integer.parseInt(s);
					exponent = 0;
				}
				Term t = new Term(coefficient, exponent);
				addTermToPolynomial(t);
			}
		}

	}

	/**
	 * Method that adds a term to the end of the list of terms
	 * 
	 * @param term
	 */
	public void addTermToPolynomial(Term term) {
		Node temp = new Node(term);
		if (termsHead == null) {
			this.termsHead = temp;
			this.termsTail = temp;
		} else {
			this.termsTail.next = temp;
			this.termsTail = temp;
		}
	}

	/**
	 * toString method that outputs standard polynomial format
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = this.termsHead;
		while (temp != null) {
			if (temp.termData != null) {
				int coefficient = temp.termData.getCoefficient();
				int exponent = temp.termData.getExponent();
				// check if coefficients are negative or positive
				if (coefficient > 0)
					result.append("+");
				else if (coefficient < 0) {
					result.append("-");
					coefficient = coefficient*-1;
				}
				//accounts for constant except for 1 because that would be "x"(tested for later)
				if (coefficient != 1 || exponent == 0)
					result.append(coefficient);
				// exponents
				if (exponent > 1 && coefficient > 0) {
					result.append("x^" + exponent);
					// special case for exponent and coefficient that are 1
				} else if (exponent == 1 && coefficient ==1) {
					result.append("x");
				}

				temp = temp.next;
			}
		}
		return result.toString();
	}

	/**
	 * method that adds two polynomials to get the sum
	 * 
	 * @param p
	 * @return
	 */
	public Polynomial addPolynomial(Polynomial p) {
		Polynomial sum = new Polynomial();
		Node temp = this.termsHead;
		Node temp2 = p.termsHead;
		// while loop to go through the terms
		while (temp != null && temp2 != null) {
			if (temp.termData.getExponent() > temp2.termData.getExponent()) {
				sum.addTermToPolynomial(temp.termData);
				temp = temp.next;
			} else if (temp.termData.getExponent() < temp2.termData.getExponent()) {
				sum.addTermToPolynomial(temp2.termData);
				temp2 = temp2.next;
			} else {
				sum.addTermToPolynomial(temp.termData.addTerm(temp2.termData));
				temp = temp.next;
				temp2 = temp2.next;
			}
		}
		return sum;
	}

	/**
	 * Node Class
	 * 
	 */
	private class Node {
		// data fields
		private Term termData;
		private Node next;

		/**
		 * 
		 * @param termData
		 */
		private Node(Term termData) {
			this.termData = termData;
		}

		/**
		 * 
		 * @param termData
		 * @param next
		 */
		private Node(Term termData, Node next) {
			this.termData = termData;
			this.next = next;
		}
	}

}
