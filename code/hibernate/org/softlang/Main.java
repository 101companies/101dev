package org.softlang;

import org.softlang.om.*;

public class Main {

	public static void main(String[] args) {

		// Comment out the following to just initialize the database

		HibernateConnectivity hc = new HibernateConnectivity();

		System.out.println("Loading company...");
		Company company = hc.loadCompany();
		System.out.println("Done.");

		System.out.println("Total salary = " + Total.totalCompany(company));

		System.out.println("Cutting...");
		Cut.cutCompany(company);
		System.out.println("Done.");

		System.out.println("Save company...");
		hc.saveCompany(company);
		System.out.println("Done.");

		System.out.println("Reload Company...");
		company = hc.loadCompany();
		System.out.println("Done.");

		System.out.println("New total salary = " + Total.totalCompany(company));
	}
}
