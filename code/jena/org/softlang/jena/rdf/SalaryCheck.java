package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import com.hp.hpl.jena.rdf.model.*;
import java.util.LinkedList;
import java.util.List;

public class SalaryCheck {

	public static boolean checkSalaries(CompanyModel c) {

		StmtIterator stmtit = c.getModel().listStatements(
				new SimpleSelector(
						null,
						c.DEPTS, (RDFNode) null));

		List<Resource> depts = new LinkedList<Resource>();

		while (stmtit.hasNext()) {
			NodeIterator subDeptsIt = stmtit.next().getBag().iterator();
			while (subDeptsIt.hasNext())
				depts.add(subDeptsIt.next().asResource());
		}
		for (Resource dept : depts) {
			// get manager's salary
			double managerSalary = dept.getProperty(c.MANAGER)
					.getProperty(c.SALARY).getDouble();
			NodeIterator employeeIt = dept.getProperty(
					c.EMPLOYEES).getBag().iterator();
			while (employeeIt.hasNext())
				if (!(employeeIt.next().asResource().getProperty(
						c.SALARY).getDouble() < managerSalary))
					return false;
		}

		return true;

	}
}