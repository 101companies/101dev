package org.softlang.jena.query;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Syntax;

public class MentorCheck {

	public static boolean checkMentors(CompanyModel c){
		String queryString = 
			"ASK " 																	+
				"WHERE " 															+
					"{ ?employee1" + " <" + c.MENTOR + "> " + "?mentor . " 			+
						" EXISTS {"													+
							" ?employee2" + " <" + c.MENTOR + "> " + "?mentor . " 	+ 
							" FILTER(?employee1 != ?employee2) " 				    +
						"}"															+
					"}";  
			
		
		Query query = QueryFactory.create(queryString,Syntax.syntaxARQ);
		QueryExecution qe = QueryExecutionFactory.create(query, c.getModel());
		boolean out = qe.execAsk();
		qe.close();
		return !out;
	}
}


