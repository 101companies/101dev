// A variation that involves subtyping

package org.softlang.code.jaxb;

import org.softlang.company2.*;

public class Total2 {
	
	public static double compute(Company c) {
		double total = 0;
		if (c.getDept() != null)
			for (Dept d : c.getDept())
				total += compute(d);
		return total;
	}
	
	public static double compute(Dept d) {
		double total = 0;
		if (d != null) {
			total += compute(d.getManager());
			if (d.getSubunit() != null)
				for (Subunit s : d.getSubunit())
					total += compute(s);
		}
		return total;		
	}
	
	public static double compute(Employee e) {
		double total = 0;
		if (e != null)
			total += e.getSalary();
		return total;
	}

	// This is where subtyping kicks in.
	public static double compute(Subunit s) {
		if (s instanceof Pu)
			return compute((Pu)s);
		if (s instanceof Du)
			return compute((Du)s);
		throw new IllegalArgumentException();
	}
}
