package org.softlang.pureJena;

import java.util.LinkedList;
import java.util.List;

import org.softlang.util.CompanyConstants;

import com.hp.hpl.jena.rdf.model.*;

public class SalaryCheck {

	public static boolean checkSalaries(Model model) {

		StmtIterator stmtit = model.listStatements(new SimpleSelector(null,
				CompanyConstants.DEPTS, (RDFNode) null));

		List<Resource> depts = new LinkedList<Resource>();

		while (stmtit.hasNext()) {
			NodeIterator subDeptsIt = stmtit.next().getBag().iterator();
			while (subDeptsIt.hasNext())
				depts.add(subDeptsIt.next().asResource());
		}
		for (Resource dept : depts) {
			// get manager's salary
			double managerSalary = dept.getProperty(CompanyConstants.MANAGER)
					.getProperty(CompanyConstants.SALARY).getDouble();
			NodeIterator employeeIt = dept.getProperty(
					CompanyConstants.EMPLOYEES).getBag().iterator();
			while (employeeIt.hasNext())
				if (!(employeeIt.next().asResource().getProperty(
						CompanyConstants.SALARY).getDouble() < managerSalary))
					return false;
		}

		return true;

	}
}