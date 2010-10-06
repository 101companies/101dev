package org.softlang.client.company;

import java.io.Serializable;

public class Subunit implements Serializable {

	private static final long serialVersionUID = -2008895922137165250L;

	private Employee pu;
	private Dept du;

	public Subunit() {
	}

	public Subunit(Employee pu) {
		this.pu = pu;
	}

	public Subunit(Dept du) {
		this.du = du;
	}

	public Employee getPu() {
		return pu;
	}

	public void setPu(Employee pu) {
		du = null;
		this.pu = pu;
	}

	public Dept getDu() {
		return du;
	}

	public void setDu(Dept du) {
		pu = null;
		this.du = du;
	}

	public boolean equals(Object o) {
		Subunit that = (Subunit) o;
		if (that.getPu() != null && this.getPu() != null)
			return this.getPu().equals(that.getPu());
		if (that.getDu() != null && this.getDu() != null)
			return this.getDu().equals(that.getDu());
		return false;

	}
}
