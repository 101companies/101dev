package org.softlang.swing.controller;

import java.util.Stack;

import org.soflang.swing.view.*;
import org.softlang.company.*;

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
		deptView.showCompany(company);
	}

	public void deptClicked(Dept dept) {
		showDept(dept, true);
	}

	private void showDept(Dept dept, boolean deeper) {
		if (openDept != null && deeper)
			deptStack.push(openDept);
		openDept = dept;
		deptView.showDept(dept);
	}

	public void backDeptClicked() {
		if (deptStack.isEmpty()) {
			openDept = null;
			deptView.showCompany(company);
		} else {
			showDept(deptStack.pop(), false);
		}
	}

	public void storeBackDeptClicked(String name) {
		openDept.setName(name);
		if (deptStack.isEmpty()) {
			openDept = null;
			deptView.showCompany(company);
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
		deptView.showDept(openDept);
	}

}
