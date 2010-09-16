package org.softlang.company;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A company is a list of departments
 */
public class Company implements Serializable {

	private static final long serialVersionUID = -8842555369765243044L;
	private List<Dept> depts;

	public Company() {
		depts = new LinkedList<Dept>();
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void addDept(Dept dept) {
		depts.add(dept);
	}

	public void removeDept(int index) {
		depts.remove(index);
	}
}
