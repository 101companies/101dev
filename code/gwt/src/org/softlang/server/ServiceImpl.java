package org.softlang.server;

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

		Company company = new Company();

		Person craig = new Person();
		craig.setName("Craig");
		craig.setAddress("Redmond");
		Person erik = new Person();
		erik.setName("Erik");
		erik.setAddress("Utrecht");
		Person ralf = new Person();
		ralf.setName("Ralf");
		ralf.setAddress("Koblenz");
		Person ray = new Person();
		ray.setName("Ray");
		ray.setAddress("Redmond");
		Person klaus = new Person();
		klaus.setName("Klaus");
		klaus.setAddress("Boston");
		Person karl = new Person();
		karl.setName("Karl");
		karl.setAddress("Riga");
		Person joe = new Person();
		joe.setName("Joe");
		joe.setAddress("Wifi City");

		Employee eCraig = new Employee();
		Employee eErik = new Employee();
		Employee eRalf = new Employee();
		Employee eRay = new Employee();
		Employee eKlaus = new Employee();
		Employee eKarl = new Employee();
		Employee eJoe = new Employee();

		eCraig.setPerson(craig);
		eErik.setPerson(erik);
		eRalf.setPerson(ralf);
		eRay.setPerson(ray);
		eKlaus.setPerson(klaus);
		eKarl.setPerson(karl);
		eJoe.setPerson(joe);

		eCraig.setSalary(123456);
		eErik.setSalary(12345);
		eRalf.setSalary(1234);
		eRay.setSalary(234567);
		eKlaus.setSalary(23456);
		eKarl.setSalary(2345);
		eJoe.setSalary(2344);

		Dept research = new Dept();
		Subunit s01 = new Subunit();
		Subunit s02 = new Subunit();

		research.setManager(eCraig);
		research.setName("Research");
		research.getSubunits().add(s01);
		research.getSubunits().add(s02);
		s01.setPu(eErik);
		s02.setPu(eRalf);

		Dept development = new Dept();
		development.setManager(eRay);
		development.setName("Development");
		Subunit s11 = new Subunit();
		development.getSubunits().add(s11);
		Dept d11 = new Dept();
		d11.setName("Dev1");
		d11.setManager(eKlaus);
		s11.setDu(d11);
		Subunit s111 = new Subunit();
		d11.getSubunits().add(s111);
		Dept d111 = new Dept();
		d111.setName("Dev1.1");
		d111.setManager(eKarl);
		s111.setDu(d111);
		Subunit s1111 = new Subunit();
		s1111.setPu(eJoe);
		d111.getSubunits().add(s1111);

		company.getDepts().add(research);
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
