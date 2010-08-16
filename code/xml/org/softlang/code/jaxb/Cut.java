package org.softlang.code.jaxb;

import org.softlang.company.*;

public class Cut {
	
	public static void visit(Company c) {
		if (c.getDept() != null)
			for (Dept d : c.getDept())
				visit(d);
	}
	
	public static void visit(Dept d) {
		if (d != null) {
			visit(d.getManager());
			if (d.getSubunit() != null)
				for (Subunit s : d.getSubunit())
					run(s);
		}
	}
	
	public static void visit(Employee e) {
		if (e != null)
			e.setSalary(e.getSalary() / 2);
	}
	
	public static void run(Subunit s) {
		visit(s.getDu());
		visit(s.getPu());
	}
}
