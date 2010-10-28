// (C) 2009 Ralf Laemmel

package algorithm.power;

public class Inefficient {

	public static int power(int x, int n) {
		int result = 1;
		for (int i=1; i<=n; i++)
			result *= x;
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(power(2,1)); // prints 2
		System.out.println(power(2,2)); // prints 4
		System.out.println(power(2,3)); // prints 8
		System.out.println(power(2,4)); // prints 16
		System.out.println(power(3,1)); // prints 3
		System.out.println(power(3,2)); // prints 9
		System.out.println(power(3,3)); // prints 27
		System.out.println(power(3,4)); // prints 81
	}

}
