package com.company.radiostore.view.employee;

import com.company.radiostore.entity.Employee;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "employees", layout = MainView.class)
@ViewController("Employee.list")
@ViewDescriptor("employee-list-view.xml")
@LookupComponent("employeesDataGrid")
@DialogMode(width = "64em")
public class EmployeeListView extends StandardListView<Employee> {
}