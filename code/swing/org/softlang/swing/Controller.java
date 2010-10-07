package org.softlang.swing;

import org.softlang.company.*;
import java.util.Stack;

public class Controller {

	private DeptView deptView;
	private EmployeeView employeeView;

	private Company company;
	private Stack<Dept> deptStack = new Stack<Dept>();
	private Dept openDept;
	private Employee openEmployee;

	public Controller(Company company) {
		this.company = company;
	}

	// setters
	public void setEmployeeView(EmployeeView employeeView) {
		this.employeeView = employeeView;
	}

	public void setDeptView(DeptView deptView) {
		this.deptView = deptView;
	}

	//

	public void go() {
		showCompany();
	}

	private void showCompany() {
		deptView.showCompany(company, company.total());
	}

	public void deptClicked(Dept dept) {
		showDept(dept, true);
	}

	private void showDept(Dept dept, boolean deeper) {
		if (openDept != null && deeper)
			deptStack.push(openDept);
		openDept = dept;
		deptView.showDept(dept, dept.total());
	}

	public void backDeptClicked() {
		if (deptStack.isEmpty()) {
			openDept = null;
			deptView.showCompany(company, company.total());
		} else {
			showDept(deptStack.pop(), false);
		}
	}

	public void storeBackDeptClicked(String name) {
		openDept.setName(name);
		if (deptStack.isEmpty()) {
			openDept = null;
			deptView.showCompany(company, company.total());
		} else
			showDept(deptStack.pop(), false);
	}

	public void employeeClicked(Employee employee) {
		showEmployee(employee);
	}

	public void employeeClosed() {
		openEmployee = null;
	}

	private void showEmployee(Employee employee) {
		openEmployee = employee;
		employeeView.showEmployee(employee);
	}

	public void storeEmployeeClicked(String name, String address, double salary) {
		if (openEmployee != null) {
			openEmployee.getPerson().setName(name);
			openEmployee.getPerson().setAddress(address);
			openEmployee.setSalary(salary);
		}
		deptView.showDept(openDept, openDept.total());
	}

	public void cutCompanyClicked() {
		company.cut();
		showCompany();
		if (openEmployee != null)
			showEmployee(openEmployee);
	}

	public void cutDeptClicked() {
		openDept.cut();
		showDept(openDept, false);
		if (openEmployee != null)
			showEmployee(openEmployee);
	}

	public void cutEmployeeClicked() {
		openEmployee.cut();
		showEmployee(openEmployee);
		showDept(openDept, false);
	}

}
