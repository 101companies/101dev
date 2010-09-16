package org.softlang;

import org.hibernate.Session;
import org.softlang.om.*;
import org.softlang.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		// Comment out the following to just initialize the database

		HibernateConnectivity hc = new HibernateConnectivity(session);
		System.out.println("Loading company...");
		Company company = hc.loadCompany();
		System.out.println("Done.");
		System.out.println("Total salary = " + Total.totalCompany(company));
		System.out.println("Cutting...");
		Cut.cutCompany(company);
		System.out.println("Done.");
		System.out.println("Save company...");
		System.out.println("Reload Company.");
		company = hc.loadCompany();
		System.out.println("New total salary = " + Total.totalCompany(company));
		hc.saveCompany(company);
	}
}
