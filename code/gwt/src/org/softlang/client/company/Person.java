package org.softlang.client.company;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -200889592677165150L;

	private String name;
	private String address;

	public Person() {

	}

	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object o) {
		Person that = (Person) o;
		return (this.getName().equals(that.getName()) && this.getAddress()
				.equals(that.getAddress()));
	}

}
