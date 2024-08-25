package com.company.radiostore.view.employee;

import com.company.radiostore.entity.Employee;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "employees/:id", layout = MainView.class)
@ViewController("Employee.detail")
@ViewDescriptor("employee-detail-view.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeDetailView extends StandardDetailView<Employee> {
}