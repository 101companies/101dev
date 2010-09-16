package org.softlang.or;

import org.softlang.or.company.*;

/**
 * Get the company depth
 * 
 */
public class CompanyDepth {

	public static int depthCompany(Company company) {
		int maxDepth = 0;
		for (Dept dept : company.getDepts())
			maxDepth = 1 + Math.max(maxDepth, depthDept(dept));
		return maxDepth;
	}

	public static int depthDept(Dept dept) {
		int maxDepth = 0;
		for (Dept subDepartment : dept.getSubDepartments())
			maxDepth = 1 + Math.max(maxDepth, depthDept(subDepartment));
		return maxDepth;
	}

}
