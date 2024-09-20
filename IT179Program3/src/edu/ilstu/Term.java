/**
 * 
 */
package edu.ilstu;

/**
 * 
 */
public class Term {
	// data fields
	private int coefficient;
	private int exponent;

	/**
	 * 
	 * @param coefficient
	 * @param exponent
	 */
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	public String toString() {
		if (exponent == 0) {
			return Integer.toString(this.coefficient);
		} else if (exponent == 1) {
			return this.coefficient + "x";
		} else
			return this.coefficient + "x^" + this.exponent;
	}

	/**
	 * addTerm method that adds two like terms together
	 * 
	 * @param term
	 * @return
	 */
	public Term addTerm(Term term) {
		if (this.exponent != term.exponent) {
			return null;
		}
		int coefficientSum = this.coefficient + term.coefficient;
		Term t = new Term(coefficientSum, this.exponent);
		return t;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public int getExponent() {
		return exponent;
	}

}
