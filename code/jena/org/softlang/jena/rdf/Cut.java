package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import java.util.Iterator;
import com.hp.hpl.jena.rdf.model.*;

/**
 * 
 * Cut all salaries in half.
 * 
 */
public class Cut {

	public static void cut(CompanyModel c) {
		Iterator<Statement> stmtit = c.getModel().listStatements(
				new SimpleSelector(null, c.SALARY, (RDFNode) null)).toList()
				.iterator();
		while (stmtit.hasNext()) {
			Statement stmt = stmtit.next();
			stmt.changeLiteralObject(stmt.getDouble() / 2);
		}
	}
}
