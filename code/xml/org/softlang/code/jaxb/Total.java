package org.softlang.code.jaxb;

import org.softlang.company.*;

public class Total {
	
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
	
	public static double compute(Subunit s) {
		double total = 0;
		total += compute(s.getDu());
		total += compute(s.getPu());
		return total;
	}
}
