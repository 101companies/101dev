package org.softlang.company;

import java.io.Serializable;

/**
 * An Employee has a salary and some person information
 * 
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = -5270894415042869399L;
	private int id;
	private Person person;
	private double salary;

	public Employee() {
		id = 0;
	}

	public Employee(int id) {
		this.id = id;
	}

	public Employee(int salary, Person p) {
		id = 0;
		this.setSalary(salary);
		this.setPerson(p);
	}

	public int getEmployeeid() {
		return id;
	}

	public void setEmploeeid(int id) {
		if (this.id == 0)
			this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
