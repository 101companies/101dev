package org.softlang.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import org.softlang.company.*;

public class MainView {

	JFrame frame;
	JPanel pmangr, pdept, pempl, pback;
	JLabel lbmangr, lbdept, lbempl, lbdeptname;
	JButton bedit1, bedit2, bback, brefresh;
	JList ldept, lempl;
	MouseListener dml, eml;
	ActionListener al1, al2, al3;

	Company com;

	String[] dname, ename;
	Dept[] d;
	Employee[] empl;
	
	Dept curr;
	
	public MainView(Company com) {
		this.com = com;
		init();
		setConfig();
		start();
		setVisible();
	}
	
	public void init() {
		frame = new JFrame("Company");
		pmangr = new JPanel();
		pdept = new JPanel();
		pempl = new JPanel();
		pback = new JPanel();
		lbmangr = new JLabel("Manager: --");
		lbdept = new JLabel("Departments:  ");
		lbempl = new JLabel("Employees:     ");
		lbdeptname = new JLabel("Dept: --");
		bedit1 = new JButton("Edit");
		bedit2 = new JButton("Edit");
		bback = new JButton("<<");
		brefresh = new JButton("Refresh");
		ldept = new JList();
		lempl = new JList();		
	}
	
	public void setConfig() {
		frame.setSize(400, 400);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { System.exit(0); }
		});
		frame.setLayout(new GridLayout(0, 1));

		pmangr.setLayout(null);
		pdept.setLayout(new FlowLayout());
		pempl.setLayout(new FlowLayout());
		pback.setLayout(null);

		lbmangr.setBounds(50, 20, 210, 20);
		bedit1.setBounds(270, 20, 80, 20);
		lbdeptname.setBounds(50, 50, 210, 20);
		bedit2.setBounds(270, 50, 80, 20);
		
		pmangr.add(lbmangr);
		pmangr.add(bedit1);
		pmangr.add(lbdeptname);
		pmangr.add(bedit2);
		
		ldept.setVisibleRowCount(5);
		pdept.add(lbdept);
		pdept.add(new JScrollPane(ldept));

		lempl.setVisibleRowCount(5);
		pempl.add(lbempl);
		pempl.add(new JScrollPane(lempl));
		
		bback.setBounds(10, 40, 50, 30);
		bback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
			
		});
		pback.add(bback);
		
		brefresh.setBounds(60, 40, 80, 30);
		brefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curr != null) update(curr);
				else start();
			}
			
		});
		pback.add(brefresh);
		
		frame.add(pmangr);
		frame.add(pdept);
		frame.add(pempl);
		frame.add(pback);
	}
	
	@SuppressWarnings("deprecation")
	public void start() {
		curr = null;
		removeAllListeners();
		lbmangr.setText("Manager: --");
		lbdeptname.setText("Dept: --");
		int i = 0;
		d = new Dept[com.getDepts().size()];
		dname = new String[com.getDepts().size()];
		for (Dept dept : com.getDepts()) {
			d[i] = dept;
			dname[i] = dept.getName();
			i++;
		}
		ldept.setListData(dname);
		ldept.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		ldept.addMouseListener(dml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				update(d[ldept.getSelectedIndex()]);
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
		ldept.setFixedCellWidth(200);
		lempl.setFixedCellWidth(200);
		ldept.enable();
		lempl.disable();
		String[] ename = new String[0];
		lempl.setListData(ename);
		setVisible();
	}
	
	@SuppressWarnings("deprecation")
	public void update(final Dept selectedDept) {
		removeAllListeners();
		
		// ###################################################
		// ## Manager Deptname ##
		// ###################################################
		lbmangr.setText("Manager: " + selectedDept.getManager().getPerson().getName());
		lbdeptname.setText("Dept: " + selectedDept.getName());
		bedit1.addActionListener(al1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeView(selectedDept.getManager());
			}

		});
		bedit2.addActionListener(al2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeptView(selectedDept);				
			}
			
		});
		// ###################################################
		// ## Liste Deptartment und Employee ##
		// ###################################################
		int i = 0, j = 0;
		for (Subunit sub : selectedDept.getSubunits()) {
			if (sub.getDu() != null) { i++; }
			else { j++; }
		}
		d = new Dept[i];
		dname = new String[i];
		empl = new Employee[j];
		ename = new String[j];
		i = 0;
		j = 0;
		for (Subunit sub : selectedDept.getSubunits()) {
			if (sub.getDu() != null) {
				d[i] = sub.getDu();
				dname[i] = sub.getDu().getName();
				i++;
			} else {
				empl[j] = sub.getPu();
				ename[j] = sub.getPu().getPerson().getName();
				j++;
			}
		}		
		ldept.setListData(dname);
		ldept.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(i!=0) {
			ldept.addMouseListener(dml = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					update(d[ldept.getSelectedIndex()]);
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

		lempl.setListData(ename);
		lempl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(j!= 0) {
			lempl.addMouseListener(eml = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					new EmployeeView(empl[lempl.getSelectedIndex()]);
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
	
		if(i != 0) ldept.enable();
		else ldept.disable();
		
		if(j != 0) lempl.enable();
		else lempl.disable();
		setVisible();
		curr = selectedDept;
	}
	
	public void setVisible() {
		frame.setVisible(true);
		pmangr.setVisible(true);
		pdept.setVisible(true);
		pempl.setVisible(true);
		pback.setVisible(true);
	}
	
	public void removeAllListeners() {
		if (dml != null) ldept.removeMouseListener(dml);
		if (eml != null) lempl.removeMouseListener(eml);
		if (al1 != null) bedit1.removeActionListener(al1);
		if (al2 != null) bedit2.removeActionListener(al2);
		if (al3 != null) bback.removeActionListener(al3);
	}

}
