package org.softlang.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.softlang.company.*;

public class DeptView implements View {

	private Dept d;
	JFrame frame;
	JPanel panel;
	JList list;
	int line = 0;
	ListModel subunits;

	public DeptView(Dept d) {
		this.setD(d);
		createGui();
	}

	@Override
	public void createGui() {

		setConfig();
		
		JLabel label1 = new JLabel("Department:");
		label1.setBounds(10, (line += 20), 150, 20);
		this.panel.add(label1);
		JTextField tf1 = new JTextField(01);
		tf1.setBounds(10, (line += 20), 150, 20);
		tf1.setText(d.getName());
		this.panel.add(tf1);

		createEmployee(d.getManager());

		for (Subunit sub : d.getSubunits()) {
			createSub(sub);
		}

		JButton jbn2 = new JButton("<<");
		jbn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		jbn2.setBounds(10, (line += 30), 50, 30);
		this.panel.add(jbn2);

		this.panel.setLayout(null);
		this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.frame.setVisible(true);
	}

	public void setConfig() {
		this.frame = new JFrame("Dept: " + d.getName());
		this.frame.setSize(200, 200);
		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.panel = new JPanel();
		this.list = new JList();
	}

	public void createSub(final Subunit sub) {
		JButton button = new JButton("Subunit-ID"
				+ String.valueOf(sub.getSubunitid()));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SubunitView(sub);
			}
		});
		button.setBounds(10, (line += 30), 150, 30);
		this.panel.add(button);
	}

	public void createEmployee(final Employee empl) {
		JButton button = new JButton("Manager");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeView(empl);
			}
		});
		button.setBounds(10, (line += 30), 150, 30);
		this.panel.add(button);
	}

	public void setD(Dept d) {
		this.d = d;
	}

	public Dept getD() {
		return d;
	}

}
