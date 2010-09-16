package org.softlang.company;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A department has a name, a manager and a list of subunits
 * 
 */
public class Dept implements Serializable {

	private static final long serialVersionUID = 3286725847863678814L;
	private int id;
	private String name;
	private Employee manager;
	private List<Subunit> subunits;

	public Dept() {
		id = 0;
		subunits = new LinkedList<Subunit>();
	}

	public Dept(int id) {
		this.id = id;
		subunits = new LinkedList<Subunit>();
	}

	public int getDeptid() {
		return id;
	}

	public void setDeptid(int id) {
		if (this.id == 0)
			this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Subunit> getSubunits() {
		return subunits;
	}

	public void addSubunit(Subunit subunit) {
		subunits.add(subunit);
	}

	public void removeSubunit(int index) {
		subunits.remove(index);
	}
}
