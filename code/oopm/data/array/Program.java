// (C) 2009 Ralf Laemmel

package data.array;

public class Program {

	/**
	 * We can only put ints into an IntArray.
	 */
	public static void testIntArray() {
		IntArray a = new IntArray();
		for (int i=0; i<10; i++) {
			a.setAt(i, i);
			System.out.println(
					" a[" + i + "] == " + a.getAt(i) +
					"; a.length == " + a.getLength() +
					"/" + a.getCapacity());
		}
	}
	
	/**
	 * We can put any kind of reference into an ObjectArray.
	 * Here, we put Strings into the collection, indeed.
	 */
	public static void testObjectArray() {
		ObjectArray a = new ObjectArray();
		for (int i=0; i<10; i++) {
			a.setAt(i, "\"" + i + "\"");
			System.out.println(
					" a[" + i + "] == " + a.getAt(i) +
					"; a.length == " + a.getLength() +
					"/" + a.getCapacity());
		}
	}

	/**
	 * We can put a boxed int into an IntArray.
	 * Because of auto-boxing, we can put an int into the collection, too.
	 */
	public static void testArray() {
		Array<Integer> a = new Array<Integer>();
		for (int i=0; i<10; i++) {
			// a.setAt(i, "\"" + i + "\"");
			a.setAt(i, i);
			System.out.println(
					" a[" + i + "] == " + a.getAt(i) +
					"; a.length == " + a.getLength() +
					"/" + a.getCapacity());
		}
	}
	
	public static <T> String arrayToString(T[] a) {
		String r = "[";
		for (int i = 0; i < a.length; i++) {
			if (i!=0) r += ",";
			r += a[i].toString();
		}
		r += "]";
		return r;
	}

	public static <T> String arrayToStringBuilder(T[] a) {
		StringBuilder r = new StringBuilder();
		r.append("[");
		for (int i = 0; i < a.length; i++) {
			if (i!=0) r.append(",");
			r.append(a[i].toString());
		}
		r.append("]");
		return r.toString();
	}
		
	public static void testStringBuilder() {
		StringBuilder x = new StringBuilder();
		x.append("This ");
		x.append("is ");
		x.append("a ");
		x.append("very ");
		x.append("long ");
		x.append("string.");
		System.out.println(x.toString());
	}
		
	public static void main(String[] args) {
		testIntArray();
		testObjectArray();
		testArray();
		testStringBuilder();
	}
}
