package org.softlang.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.softlang.company.*;

public class DeptView implements View {

	private Dept d;
	private Subunit[] subunits;
	private String[] names;
	int line = 0;
	boolean setList = false;
	JFrame frame, prevFrame;
	JPanel panel;
	JList list;

	public DeptView(Dept dept, JFrame frame) {
		this.d = dept;
		this.prevFrame = frame;
		if (d.getSubunits().size() > 0) {
			subunits = new Subunit[d.getSubunits().size()];
			names = new String[d.getSubunits().size()];
			setList();
			setList = true;
		}
		setConfig();
		createGui();
	}

	private void setList() {
		int i = 0;
		for (final Subunit sub : d.getSubunits()) {
			subunits[i] = sub;
			names[i] = "Subunit-ID " + sub.getSubunitid();
			i++;
		}
		list = new JList(names);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new SubunitView(subunits[list.getSelectedIndex()], frame);
				frame.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	public void createGui() {

		createEmployee(d.getManager());

		JButton jbn2 = new JButton("<<");
		jbn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prevFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		jbn2.setBounds(10, (line += 30), 50, 30);
		this.panel.add(jbn2);

		if (setList)
			this.panel.add(new JScrollPane(list));

		this.frame.getContentPane().add(panel);
		this.frame.setLayout(new FlowLayout());
		this.frame.setVisible(true);
	}

	private void createEmployee(final Employee manager) {
		JButton button = new JButton("Manager");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeView(manager, frame);
				frame.setVisible(false);
			}
		});
		button.setBounds(10, (line += 30), 150, 30);
		this.panel.add(button);
	}

	@Override
	public void setConfig() {
		this.frame = new JFrame("Dept: " + d.getName());
		this.frame.setSize(400, 200);
		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.panel = new JPanel();
	}
}
