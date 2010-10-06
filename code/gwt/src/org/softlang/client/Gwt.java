package org.softlang.client;

import java.util.LinkedList;
import java.util.Stack;

import org.softlang.client.company.*;
import org.softlang.client.util.ResultPair;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {

	private final ServiceAsync service = GWT.create(Service.class);
	private Company company;
	private boolean saved = true;
	private Stack<Dept> deptStack = new Stack<Dept>();
	private final AbsolutePanel all = new AbsolutePanel();

	public void onModuleLoad() {
		all.setStyleName("all");
		RootPanel.get().add(all);
		HorizontalPanel footer = new HorizontalPanel();
		footer.setStyleName("footer2");
		RootPanel.get().add(footer);

		service.getCompany(new AsyncCallback<Company>() {

			@Override
			public void onSuccess(Company result) {
				company = result;
				showCompany();
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	// Display company

	public void showCompany() {
		final VerticalPanel topPanel = new VerticalPanel();
		final HorizontalPanel salaryPanel = new HorizontalPanel();
		final VerticalPanel borderPanel1 = new VerticalPanel();
		final VerticalPanel deptListPanel = new VerticalPanel();

		topPanel.setStyleName("topPanel");
		final HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.setStyleName("menuPanel");
		final Button upButton = new Button("Up");
		upButton.setEnabled(false);
		upButton.setStyleName("navButton");
		menuPanel.add(upButton);
		final Button saveButton = new Button("Save");
		saveButton.setStyleName("saveButton");
		saveButton.setEnabled(!saved);
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save(saveButton);

			}
		});
		menuPanel.add(saveButton);

		topPanel.add(menuPanel);
		final HorizontalPanel topInnerPanel = new HorizontalPanel();
		topInnerPanel.setStyleName("topInnerPanel");
		final Label infoTopText = new Label("Top Departments");
		infoTopText.setStyleName("viewInfo");
		topInnerPanel.add(infoTopText);
		topPanel.add(topInnerPanel);
		all.add(topPanel);

		deptListPanel.setStyleName("list");
		for (int i = 0; i < company.getDepts().size(); i++) {
			final int finali = i;
			HorizontalPanel butPanel = new HorizontalPanel();
			butPanel.setStyleName("butPanel");
			Button curDeptButton = new Button(company.getDepts().get(i)
					.getName());
			curDeptButton.setStyleName("deptButton");
			curDeptButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					all.clear();
					showDept(company.getDepts().get(finali));
				}
			});
			butPanel.add(curDeptButton);
			deptListPanel.add(butPanel);
		}
		Button deptAddButton = new Button("Add");
		deptAddButton.setStyleName("addButton");
		deptAddButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Dept newDept = new Dept("", null, new LinkedList<Subunit>());
				company.getDepts().add(newDept);
				saved = false;
				all.clear();
				showDept(newDept);
			}
		});
		topInnerPanel.add(deptAddButton);
		all.add(deptListPanel);

		borderPanel1.addStyleName("salaryBorderPanel");
		all.add(borderPanel1);

		salaryPanel.setStyleName("salaryPanel");
		final Label salaryInfoValue = new Label();
		service.getCompanyTotal(company, new AsyncCallback<Double>() {

			@Override
			public void onSuccess(Double result) {
				salaryInfoValue.setText("Total salary = " + result + " $");
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		salaryInfoValue.setStyleName("salaryInfo");
		salaryPanel.add(salaryInfoValue);
		Button cutButton = new Button("Cut");
		cutButton.setStyleName("cutButton");
		cutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.cutCompany(company,
						new AsyncCallback<ResultPair<Company>>() {

							@Override
							public void onSuccess(ResultPair<Company> result) {
								company = result.getT();
								saved = false;
								saveButton.setEnabled(true);
								salaryInfoValue.setText("Total salary = "
										+ result.getNewSalary() + " $");
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});

			}
		});
		salaryPanel.add(cutButton);
		all.add(salaryPanel);

	}

	// Display given department

	public void showDept(final Dept dept) {
		final VerticalPanel topPanel = new VerticalPanel();
		final HorizontalPanel namePanel = new HorizontalPanel();
		final VerticalPanel borderPanel = new VerticalPanel();
		final HorizontalPanel managerPanel = new HorizontalPanel();
		final VerticalPanel borderPanel1 = new VerticalPanel();
		final HorizontalPanel employeesPanelTop = new HorizontalPanel();
		final VerticalPanel employeesPanel = new VerticalPanel();
		final VerticalPanel borderPanel2 = new VerticalPanel();
		final HorizontalPanel subDeptsPanelTop = new HorizontalPanel();
		final VerticalPanel subDeptsPanel = new VerticalPanel();
		final VerticalPanel borderPanel3 = new VerticalPanel();
		final HorizontalPanel salaryPanel = new HorizontalPanel();

		topPanel.setStyleName("topPanel");
		final HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.setStyleName("menuPanel");
		final Button upButton = new Button("Up");
		upButton.setStyleName("navButton");
		upButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				all.clear();
				if (deptStack.isEmpty())
					showCompany();
				else
					showDept(deptStack.pop());
			}
		});
		menuPanel.add(upButton);
		final Button saveButton = new Button("Save");
		saveButton.setStyleName("saveButton");
		saveButton.setEnabled(!saved);
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save(saveButton);
			}
		});
		menuPanel.add(saveButton);
		final Button deleteButton = new Button("Delete");
		deleteButton.setStyleName("saveButton");
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				all.clear();
				saved = false;
				if (deptStack.isEmpty()) {
					company.getDepts().remove(dept);
					showCompany();
				} else {
					Dept upperDept = deptStack.pop();
					upperDept.getSubunits().remove(new Subunit(dept));
					showDept(upperDept);
				}

			}
		});
		menuPanel.add(deleteButton);

		topPanel.add(menuPanel);

		topPanel.add(menuPanel);
		final HorizontalPanel topInnerPanel = new HorizontalPanel();
		topInnerPanel.setStyleName("topInnerPanel");
		final Label infoTopText = new Label("Department ");
		infoTopText.setStyleName("viewInfo");
		topInnerPanel.add(infoTopText);
		final Label deptNameText = new Label("\"" + dept.getName() + "\"");
		deptNameText.setStyleName("viewInfoExtDept");
		topInnerPanel.add(deptNameText);
		topPanel.add(topInnerPanel);
		all.add(topPanel);

		namePanel.setStyleName("deptInfoPanel");
		final Label nameInfoText = new Label("Name: ");
		nameInfoText.setStyleName("deptInfoText");
		namePanel.add(nameInfoText);
		final TextBox nameEditBox = new TextBox();
		nameEditBox.setText(dept.getName());
		nameEditBox.setStyleName("deptEditBoxOff");
		nameEditBox.setReadOnly(true);
		namePanel.add(nameEditBox);
		final Button nameEditButton = new Button("Edit");
		nameEditButton.setStyleName("editButton");
		nameEditButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameEditBox.isReadOnly()) {
					nameEditBox.setReadOnly(false);
					nameEditBox.setStyleName("deptEditBox");
					nameEditButton.setText("done");
				} else {
					nameEditBox.setReadOnly(true);
					nameEditBox.setStyleName("deptEditBoxOff");
					nameEditButton.setText("edit");
					if (!nameEditBox.getText().equals(dept.getName())) {
						saved = false;
						saveButton.setEnabled(true);
						dept.setName(nameEditBox.getText());
						deptNameText.setText("\"" + dept.getName() + "\"");
					}
				}
			}
		});
		namePanel.add(nameEditButton);
		all.add(namePanel);

		borderPanel.addStyleName("borderPanel");
		all.add(borderPanel);

		managerPanel.setStyleName("managerPanel");
		Label managerInfo = new Label("Manager: ");
		managerInfo.setStyleName("managerInfo");
		managerPanel.add(managerInfo);
		Button managerButton = new Button();
		if (dept.getManager() != null) {
			managerButton.setText(dept.getManager().getPerson().getName());
			managerButton.setStyleName("employeeButton");
			managerButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					all.clear();
					deptStack.push(dept);
					showEmployee(dept.getManager());
				}
			});
		} else {
			managerButton.setText("<empty>");
			managerButton.setStyleName("employeeButtonEmpty");
			managerButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					all.clear();
					deptStack.push(dept);
					Employee manager = new Employee(new Person("", ""), 0.0);
					dept.setManager(manager);
					saved = false;
					showEmployee(manager);
				}
			});
		}

		managerPanel.add(managerButton);
		all.add(managerPanel);

		borderPanel1.addStyleName("borderPanel");
		all.add(borderPanel1);

		employeesPanelTop.setStyleName("listTop");
		final Label employeesInfoText = new Label("Employees:");
		employeesInfoText.setStyleName("listInfo");
		employeesPanelTop.add(employeesInfoText);

		Button empAddButton = new Button("Add");
		empAddButton.setStyleName("addButtonList");
		empAddButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Employee newEmployee = new Employee(new Person("", ""), 0.0);
				dept.getSubunits().add(new Subunit(newEmployee));
				deptStack.push(dept);
				saved = false;
				all.clear();
				showEmployee(newEmployee);
			}
		});
		employeesPanelTop.add(empAddButton);
		all.add(employeesPanelTop);

		employeesPanel.setStyleName("list");
		for (int i = 0; i < dept.getSubunits().size(); i++) {
			final int finali = i;
			if (dept.getSubunits().get(finali).getPu() != null) {
				HorizontalPanel butPanel = new HorizontalPanel();
				butPanel.setStyleName("butPanel");
				Button curEmplButton = new Button(dept.getSubunits()
						.get(finali).getPu().getPerson().getName());
				curEmplButton.setStyleName("employeeButton");
				curEmplButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						all.clear();
						deptStack.push(dept);
						System.out.println(dept.getSubunits().get(finali)
								.getPu());
						showEmployee(dept.getSubunits().get(finali).getPu());

					}
				});
				butPanel.add(curEmplButton);
				employeesPanel.add(butPanel);
				butPanel.setHeight("40px");
			}
		}

		all.add(employeesPanel);

		borderPanel2.addStyleName("borderPanel");
		all.add(borderPanel2);

		subDeptsPanelTop.setStyleName("listTop");
		final Label subDeptsInfoText = new Label("Sub Departments:");
		subDeptsInfoText.setStyleName("listInfo");
		subDeptsPanelTop.add(subDeptsInfoText);
		Button deptAddButton = new Button("Add");
		deptAddButton.setStyleName("addButtonList");
		deptAddButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Dept newDept = new Dept("", null, new LinkedList<Subunit>());
				dept.getSubunits().add(new Subunit(newDept));
				deptStack.push(dept);
				saved = false;
				all.clear();
				showDept(newDept);
			}
		});
		subDeptsPanelTop.add(deptAddButton);
		all.add(subDeptsPanel);
		all.add(subDeptsPanelTop);

		subDeptsPanel.setStyleName("list");
		for (int i = 0; i < dept.getSubunits().size(); i++) {
			final int finali = i;
			if (dept.getSubunits().get(finali).getDu() != null) {
				HorizontalPanel butPanel = new HorizontalPanel();
				butPanel.setStyleName("butPanel");
				Button curDeptButton = new Button(dept.getSubunits()
						.get(finali).getDu().getName());
				curDeptButton.setStyleName("deptButton");
				curDeptButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						all.clear();
						deptStack.push(dept);
						showDept(dept.getSubunits().get(finali).getDu());
					}
				});
				butPanel.add(curDeptButton);
				subDeptsPanel.add(butPanel);
				butPanel.setHeight("40px");
			}
		}

		all.add(subDeptsPanel);

		borderPanel3.addStyleName("salaryBorderPanel");
		all.add(borderPanel3);

		salaryPanel.setStyleName("salaryPanel");
		final Label salaryInfoValue = new Label();
		service.getDeptTotal(dept, new AsyncCallback<Double>() {

			@Override
			public void onSuccess(Double result) {
				salaryInfoValue.setText("Total salary = " + result + " $");
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

		salaryInfoValue.setStyleName("salaryInfo");
		salaryPanel.add(salaryInfoValue);
		Button cutButton = new Button("Cut");
		cutButton.setStyleName("cutButton");
		cutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.cutDept(dept, new AsyncCallback<ResultPair<Dept>>() {

					@Override
					public void onSuccess(ResultPair<Dept> result) {
						saved = false;
						saveButton.setEnabled(true);
						dept.setManager(result.getT().getManager());
						dept.setName(result.getT().getName());
						dept.setSubunits(result.getT().getSubunits());
						salaryInfoValue.setText("Total salary = "
								+ result.getNewSalary() + " $");
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		});
		salaryPanel.add(cutButton);
		all.add(salaryPanel);

	}

	// Display given employee

	public void showEmployee(final Employee employee) {
		final VerticalPanel topPanel = new VerticalPanel();
		final HorizontalPanel namePanel = new HorizontalPanel();
		final HorizontalPanel addressPanel = new HorizontalPanel();
		final HorizontalPanel salaryPanel = new HorizontalPanel();

		topPanel.setStyleName("topPanel");
		final HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.setStyleName("menuPanel");
		final Button upButton = new Button("Up");
		upButton.setStyleName("navButton");
		upButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				all.clear();
				showDept(deptStack.pop());
			}
		});
		menuPanel.add(upButton);
		final Button saveButton = new Button("Save");
		saveButton.setStyleName("saveButton");
		saveButton.setEnabled(!saved);
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save(saveButton);
			}
		});
		menuPanel.add(saveButton);

		final Button deleteButton = new Button("Delete");
		deleteButton.setStyleName("saveButton");
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Dept upperDept = deptStack.pop();
				boolean isManager = !upperDept.getSubunits().contains(
						new Subunit(employee));
				if (isManager)
					upperDept.setManager(null);
				else
					upperDept.getSubunits().remove(new Subunit(employee));
				all.clear();
				saved = false;
				showDept(upperDept);
			}
		});
		menuPanel.add(deleteButton);
		topPanel.add(menuPanel);

		final HorizontalPanel topInnerPanel = new HorizontalPanel();
		topInnerPanel.setStyleName("topInnerPanel");
		final Label infoTopText = new Label("Employee ");
		infoTopText.setStyleName("viewInfo");
		topInnerPanel.add(infoTopText);
		final Label employeeNameText = new Label("\""
				+ employee.getPerson().getName() + "\"");
		employeeNameText.setStyleName("viewInfoExtEmpl");
		topInnerPanel.add(employeeNameText);
		topPanel.add(topInnerPanel);
		all.add(topPanel);

		namePanel.setStyleName("empInfoPanel");
		final Label nameInfoText = new Label("Name : ");
		nameInfoText.setStyleName("empInfoText");
		namePanel.add(nameInfoText);
		final TextBox nameEditBox = new TextBox();
		nameEditBox.setText(employee.getPerson().getName());
		nameEditBox.setStyleName("empEditBoxOff");
		nameEditBox.setReadOnly(true);
		namePanel.add(nameEditBox);
		final Button nameEditButton = new Button("Edit");
		nameEditButton.setStyleName("editButton");
		nameEditButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameEditBox.isReadOnly()) {
					nameEditBox.setReadOnly(false);
					nameEditBox.setStyleName("empEditBox");
					nameEditButton.setText("Done");
				} else {
					nameEditBox.setReadOnly(true);
					nameEditBox.setStyleName("deptEditBoxOff");
					nameEditButton.setText("Edit");
					if (!nameEditBox.getText().equals(
							employee.getPerson().getName())) {
						saved = false;
						saveButton.setEnabled(true);
						employee.getPerson().setName(nameEditBox.getText());
						employeeNameText.setText("\""
								+ employee.getPerson().getName() + "\"");
					}
				}

			}
		});
		namePanel.add(nameEditButton);
		all.add(namePanel);

		addressPanel.setStyleName("empInfoPanel");
		final Label addressInfoText = new Label("Address : ");
		addressInfoText.setStyleName("empInfoText");
		addressPanel.add(addressInfoText);
		final TextBox addressEditBox = new TextBox();
		addressEditBox.setText(employee.getPerson().getAddress());
		addressEditBox.setStyleName("empEditBoxOff");
		addressEditBox.setReadOnly(true);
		addressPanel.add(addressEditBox);
		final Button addressEditButton = new Button("Edit");
		addressEditButton.setStyleName("editButton");
		addressEditButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (addressEditBox.isReadOnly()) {
					addressEditBox.setReadOnly(false);
					addressEditBox.setStyleName("empEditBox");
					addressEditButton.setText("Done");
				} else {
					addressEditBox.setReadOnly(true);
					addressEditBox.setStyleName("deptEditBoxOff");
					addressEditButton.setText("Edit");
					if (!addressEditBox.getText().equals(
							employee.getPerson().getAddress())) {
						saved = false;
						saveButton.setEnabled(true);
						employee.getPerson().setAddress(
								addressEditBox.getText());
					}
				}

			}
		});
		addressPanel.add(addressEditButton);
		all.add(addressPanel);

		salaryPanel.setStyleName("empInfoPanel");
		final Label salaryInfoText = new Label("Salary : ");
		salaryInfoText.setStyleName("empInfoText");
		salaryPanel.add(salaryInfoText);
		final TextBox salaryEditBox = new TextBox();
		salaryEditBox.setText(Double.toString(employee.getSalary()) + " $");
		salaryEditBox.setStyleName("empEditBoxOff");
		salaryEditBox.setReadOnly(true);
		salaryPanel.add(salaryEditBox);
		final Button salaryEditButton = new Button("Edit");
		salaryEditButton.setStyleName("editButton");
		salaryEditButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (salaryEditBox.isReadOnly()) {
					salaryEditBox.setReadOnly(false);
					salaryEditBox.setStyleName("empEditBox");
					salaryEditBox.setText(salaryEditBox.getText().substring(0,
							salaryEditBox.getText().length() - 2));
					salaryEditButton.setText("Done");
				} else {
					salaryEditBox.setReadOnly(true);
					salaryEditBox.setStyleName("empEditBoxOff");
					salaryEditButton.setText("Edit");
					if (!salaryEditBox.getText().equals(
							Double.toString(employee.getSalary()))) {
						try {
							employee.setSalary(Double.valueOf(salaryEditBox
									.getText()));
						} catch (NumberFormatException e) {
							salaryEditBox.setText(Double.toString(employee
									.getSalary()));
						}
						saved = false;
						saveButton.setEnabled(true);
					}
					salaryEditBox.setText(salaryEditBox.getText() + " $");

				}

			}
		});
		salaryPanel.add(salaryEditButton);
		final Button cutButton = new Button("Cut");
		cutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.cutEmployee(employee,
						new AsyncCallback<ResultPair<Employee>>() {

							@Override
							public void onSuccess(ResultPair<Employee> result) {
								employee.setSalary(result.getNewSalary());
								salaryEditBox.setText(employee.getSalary()
										+ " $");
								saved = false;
								saveButton.setEnabled(true);
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		cutButton.setStyleName("cutButton");
		salaryPanel.add(cutButton);
		all.add(salaryPanel);
	}

	public void save(final Button saveButton) {
		service.saveCompany(company, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				saved = true;
				saveButton.setEnabled(false);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}