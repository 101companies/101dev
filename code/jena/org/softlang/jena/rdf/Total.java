package org.softlang.jena.rdf;

import java.util.List;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.rdf.model.*;

/**
 * Total all salaries in a company.
 */
public class Total {

	public static double total(CompanyModel c) {
		double total = 0;
		List<Statement> l =
			c.getModel().listStatements(
				new SimpleSelector(
					null, c.SALARY, (RDFNode) null)).toList();
		for (Statement s : l)
			total += s.getDouble();
		return total;
	}
}
