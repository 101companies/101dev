// (C) 2009 Ralf Laemmel

package oo.company.visitor;

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
	private static double getTotal(Company c) {
		TotalVisitor v = new TotalVisitor();
		c.accept(v);
		return v.total;
	}

	/**
	 * Increase the salaries for all employees of the company by a fixed amount
	 */
	private static void increase(Company c, double x) {
		IncreaseVisitor v = new IncreaseVisitor();
		v.increase = x;
		c.accept(v);
	}

	/**
	 * Cut all manager salaries
	 */
	private static void cut(Company c) {
		CutVisitor v = new CutVisitor();
		c.accept(v);
	}
		
	public static void main(String[] args) {
		
		Company msft = getMsft();
		
		// Total of salaries before the increase
		System.out.println(getTotal(msft));

		// Recession-aware increase
		increase(msft, 1d);
		
		// Total of salaries after the increase
		System.out.println(getTotal(msft));

		// Cut all manager salaries
		cut(msft);

		// Total of salaries after the cut
		System.out.println(getTotal(msft));
				
	}

}
