package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.rdf.model.*;

public class Total {

	public static double total(CompanyModel c) {
		StmtIterator stmtit = 
			c.getModel().listStatements(
					new SimpleSelector(
						null,
						c.SALARY, (RDFNode) null));
		double ttl = 0;
		while (stmtit.hasNext()) {
			ttl += stmtit.nextStatement().getDouble();
		}
		return ttl;
	}
}
