package org.softlang.jaxb;

import org.softlang.company.*;

public class Cut {
	
	public static void transform(Company c) {
		if (c.getDept() != null)
			for (Dept d : c.getDept())
				transform(d);
	}
	
	public static void transform(Dept d) {
		if (d != null) {
			transform(d.getManager());
			if (d.getSubunit() != null)
				for (Subunit s : d.getSubunit())
					transform(s);
		}
	}
	
	public static void transform(Employee e) {
		if (e != null)
			e.setSalary(e.getSalary() / 2);
	}
	
	public static void transform(Subunit s) {
		transform(s.getDu());
		transform(s.getPu());
	}
}
