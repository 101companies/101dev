package org.softlang.serialization.test;

import junit.framework.Assert;

import org.junit.Test;
import org.softlang.company.Company;
import org.softlang.company.Dept;
import org.softlang.company.Employee;
import org.softlang.company.Person;
import org.softlang.company.Subunit;
import org.softlang.serialization.SerializationTool;

public class SerializationTest {

	public static Company c01 = createCompany01();
	public static Company c02 = createCompany02();

	public static Company createCompany01() {
		Company com = new Company();

		// create departments and subunits
		Dept d01 = new Dept(1);
		Dept d02 = new Dept(2);
		Dept d03 = new Dept(3);
		Dept d04 = new Dept(4);
		Subunit s01 = new Subunit(1);
		Subunit s02 = new Subunit(2);
		Subunit s03 = new Subunit(3);
		Subunit s04 = new Subunit(4);

		// create persons
		Person p01 = new Person("Ralf");
		Person p02 = new Person("Betim");
		Person p03 = new Person("Thomas");
		Person p04 = new Person("Hugo");
		Person p05 = new Person("Alice");
		Person p06 = new Person("Bob");
		Person p07 = new Person("Dominik");
		Person p08 = new Person("Martin");

		// set address
		p01.setAddress("Uni B127");
		p02.setAddress("Arenberger Str. 164");
		p03.setAddress("Markenbildchenweg 1");
		p04.setAddress("Koblenzer Str. 5");
		p05.setAddress("Lšhrstra§e 42");
		p06.setAddress("Schlossstra§e 132");
		p07.setAddress("Stegemannstra§e 54");
		p08.setAddress("Schlossstra§e 136");

		// create employees
		Employee e01 = new Employee(0);
		Employee e02 = new Employee(1);
		Employee e03 = new Employee(2);
		Employee e04 = new Employee(3);
		Employee e05 = new Employee(4);
		Employee e06 = new Employee(5);
		Employee e07 = new Employee(6);
		Employee e08 = new Employee(7);

		// make person to employee
		e01.setPerson(p01);
		e02.setPerson(p02);
		e03.setPerson(p03);
		e04.setPerson(p04);
		e05.setPerson(p05);
		e06.setPerson(p06);
		e07.setPerson(p07);
		e08.setPerson(p08);

		// set salaries
		e01.setSalary(1000);
		e02.setSalary(1000);
		e03.setSalary(1000);
		e04.setSalary(1000);
		e05.setSalary(500);
		e06.setSalary(500);
		e07.setSalary(500);
		e08.setSalary(500);

		// set name of depts
		d01.setName("SLE");
		d02.setName("TEST");
		d03.setName("EVAL");
		d04.setName("PERS");

		// set manager and pu
		d01.setManager(e01);
		d02.setManager(e02);
		d03.setManager(e03);
		d04.setManager(e04);
		s01.setPu(e05);
		s02.setPu(e06);
		s03.setPu(e07);
		s04.setPu(e08);

		d01.addSubunit(s01);
		d02.addSubunit(s02);
		d03.addSubunit(s03);
		d04.addSubunit(s04);

		com.addDept(d01);
		com.addDept(d02);
		com.addDept(d03);
		com.addDept(d04);

		// sub contains dept
		Dept dx = new Dept(10);
		dx.setName("SLE-SOU");
		Person px = new Person("Maoam");
		px.setAddress("Kšln");
		Employee ex = new Employee(10);
		ex.setPerson(px);
		ex.setSalary(850);
		dx.setManager(ex);
		s01.setDu(dx);

		return com;
	}

	public static Company createCompany02() {
		Company com = new Company();

		Dept d1 = new Dept(0);
		Dept d2 = new Dept(1);
		Subunit s1 = new Subunit(0);
		Subunit s2 = new Subunit(1);

		Person rlaemmel = new Person(0);
		Person betims = new Person(1);
		Employee rl = new Employee(0);
		Employee bs = new Employee(1);
		rl.setPerson(rlaemmel);
		bs.setPerson(betims);

		d1.addSubunit(s1);
		d1.setManager(rl);
		d2.addSubunit(s2);
		d2.setManager(bs);
		com.addDept(d1);
		com.addDept(d2);

		return com;
	}

	@Test
	public void testLoadAndCreate() {
		SerializationTool st = new SerializationTool();
	
		st.create("company01", c01);
		st.create("company02", c02);
		
		Company com01 = st.load("company01");
		Company com02 = st.load("company02");
		
		Assert.assertTrue(st.compare(c01, com01));
		Assert.assertTrue(st.compare(c02, com02));
	}
}
