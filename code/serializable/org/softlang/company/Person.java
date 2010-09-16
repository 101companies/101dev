package org.softlang.company;

import java.io.Serializable;

/**
 * A person has a name and an address
 * 
 */
public class Person implements Serializable {

	private static final long serialVersionUID = -538392287385455559L;
	private int id;
	private String name;
	private String address;

	public Person() {
		id = 0;
	}

	public Person(int id) {
		this.id = id;
	}

	public Person(String name) {
		this.setName(name);
	}

	public int getPersonid() {
		return id;
	}

	public void setPersonid(int id) {
		if (this.id == 0)
			this.id = id;
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
}
