package org.softlang.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.softlang.company.*;

public class SubunitView implements View {

	private Subunit sub;
	JFrame frame;
	JPanel panel;
	int line = 0;
	
	public SubunitView(Subunit sub) {
		this.setSub(sub);
		createGui();
	}

	@Override
	public void createGui() {

		setConfig();
		
		createEmployee(sub.getPu());
		
		if (sub.getDu() != null) {
			JButton jbn1 = new JButton("Dept: " + sub.getDu().getName());
			jbn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new DeptView(sub.getDu());
				}
			});
			jbn1.setBounds(10, (line += 30), 150, 30);
			this.panel.add(jbn1);
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
	
	private void createEmployee(final Employee pu) {
		JButton button = new JButton("PU");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeView(pu);
			}
		});
		button.setBounds(10, (line += 30), 150, 30);
		this.panel.add(button);
	}

	public void setConfig() {
		this.frame = new JFrame("Subunit-ID: " + sub.getSubunitid());
		this.frame.setSize(200, 200);
		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.panel = new JPanel();
		
	}
	
	public void setSub(Subunit sub) {
		this.sub = sub;
	}

	public Subunit getSub() {
		return sub;
	}

}
