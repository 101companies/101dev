// (C) 2009 Ralf Laemmel

package oo.company.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator for employees of companies
 */
/* package */ class Employees implements Iterator<Employee> {
	
	/*
	 * A stack of objects that are still to be visited.
	 * Objects of the following types can be on the stack:
	 * (i) Employee;
	 * (ii) Iterator<Department> (due to Company);
	 * (iii) Iterator<Unit> (due to Department).
	 */
	private Stack<Object> stack = new Stack<Object>();
	
	/**
	 * Construct an iterator from a company
	 */	
	/* package */ Employees(Company c) {
		stack.push(c.getDepartments().iterator());
		stack.push(c.getCsa());
		stack.push(c.getCeo());
	}

	/**
	 * Construct an iterator from a department
	 */	
	/* package */ Employees(Department d) {
		stack.push(d.getUnits().iterator());
		stack.push(d.getManager());		
	}	

	/**
	 * Push a unit on the iterator stack
	 */	
	private void pushUnit(Unit u) {
		if (u instanceof Department) {
			Department d = (Department)u;
			stack.push(d.getUnits().iterator());
			stack.push(d.getManager());		
			return;
		}
		if (u instanceof Employee) {
			Employee e = (Employee)u;
			stack.push(e);
			return;
		}		
	}
	
	/**
	 * Test whether there is another employee
	 */	
	public boolean hasNext() {
		pull();
		return !stack.isEmpty();
	}
	
	/**
	 * Return the next employee
	 */	
	public Employee next() {
		pull();
		if (stack.isEmpty())
			throw new NoSuchElementException();
		return (Employee)stack.pop();
	}

	/**
	 * Pull from the iterated structure to find the next employee
	 */		
	@SuppressWarnings("unchecked")
	private void pull() {
		while (true) {
			if (stack.isEmpty())
				return;
			if (stack.peek() instanceof Employee)
				return;
			if (stack.peek() instanceof Iterator) {
				Iterator i = (Iterator)stack.peek();
				if (i.hasNext()) {
					Unit u = (Unit)i.next();
					pushUnit(u);
				}
				else
					stack.pop();
			}
		}
	}	
	
	/**
	 * Removal is unsupported for this iterator
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
