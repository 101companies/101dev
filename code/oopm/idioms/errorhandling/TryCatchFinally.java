// Illustrate finally

package idioms.errorhandling;

public class TryCatchFinally {

	public static void a() {
		try {
			throw new NullPointerException();
		}
		catch (IllegalArgumentException x) {
			// ...
		}
		finally {
			System.out.println("In a at finally { }.");
		}
	}
	
	public static void main(String[] args) {
		a();
	}

}
