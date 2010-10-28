// (C) 2009 Ralf Laemmel

package oo.company.typecase;

public class Program {

	/**
	 * Approximate some bits the company structure of MSFT.
	 * See http://www.microsoft.com/presspass/exec/leadership/ for more inspiration
	 */
	private static Company getMsft() {
		
		Employee steve = new Employee();
		steve.setName("Steve Ballmer");
		steve.setSalary(234567d);

		Employee ray = new Employee();
		ray.setName("Ray Ozzie");
		ray.setSalary(123456d);
	
		Employee bob = new Employee();
		bob.setName("Bob Muglia");
		bob.setSalary(100000d);		

		Employee ralf = new Employee();
		ralf.setName("Ralf Lammel");
		ralf.setSalary(1000d);			
		
		Company msft = new Company();
		msft.setCeo(steve);
		msft.setCsa(ray);
		
		Department dep = new Department();
		dep.setDescriptor("Server and Tools");
		dep.setManager(bob);
		dep.getUnits().add(ralf);
		msft.getDepartments().add(dep);

		return msft;
	}

	/**
	 * Compute the total of all salaries for the employees of the company
	 */
	public static double getTotal(Company c) {
		double total = 0;
		total += c.getCeo().getSalary();
		total += c.getCsa().getSalary();
		for (Department d : c.getDepartments())
			total += getTotal(d);
		return total;
	}

	/**
	 * Compute the total of all salaries for the employees of a department
	 */
	private static double getTotal(Department d) {
		double total = 0;
		total += d.getManager().getSalary();
		for (Unit u : d.getUnits()) {
			if (u instanceof Employee) {
				Employee eu = (Employee)u;
				total += eu.getSalary();
				continue;
			}
			if (u instanceof Department) {
				Department du = (Department)u;
				total += getTotal(du);
				continue;
			}
			throw new UnsupportedOperationException();
		}
		return total;
	}

	/**
	 * Increase the salaries for all employees of the company by a fixed amount
	 */
	private static void increase(Company c, double x) {
		increase(c.getCeo(),x);
		increase(c.getCsa(),x);
		for (Department d : c.getDepartments())
			increase(d,x);
	}
	
	/**
	 * Increase the salary of an employee by a fixed amount
	 */
	private static void increase(Employee e, double x) {
		e.setSalary(e.getSalary() + x);
	}

	/**
	 * Increase the salaries for all employees of a department by a fixed amount
	 */
	private static void increase(Department d, double x) {
		increase(d.getManager(),x);
		for (Unit u : d.getUnits()) {
			if (u instanceof Employee) {
				Employee eu = (Employee)u;
				increase(eu, x);
				continue;
			}
			if (u instanceof Department) {
				Department du = (Department)u;
				increase(du, x);
				continue;
			}
			throw new UnsupportedOperationException();
		}
	}
		
	public static void main(String[] args) {
		
		Company msft = getMsft();
		
		// Total of salaries before the increase
		System.out.println(getTotal(msft));

		// Recession-aware increase
		increase(msft, 1d);
		
		// Total of salaries after the increase
		System.out.println(getTotal(msft));
				
	}

}
