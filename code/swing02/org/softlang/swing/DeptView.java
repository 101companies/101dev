package org.softlang.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.softlang.company.Dept;

public class DeptView {

	private Dept dept;
	int line = 0;
	JFrame frame;
	JPanel panel;
	
	public DeptView(Dept d) {
		this.dept = d;
		setConfig();
		setGui();
	}

	private void setGui() {
		JLabel label1 = new JLabel("Name:");
		label1.setBounds(10, (line += 20), 150, 20);
		this.panel.add(label1);
		final JTextField tf1 = new JTextField(01);
		tf1.setBounds(10, (line += 20), 150, 20);
		tf1.setText(dept.getName());
		tf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				dept.setName(name);
				System.out.println("Dept-Name updated");
			}

		});
		this.panel.add(tf1);
		
		JButton jbn = new JButton("<<");
		jbn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				System.out.println("back!");
			}
		});
		jbn.setBounds(10, (line += 30), 50, 30);
		this.panel.add(jbn);
		
		this.panel.setLayout(null);
		this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.frame.setVisible(true);
	}

	private void setConfig() {
		this.frame = new JFrame("Department-Name");
		this.frame.setSize(200, 130);
		this.panel = new JPanel();
		
	}
}
