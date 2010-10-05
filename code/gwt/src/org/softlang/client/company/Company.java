package org.softlang.client.company;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {

	private static final long serialVersionUID = -200889592677165250L;
	private List<Dept> depts;

	public Company() {
	}

	public Company(List<Dept> depts) {
		this.depts = depts;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public boolean equals(Object o) {
		Company that = (Company) o;
		if (this.getDepts().size() != that.getDepts().size())
			return false;
		for (int i = 0; i < this.getDepts().size(); i++)
			if (!this.getDepts().get(i).equals(that.getDepts().get(i)))
				return false;
		return true;
	}

}
