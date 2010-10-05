package org.softlang.util;

import com.hp.hpl.jena.rdf.model.*;

public class CompanyConstants {

	public static final String NS_COMPANY = "http://www.company.com/ns#";
	private static Model model = ModelFactory.createDefaultModel();

	public static final Property DEPTS;
	public static final Property DNAME;
	public static final Property MANAGER;
	public static final Property EMPLOYEES;
	public static final Property PU;
	public static final Property DU;
	public static final Property SALARY;
	public static final Property PERSON;
	public static final Property MENTOR;
	public static final Property PNAME;
	public static final Property ADDRESS;

	static {
		DEPTS = model.createProperty(NS_COMPANY + "depts");
		DNAME = model.createProperty(NS_COMPANY + "dName");
		MANAGER = model.createProperty(NS_COMPANY + "manager");
		EMPLOYEES = model.createProperty(NS_COMPANY + "employees");
		PU = model.createProperty(NS_COMPANY + "pu");
		DU = model.createProperty(NS_COMPANY + "du");
		SALARY = model.createProperty(NS_COMPANY + "salary");
		PERSON = model.createProperty(NS_COMPANY + "person");
		MENTOR = model.createProperty(NS_COMPANY + "mentor");
		PNAME = model.createProperty(NS_COMPANY + "pName");
		ADDRESS = model.createProperty(NS_COMPANY + "address");
	}
}
