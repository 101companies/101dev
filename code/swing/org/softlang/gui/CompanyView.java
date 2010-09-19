package org.softlang.gui;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.softlang.company.*;

public class CompanyView implements View {

	private Company com;
	private Dept[] depts;
	private String[] names;
	JFrame frame;
	JList list;

	public CompanyView(Company c) {
		this.com = c;
		depts = new Dept[c.getDepts().size()];
		names = new String[c.getDepts().size()];
		setConfig();
		createGui();
	}

	@Override
	public void createGui() {
		this.frame = new JFrame("Company");
		this.frame.setSize(200, 200);
		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.frame.setLayout(new FlowLayout());
		this.frame.getContentPane().add(new JScrollPane(list));
		this.frame.setVisible(true);
	}

	@Override
	public void setConfig() {
		int i = 0;
		for (Dept d : com.getDepts()) {
			depts[i] = d;
			names[i] = d.getName();
			i++;
		}
		list = new JList(names);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new DeptView(depts[list.getSelectedIndex()], frame);
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
}
