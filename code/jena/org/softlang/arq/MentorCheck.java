package org.softlang.arq;

import org.softlang.util.CompanyConstants;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;

public class MentorCheck {

	public static boolean checkMentors(Model model){
		String queryString = 
			"ASK " 																					+
				"WHERE " 																			+
					"{ ?employee1" + " <" + CompanyConstants.MENTOR + "> " + "?mentor . " 			+
						" EXISTS {"																	+
							" ?employee2" + " <" + CompanyConstants.MENTOR + "> " + "?mentor . " 	+ 
							" FILTER(?employee1 != ?employee2) " 								    +
						"}"																			+
					"}";  
			
		
		Query query = QueryFactory.create(queryString,Syntax.syntaxARQ);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		boolean out = qe.execAsk();
		qe.close();
		return !out;
	}
	
}


