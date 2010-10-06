package org.softlang.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Total {

	public static double getTotal(MyConnection myConnection) {
		double total = 0;
		try {
			// get all salaries and total 
			String sqlSalaries = "SELECT salary FROM employee";
			PreparedStatement pstmtEmployees = myConnection.getConn()
					.prepareStatement(sqlSalaries);
			ResultSet salaries = pstmtEmployees.executeQuery();
			while (salaries.next())
				total += salaries.getDouble("salary");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
}
