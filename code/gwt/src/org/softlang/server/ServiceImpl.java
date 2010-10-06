package org.softlang.server;

import java.util.LinkedList;

import org.softlang.client.*;
import org.softlang.client.company.*;
import org.softlang.client.util.ResultPair;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	private Company company;

	public ServiceImpl() {
		Company company = new Company(new LinkedList<Dept>());

		Dept research = new Dept("Research", new Employee(new Person("Craig",
				"Redmond"), 123456.0), new LinkedList<Subunit>());
		research.getSubunits().add(
				new Subunit(
						new Employee(new Person("Erik", "Utrecht"), 12345.0)));
		research.getSubunits()
				.add(
						new Subunit(new Employee(new Person("Ralf", "Koblenz"),
								1234.0)));
		company.getDepts().add(research);

		Dept development = new Dept("Development", new Employee(new Person(
				"Ray", "Redmond"), 234567.0), new LinkedList<Subunit>());

		Dept dev1 = new Dept("Dev1", new Employee(
				new Person("Klaus", "Boston"), 23456.0),
				new LinkedList<Subunit>());

		Dept dev11 = new Dept("Dev1.1", new Employee(
				new Person("Karl", "Riga"), 2345.0), new LinkedList<Subunit>());
		dev11.getSubunits().add(
				new Subunit(
						new Employee(new Person("Joe", "Wifi City"), 2344.0)));
		dev1.getSubunits().add(new Subunit(dev11));
		development.getSubunits().add(new Subunit(dev1));
		development.getSubunits().add(
				new Subunit(new Employee(new Person("William", "New York"),
						234566.0)));

		company.getDepts().add(development);
		this.company = company;

	}

	public Company getCompany() {
		return company;

	}

	// Total
	@Override
	public Double getCompanyTotal(Company company) {
		return Total.totalCompany(company);
	}

	@Override
	public Double getDeptTotal(Dept dept) {
		return Total.totalDept(dept);
	}

	// Cut
	@Override
	public ResultPair<Company> cutCompany(Company company) {
		Cut.cutCompany(company);
		return new ResultPair<Company>(company, Total.totalCompany(company));

	}

	@Override
	public ResultPair<Dept> cutDept(Dept dept) {
		Cut.cutDept(dept);
		return new ResultPair<Dept>(dept, Total.totalDept(dept));

	}

	@Override
	public ResultPair<Employee> cutEmployee(Employee employee) {
		Cut.cutEmployee(employee);
		return new ResultPair<Employee>(employee, Total.totalEmployee(employee));

	}

	@Override
	public void saveCompany(Company company) {
		this.company = company;

	}
}
