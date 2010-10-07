package org.softlang.jena.query;

import org.softlang.company.CompanyModel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * 
 * Check that salaries increase with rank in hierarchy.
 * 
 */
public class PrecedenceCheck {

	public static boolean checkPrecedence(CompanyModel c){
		String queryString = 
			"ASK" +
				"{?dept"		+ " <" + c.MANAGER 	 + "> " + "?manager"		 + ". " +
				" ?manager"		+ " <" + c.SALARY    + "> " + "?managerSalary" 	 + ". " +
				" ?dept"		+ " <" + c.EMPLOYEES + "> " + "?employees"		 + ". " +
				" ?employees"   + " <" + RDFS.member + "> " + "?employee"        + ". " +
				" ?employee"    + " <" + c.SALARY	 + "> " + "?employeeSalary"  + ". " +
				" FILTER (?employeeSalary >= ?managerSalary)" +	
				"}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, c.getModel());
		boolean out = qe.execAsk();
		qe.close();
		return !out;
	}
	
}