package org.softlang.client.company;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -210889592677165250L;

	private Person person;
	private double salary;

	public Employee() {
	}

	public Employee(Person person, double salary) {
		this.person = person;
		this.salary = salary;
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

	public boolean equals(Object o) {
		Employee that = (Employee) o;
		return (this.getSalary() == that.getSalary() && this.getPerson()
				.equals(that.getPerson()));
	}

}
