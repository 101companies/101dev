package org.softlang.client.company;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Company implements Serializable {

	private static final long serialVersionUID = -200889592677165250L;
	private List<Dept> depts;

	public Company() {
		depts = new LinkedList<Dept>();
	}

	public List<Dept> getDepts() {
		return depts;
	}
}
