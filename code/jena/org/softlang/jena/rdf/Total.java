package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.rdf.model.*;

/**
 * 
 * Total all salaries in a company.
 * 
 */
public class Total {

	public static double total(CompanyModel c) {
		StmtIterator stmtit = c.getModel().listStatements(
				new SimpleSelector(null, c.SALARY, (RDFNode) null));
		double total = 0;
		while (stmtit.hasNext()) {
			total += stmtit.nextStatement().getDouble();
		}
		return total;
	}
}
