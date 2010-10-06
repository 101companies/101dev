package org.softlang.client;

import org.softlang.client.company.*;
import org.softlang.client.util.ResultPair;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	Company getCompany();
	
	void saveCompany(Company company);

	// Total
	Double getCompanyTotal(Company company);

	Double getDeptTotal(Dept dept);

	// Cut
	ResultPair<Company> cutCompany(Company company);

	ResultPair<Dept> cutDept(Dept dept);

	ResultPair<Employee> cutEmployee(Employee employee);

}
