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
	
	public static double aggregate(Subunit s) {
		double total = 0;
		total += aggregate(s.getDu());
		total += aggregate(s.getPu());
		return total;
	}
}
