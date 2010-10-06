package org.softlang.company;

import org.softlang.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * We build a company from SQL Data.
 * 
 */
public class ObjectFactory {

	private MyConnection myConnection;

	public ObjectFactory(MyConnection myConnection) {
		this.myConnection = myConnection;
		if (!myConnection.getIsConnected())
			myConnection.connect();

	}

	public Company createCompany() {
		Company company = new Company();
		try {
			// get all "top departments" (departments with no upper department)
			String sqlDepts = "SELECT deptId FROM dept WHERE upperDeptId IS NULL";
			PreparedStatement pstmtDepts = myConnection.getConn()
					.prepareStatement(sqlDepts);
			ResultSet deptIdsR = pstmtDepts.executeQuery();
			// create each department from it's database primary key and add it
			// the company's department list
			while (deptIdsR.next()) {
				company.getDepts().add(createDept(deptIdsR.getInt("deptId")));
			}
			// reset flags
			company.setUnchanged();
			company.getDepts().setUnchanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public Dept createDept(int deptId) {
		Dept dept = null;
		try {
			// get department entry from database
			String sqlDept = "SELECT * FROM dept WHERE deptId = ?";
			PreparedStatement pstmtDept = myConnection.getConn()
					.prepareStatement(sqlDept);
			pstmtDept.setInt(1, deptId);
			ResultSet deptR = pstmtDept.executeQuery();
			deptR.next();
			// create new department
			dept = new Dept(deptId);
			// add name and manager
			dept.setName(deptR.getString("name"));
			Employee manager = createEmployee(deptR.getInt("managerId"));
			dept.setManager(manager);
			// get all department's employees
			String sqlEmployees = "SELECT employeeId FROM employee WHERE deptId = ?";
			PreparedStatement pstmtEmployees = myConnection.getConn()
					.prepareStatement(sqlEmployees);
			pstmtEmployees.setInt(1, deptId);
			ResultSet employeesR = pstmtEmployees.executeQuery();
			while (employeesR.next()) {
				Employee employee = createEmployee(employeesR
						.getInt("employeeId"));
				dept.getEmployees().add(employee);
			}
			// get all sub departments
			String sqlSubDepts = "SELECT deptId FROM dept WHERE upperDeptId = ?";
			PreparedStatement pstmtSubDepts = myConnection.getConn()
					.prepareStatement(sqlSubDepts);
			pstmtSubDepts.setInt(1, deptId);
			ResultSet subDeptsR = pstmtSubDepts.executeQuery();
			while (subDeptsR.next()) {
				Dept subDept = createDept(subDeptsR.getInt("deptId"));
				dept.getSubDepartments().add(subDept);
			}
			// reset flags
			dept.setUnchanged();
			dept.getSubDepartments().setUnchanged();
			dept.getEmployees().setUnchanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	public Employee createEmployee(int employeeId) {
		Employee employee = null;
		try {
			// get employee entry from database
			String sqlEmployee = "SELECT * FROM employee WHERE employeeId = ?";
			PreparedStatement pstmtEmployee = myConnection.getConn()
					.prepareStatement(sqlEmployee);
			pstmtEmployee.setInt(1, employeeId);
			ResultSet employeeR = pstmtEmployee.executeQuery();
			employeeR.next();
			// create new employee
			employee = new Employee(employeeId);
			// set salary and person information
			employee.setSalary(employeeR.getDouble("salary"));
			employee.setName(employeeR.getString("name"));
			employee.setAddress(employeeR.getString("address"));
			// reset flag
			employee.setUnchanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
