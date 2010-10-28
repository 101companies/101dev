// (C) 2008 Ralf Laemmel

package data.range;

/**
 * A data type for ranges of float. Upon construction a min/max range is
 * established. Clearly, it is required that min<=max as the invariant. There
 * are setters for the min/max components that preserve the invariant. Invariant
 * and assertion checking relies on Java's assert. Hence, one must turn assert
 * to get enforcement.
 */
public class FloatRange {
	private Float min, max;

	/**
	 * The invariant of this class
	 */
	private void Invariant() {
		assert this.min <= this.max;
	}

	/**
	 * Constructor that establishes the invariant
	 */
	public FloatRange(Float min, Float max) {

		// Precondition
		assert min < max;

		this.min = min;
		this.max = max;

		// Postcondition
		assert getMin() == min && getMax() == max;

		Invariant();
	}

	/**
	 * Getter for min
	 */
	public Float getMin() {
		Invariant();
		return min;
	}

	/**
	 * Setter for min
	 */
	public void setMin(Float min) {
		Invariant();
		// Precondition
		assert min <= getMax();

		this.min = min;

		Invariant();
		// Postcondition
		assert getMin() == min;
	}

	/**
	 * Getter for max
	 */
	public Float getMax() {
		Invariant();
		return max;
	}

	/**
	 * Setter for max
	 */
	public void setMax(Float max) {
		Invariant();
		// Precondition
		assert getMin() <= max;

		this.max = max;

		Invariant();
		// Postcondition
		assert getMax() == max;
	}
}
