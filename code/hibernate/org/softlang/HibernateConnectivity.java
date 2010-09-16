package org.softlang;

import java.util.List;
import org.hibernate.Session;
import org.softlang.om.*;
import org.softlang.util.HibernateUtil;

public class HibernateConnectivity {

	private Session session;

	public HibernateConnectivity() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public Company loadCompany() {
		if (!session.isOpen())
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Company company = new Company();
		// get all top departments
		List<?> depts = session.createQuery(
				"from Dept where UPPER_DEPT_ID is null").list();
		// add them to the company's top department list
		for (Object o : depts)
			company.getDepts().add((Dept) o);
		return company;
	}

	public void saveCompany(Company company) {
		// save company by saving all top departments
		for (Dept dept : company.getDepts()) {
			session.save(dept);
		}
		session.getTransaction().commit();

	}

}
