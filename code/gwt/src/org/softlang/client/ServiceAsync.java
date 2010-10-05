package org.softlang.client;

import org.softlang.client.company.*;
import org.softlang.client.util.ResultPair;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>Service</code>.
 */
public interface ServiceAsync {
	void getCompany(AsyncCallback<Company> callback);

	void saveCompany(Company company, AsyncCallback<Void> callback);

	// Total
	void getCompanyTotal(Company company, AsyncCallback<Double> callback);

	void getDeptTotal(Dept dept, AsyncCallback<Double> callback);

	// Cut
	void cutCompany(Company company, AsyncCallback<ResultPair<Company>> callback);

	void cutDept(Dept dept, AsyncCallback<ResultPair<Dept>> callback);

	void cutEmployee(Employee employee,
			AsyncCallback<ResultPair<Employee>> callback);

}
