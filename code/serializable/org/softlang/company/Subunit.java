package org.softlang.company;

import java.io.Serializable;

/**
 * A subunit is either a person unit or a department unit
 * 
 */
public class Subunit implements Serializable {

	private static final long serialVersionUID = 4404891368793247786L;
	private int id;
	private Employee pu;
	private Dept du;

	public Subunit() {
		id = 0;
	}

	public Subunit(int id) {
		this.id = id;
	}

	public int getSubunitid() {
		return id;
	}

	public void setSubunitid(int id) {
		if (this.id == 0)
			this.id = id;
	}

	public Employee getPu() {
		return pu;
	}

	public void setPu(Employee pu) {
		this.pu = pu;
	}

	public Dept getDu() {
		return du;
	}

	public void setDu(Dept du) {
		this.du = du;
	}
}
