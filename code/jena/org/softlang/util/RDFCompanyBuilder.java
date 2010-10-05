package org.softlang.util;

import com.hp.hpl.jena.rdf.model.*;

public class RDFCompanyBuilder {

	private Model model;
	

	
	public RDFCompanyBuilder(){
		model = ModelFactory.createDefaultModel();
		
	}
	
	public Model getModel(){
		return model;
	}
	
	public Resource createCompany(Container deptsBag) {
		return model.createResource().
			addProperty(
					CompanyConstants.DEPTS, 
					deptsBag);
	}
	
	
	public Resource createDept(String uri,String dName, Resource manager, Container employees, Container depts){
		return model.createResource(uri).
			addLiteral(
					CompanyConstants.DNAME,
					dName).
			addProperty(
					CompanyConstants.MANAGER,
					manager).
			addProperty(
					CompanyConstants.EMPLOYEES,
					employees).
			addProperty(
					CompanyConstants.DEPTS, 
					depts);
	}
	
	
	public Resource createEmployee(String uri, Resource person, Double salary, Resource mentor){
		Resource employee =  model.createResource(uri).
			addLiteral(
					CompanyConstants.SALARY, 
					salary).
			addProperty(
					CompanyConstants.PERSON, 
					person);
		if (mentor!=null)
			employee.addProperty(
					CompanyConstants.MENTOR,
					mentor);
		return employee; 
	}
	
	
	public Resource createPerson(String pName, String pAddress){
		return model.createResource().
			addLiteral(
					CompanyConstants.PNAME, 
					pName).
			addLiteral(
					CompanyConstants.ADDRESS, 
					pAddress);
	}

}
