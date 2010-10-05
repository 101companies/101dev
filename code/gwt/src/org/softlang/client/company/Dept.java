package org.softlang.client.company;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {

	private static final long serialVersionUID = -2008895922177165250L;
	private String name;
	private Employee manager;
	private List<Subunit> subunits;

	public Dept() {
	}

	public Dept(String name, Employee manager, List<Subunit> subunits) {
		this.name = name;
		this.manager = manager;
		this.subunits = subunits;
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

	public void setSubunits(List<Subunit> subunits) {
		this.subunits = subunits;
	}

	public boolean equals(Object o) {
		Dept that = (Dept) o;
		if (this.getManager() == null) {
			if (that.getManager() != null)
				return false;
		} else if (that.getManager() == null)
			return false;
		else if (!this.getManager().equals(that.getManager()))
			return false;

		if (this.getSubunits().size() != that.getSubunits().size())
			return false;
		for (int i = 0; i < this.getSubunits().size(); i++)
			if (!this.getSubunits().get(i).equals(that.getSubunits().get(i)))
				return false;
		return true;
	}

}
