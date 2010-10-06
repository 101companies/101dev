package org.softlang.jaxb;

import org.softlang.company.*;

public class Total {
	
	public static double getTotal(Company c) {
		double total = 0;
		if (c.getDept() != null)
			for (Dept d : c.getDept())
				total += getTotal(d);
		return total;
	}
	
	public static double getTotal(Dept d) {
		double total = 0;
		if (d != null) {
			total += getTotal(d.getManager());
			if (d.getSubunit() != null)
				for (Subunit s : d.getSubunit())
					total += getTotal(s);
		}
		return total;		
	}
	
	public static double getTotal(Employee e) {
		double total = 0;
		if (e != null)
			total += e.getSalary();
		return total;
	}
	
	public static double getTotal(Subunit s) {
		double total = 0;
		total += getTotal(s.getDu());
		total += getTotal(s.getPu());
		return total;
	}
}
