package org.softlang.plain;

public class Main {

	public static void main(String[] args) {

		MyConnection myConnection = new MyConnection(
				"mysqlhost.uni-koblenz.de", "company", 3306, "tschmorleiz",
				"stacky");

		myConnection.connect();

		System.out.println("Total = " + Total.total(myConnection));
		System.out.println("Company depth = "
				+ CompanyDepth.depthCompany(myConnection));

		System.out.print("Cutting... ");
		Cut.cut(myConnection);
		System.out.println("Done");

		System.out.println("New total = " + Total.total(myConnection));

	}

}
