// (C) 2009 Ralf Laemmel

package oo.company.virtual;

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
		
	public static void main(String[] args) {
		
		Company msft = getMsft();
		
		// Total of salaries before the increase
		System.out.println(msft.getTotal());

		// Recession-aware increase
		msft.increase(1d);
		
		// Total of salaries after the increase
		System.out.println(msft.getTotal());

		// Salary cut for top-level managers
		msft.cut();

		// Total of salaries after salary cut
		System.out.println(msft.getTotal());
						
	}

}
