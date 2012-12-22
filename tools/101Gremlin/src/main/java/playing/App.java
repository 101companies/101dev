package playing;

import org.openrdf.repository.RepositoryException;
import org.softlang.Explorer;

public class App {
	public static void main(String[] args) throws RepositoryException {
		System.out.println(Explorer.getResourceTriples("Language-3AHaskell"));
		System.out.println(Explorer.getResourceTriplesOut("Contribution-3Axslt"));		
	}
}
