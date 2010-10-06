package org.softlang.swing;

import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.softlang.company.Company;
import org.softlang.company.Dept;
import org.softlang.swing.test.Main;

public class CompanyGUI {

	JFrame frame;

	Stack<Dept> deptStack = new Stack<Dept>();

	public CompanyGUI(Company company) {
		frame = new JFrame("Company : Top Departments");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 500);
		frame.setLocation(200, 200);
		JPanel navPanel = new JPanel();

		frame.add(navPanel);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CompanyGUI(Main.createCompany());
	}

}
