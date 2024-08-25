package com.company.radiostore.view.employee;

import com.company.radiostore.entity.Employee;
import com.company.radiostore.entity.User;
import com.company.radiostore.security.CreatedByMeOrdersRole;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "employees/:id", layout = MainView.class)
@ViewController("Employee.detail")
@ViewDescriptor("employee-detail-view.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeDetailView extends StandardDetailView<Employee> {
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private TypedTextField<String> fullNameField;

    @Subscribe
    public void onAfterSave(final AfterSaveEvent event) {
        RoleAssignmentEntity employeeRowRole = dataManager.create(RoleAssignmentEntity.class);
        employeeRowRole.setUsername(getEditedEntity().getUser().getUsername());
        employeeRowRole.setRoleType(RoleAssignmentRoleType.ROW_LEVEL);
        employeeRowRole.setRoleCode(CreatedByMeOrdersRole.CODE);
        dataManager.save(employeeRowRole);
    }

    @Subscribe("userField")
    public void onUserFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<User>, User> event) {
        fullNameField.setValue(getEditedEntity().getFullName());
    }
}