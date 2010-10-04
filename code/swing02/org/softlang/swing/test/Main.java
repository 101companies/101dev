package org.softlang.swing.test;

import org.softlang.company.*;
import org.softlang.swing.*;

public class Main {

	public static Company createCompany() {

		Company com = new Company();

		
		
		Person craig = new Person("Craig");
		craig.setAddress("Redmond");
		Person erik = new Person("Erik");
		erik.setAddress("Utrecht");
		Person ralf = new Person("Ralf");
		ralf.setAddress("Koblenz");
		Person ray = new Person("Ray");
		ray.setAddress("Redmond");
		Person klaus = new Person("Klaus");
		klaus.setAddress("Boston");
		Person karl = new Person("Karl");
		karl.setAddress("Riga");
		Person joe = new Person("Joe");
		joe.setAddress("Wifi City");
		
		Employee eCraig = new Employee();
		Employee eErik = new Employee();
		Employee eRalf = new Employee();
		Employee eRay = new Employee();
		Employee eKlaus = new Employee();
		Employee eKarl = new Employee();
		Employee eJoe = new Employee();
		
		eCraig.setPerson(joe);
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
		research.addSubunit(s01);
		research.addSubunit(s02);
		s01.setPu(eErik);
		s02.setPu(eRalf);

		
		Dept development = new Dept();
		development.setManager(eRay);
		development.setName("Development");
		Subunit s11 = new Subunit();
		development.addSubunit(s11);
		Dept d11 = new Dept();
		d11.setName("Dev1");
		d11.setManager(eKlaus);
		s11.setDu(d11);
		Subunit s111 = new Subunit();
		d11.addSubunit(s111);
		Dept d111 = new Dept();
		d111.setName("Dev1.1");
		d111.setManager(eKarl);
		s111.setDu(d111);
		Subunit s1111 = new Subunit();
		s1111.setPu(eJoe);
		d111.addSubunit(s1111);
		
		com.addDept(research);
		com.addDept(development);
		return com;
	}

	public static void main(String[] args) {
		new MainView(createCompany());
	}
}
