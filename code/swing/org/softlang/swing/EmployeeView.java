package org.softlang.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.softlang.company.Employee;

public class EmployeeView {

	private final Controller controller;

	private final JFrame frame;
	private final JTextField nameField;
	private final JTextField addressField;
	private final JTextField salaryField;

	public EmployeeView(Controller controller) {
		this.controller = controller;
		frame = new JFrame();
		nameField = new JTextField();
		addressField = new JTextField();
		salaryField = new JTextField();
		init();
	}

	public void showEmployee(Employee employee) {
		frame.setTitle("Employee  \"" + employee.getPerson().getName() + "\"");
		nameField.setText(employee.getPerson().getName());
		addressField.setText(employee.getPerson().getAddress());
		salaryField.setText(Double.toString(employee.getSalary()));
		frame.setVisible(true);
	}

	public void init() {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				controller.employeeClosed();
				frame.setVisible(false);
			}
		});
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		JPanel infoPanel = new JPanel(new GridLayout(3, 1, 30, 30));

		JPanel namePanel = new JPanel(new GridLayout(1, 2, 0, 10));
		JLabel nameLabel = new JLabel("Name:");
		namePanel.add(nameLabel);
		nameField.setColumns(10);
		namePanel.add(nameField);
		infoPanel.add(namePanel);

		JPanel addressPanel = new JPanel(new GridLayout(1, 2, 0, 10));
		JLabel addressLabel = new JLabel("Address:");
		addressPanel.add(addressLabel);
		addressPanel.add(addressField);
		addressField.setColumns(10);
		infoPanel.add(addressPanel);

		JPanel salaryPanel = new JPanel(new GridLayout(1, 2, 0, 10));
		JLabel salaryLabel = new JLabel("Salary:");
		salaryPanel.add(salaryLabel);
		salaryPanel.add(salaryField);
		salaryField.setColumns(10);
		infoPanel.add(salaryPanel);

		panel.add(infoPanel);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 30, 30));
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double newSalary = 0.0;
				try {
					newSalary = Double.valueOf(salaryField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame,
							"Salary-entry is not a double value!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				controller.storeEmployeeClicked(nameField.getText(),
						addressField.getText(), newSalary);
				frame.setVisible(false);
			}
		});
		buttonPanel.add(okButton);

		panel.add(buttonPanel);

		frame.getContentPane().add(panel);
		frame.setSize(290, 250);
		frame.setLocation(200, 200);
		frame.setResizable(false);

	}
}
