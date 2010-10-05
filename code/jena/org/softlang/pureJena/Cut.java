package org.softlang.pureJena;

import java.util.Iterator;
import org.softlang.util.CompanyConstants;
import com.hp.hpl.jena.rdf.model.*;

public class Cut {

	public static void cut(Model model){
		Iterator<Statement> stmtit = model.listStatements(
				new SimpleSelector(null, CompanyConstants.SALARY, (RDFNode) null))
				.toList().iterator();
		while (stmtit.hasNext()) {
			Statement stmt = stmtit.next();
			stmt.changeLiteralObject(stmt.getDouble() / 2);
		}
	}
}
