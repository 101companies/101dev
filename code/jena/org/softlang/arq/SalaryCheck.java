package org.softlang.arq;

import org.softlang.util.CompanyConstants;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.RDFS;

public class SalaryCheck {

	public static boolean checkSalaries(Model model){
		String queryString = 
			"ASK" +
				"{?dept"		+ " <" + CompanyConstants.MANAGER 	+ "> " + "?manager"			+ ". " +
				" ?manager"		+ " <" + CompanyConstants.SALARY    + "> " + "?managerSalary" 	+ ". " +
				" ?dept"		+ " <" + CompanyConstants.EMPLOYEES + "> " + "?employees"		+ ". " +
				" ?employees"   + " <" + RDFS.member				+ "> " + "?employee"        + ". " +
				" ?employee"    + " <" + CompanyConstants.SALARY	+ "> " + "?employeeSalary"  + ". " +
				" FILTER (?employeeSalary >= ?managerSalary)" +	
				"}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		boolean out = qe.execAsk();
		qe.close();
		return !out;
	}
	
}