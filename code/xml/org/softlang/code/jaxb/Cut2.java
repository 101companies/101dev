// A variation that involves subtyping

package org.softlang.code.jaxb;

import org.softlang.company2.*;

public class Cut2 {
	
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
					visit(s);
		}
	}
	
	public static void visit(Employee e) {
		if (e != null)
			e.setSalary(e.getSalary() / 2);
	}
		
	// This is where subtyping kicks in.
	public static void visit(Subunit s) {
		if (s instanceof Pu)
			visit((Pu)s);
		if (s instanceof Du)
			visit((Du)s);
		throw new IllegalArgumentException();		
	}
}
