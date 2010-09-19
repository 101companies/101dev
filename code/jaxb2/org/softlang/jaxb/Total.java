// A variation that involves subtyping

package org.softlang.jaxb;

import org.softlang.company.*;

public class Total {
	
	public static double aggregate(Company c) {
		double total = 0;
		if (c.getDept() != null)
			for (Dept d : c.getDept())
				total += aggregate(d);
		return total;
	}
	
	public static double aggregate(Dept d) {
		double total = 0;
		if (d != null) {
			total += aggregate(d.getManager());
			if (d.getSubunit() != null)
				for (Subunit s : d.getSubunit())
					total += aggregate(s);
		}
		return total;		
	}
	
	public static double aggregate(Employee e) {
		double total = 0;
		if (e != null)
			total += e.getSalary();
		return total;
	}

	// This is where subtyping kicks in.
	public static double aggregate(Subunit s) {
		if (s instanceof Pu)
			return aggregate(((Pu)s).getEmployee());
		if (s instanceof Du)
			return aggregate(((Du)s).getDept());
		throw new IllegalArgumentException();
	}
}
