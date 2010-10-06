package org.soflang.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.softlang.company.Company;
import org.softlang.company.Dept;
import org.softlang.company.Subunit;
import org.softlang.swing.controller.Controller;

public class DeptView {

	private final Controller controller;
	private final JPanel namePanel, managerPanel, employeePanel, subDeptPanel,
			navPanel;
	private final JFrame frame;
	private final JTextField nameField;
	private JButton managerButton;
	private final JList employeeList;
	private final DefaultListModel employeeListModel;
	private final JLabel subDeptLabel;
	private final JList subDeptList;
	private final DefaultListModel subDeptListModel;

	public DeptView(Controller controller) {
		this.controller = controller;
		frame = new JFrame();
		namePanel = new JPanel(new GridLayout(1, 2, 0, 10));
		managerPanel = new JPanel(new GridLayout(1, 2, 66, 10));
		employeePanel = new JPanel(new GridLayout(1, 2, 30, 10));
		subDeptPanel = new JPanel(new GridLayout(1, 2, 30, 10));
		navPanel = new JPanel(new GridLayout(1, 2, 30, 10));
		subDeptLabel = new JLabel();
		nameField = new JTextField();
		managerButton = new JButton();
		employeeListModel = new DefaultListModel();
		subDeptListModel = new DefaultListModel();
		employeeList = new JList(employeeListModel);
		subDeptList = new JList(subDeptListModel);
		init();
	}

	public void showCompany(final Company company) {
		setNonTopPanelVisibilty(false);
		removeListener();
		subDeptListModel.clear();
		frame.setTitle("Company");
		subDeptLabel.setText("Top departments: ");
		for (Dept dept : company.getDepts())
			addDept(dept);
		frame.setVisible(true);

	}

	public void showDept(final Dept dept) {
		setNonTopPanelVisibilty(true);
		removeListener();
		employeeListModel.clear();
		subDeptListModel.clear();
		frame.setTitle("Department  \"" + dept.getName() + "\"");
		subDeptLabel.setText("Sub departments: ");
		nameField.setText(dept.getName());
		managerButton.setText(dept.getManager().getPerson().getName());
		if (managerButton.getActionListeners().length != 0)
			managerButton.removeActionListener(managerButton
					.getActionListeners()[0]);
		managerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.employeeClicked(dept.getManager());
			}
		});
		for (Subunit subunit : dept.getSubunits()) {
			addSubunit(subunit);
		}
	}

	private void removeListener() {
		for (ActionListener al : managerButton.getActionListeners())
			managerButton.removeActionListener(al);
		for (ListSelectionListener lsl : employeeList
				.getListSelectionListeners())
			employeeList.removeListSelectionListener(lsl);
		for (ListSelectionListener lsl : subDeptList
				.getListSelectionListeners())
			subDeptList.removeListSelectionListener(lsl);
	}

	private void setNonTopPanelVisibilty(boolean visibility) {
		namePanel.setVisible(visibility);
		managerPanel.setVisible(visibility);
		employeePanel.setVisible(visibility);
		navPanel.setVisible(visibility);
	}

	private void addSubunit(final Subunit subunit) {
		if (subunit.getPu() != null) {
			final int newIndex = employeeListModel.size();
			employeeListModel.add(newIndex, subunit.getPu().getPerson()
					.getName());
			employeeList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (employeeList.getSelectedIndex() == newIndex)
						controller.employeeClicked(subunit.getPu());

				}
			});
		} else {
			final int newIndex = subDeptListModel.size();
			subDeptListModel.add(newIndex, subunit.getDu().getName());
			subDeptList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (subDeptList.getSelectedIndex() == newIndex)
						controller.deptClicked(subunit.getDu());

				}
			});
		}
	}

	private void addDept(final Dept dept) {
		final int newIndex = subDeptListModel.size();
		subDeptListModel.add(newIndex, dept.getName());
		subDeptList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (subDeptList.getSelectedIndex() == newIndex)
					controller.deptClicked(dept);

			}
		});

	}

	private void init() {
		frame.setSize(300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));

		JLabel nameLabel = new JLabel("Name:");
		namePanel.add(nameLabel);
		nameField.setColumns(10);
		namePanel.add(nameField);
		nameField.setPreferredSize(new Dimension(10, 20));
		panel.add(namePanel);

		JLabel managerLabel = new JLabel("Manager:");
		managerPanel.add(managerLabel);
		managerButton.setPreferredSize(new Dimension(80, 25));
		managerPanel.add(managerButton);
		panel.add(managerPanel);

		JLabel employeesLabel = new JLabel("Employees:");
		employeePanel.add(employeesLabel);
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane emplScrollPane = new JScrollPane(employeeList);
		emplScrollPane.setPreferredSize(new Dimension(100, 100));
		employeePanel.add(emplScrollPane);
		panel.add(employeePanel);

		subDeptPanel.add(subDeptLabel);
		subDeptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane subDeptScrollPane = new JScrollPane(subDeptList);
		subDeptScrollPane.setPreferredSize(new Dimension(80, 100));
		subDeptPanel.add(subDeptScrollPane);
		panel.add(subDeptPanel);

		JButton backCancelButton = new JButton("Up & Save");
		backCancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.backDeptClicked();
			}
		});
		navPanel.add(backCancelButton);
		JButton backSaveButton = new JButton("Up & Cancel");
		backSaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.storeBackDeptClicked(nameField.getText());
			}
		});
		navPanel.add(backSaveButton);
		panel.add(navPanel);

		frame.getContentPane().add(panel);
		frame.setLocation(170, 250);
		frame.setSize(290, 500);
		frame.setResizable(false);
	}

}
