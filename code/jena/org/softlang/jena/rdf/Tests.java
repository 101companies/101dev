package org.softlang.jena.rdf;

import org.softlang.company.CompanyModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tests {

	public static final String sampleCompany = "sampleCompany.rdf";

	public static void main(String[] args) throws FileNotFoundException {
		CompanyModel sample = new CompanyModel();
		sample.getModel().read(new FileInputStream(sampleCompany), null);

		System.out.println("> No \"unfair\" salaries? \n"
				+ SalaryCheck.checkSalaries(sample));
		System.out.println("> Salary Total  \n" + Total.total(sample));
		System.out.println("> No mentor with more than one mentee? \n"
				+ MentorCheck.checkMentors(sample));
		System.out.println("> Cut!");
		Cut.cut(sample);
		System.out.println("done");
		System.out.println("> Salary Total  \n" + Total.total(sample));
		System.out.println();
	}
}
