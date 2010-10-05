package org.softlang.pureJena;

import org.softlang.util.CompanyConstants;
import com.hp.hpl.jena.rdf.model.*;

public class Total {

	public static double total(Model model) {
		StmtIterator stmtit = model.listStatements(new SimpleSelector(null,
				CompanyConstants.SALARY, (RDFNode) null));
		double ttl = 0;
		while (stmtit.hasNext()) {
			ttl += stmtit.nextStatement().getDouble();
		}
		return ttl;
	}

}
