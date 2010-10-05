package org.softlang.arq;

import org.softlang.util.CompanyConstants;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;

public class Total {

	public static double total(Model model){
		String queryString = 
			"SELECT sum(?salary)" +
			"WHERE " +
				"{?e"   + " <" + CompanyConstants.SALARY	+ "> " + "?salary"  + "}";
		
		// We need some "syntax extension" here
		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet rs = qe.execSelect();
		double ttl = rs.next().getLiteral(rs.getResultVars().get(0))
				.getDouble();
		qe.close();
		return ttl;
	}
	
}
