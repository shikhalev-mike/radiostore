package com.company.radiostore.view.user;

import com.company.radiostore.entity.Customer;
import com.company.radiostore.entity.User;
import com.company.radiostore.security.CreatedByMeOrdersRole;
import com.company.radiostore.security.CustomerRole;
import com.company.radiostore.security.DatabaseUserRepository;
import com.company.radiostore.security.UiMinimalRole;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.core.SaveContext;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.security.role.RoleGrantedAuthorityUtils;
import io.jmix.security.role.assignment.RoleAssignmentRepository;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import io.jmix.securitydata.impl.role.provider.DatabaseResourceRoleProvider;
import io.jmix.securityflowui.role.UiFilterRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

@Route(value = "users/:id", layout = MainView.class)
@ViewController("User.detail")
@ViewDescriptor("user-detail-view.xml")
@EditedEntityContainer("userDc")
public class UserDetailView extends StandardDetailView<User> {

    @ViewComponent
    private TypedTextField<String> usernameField;
    @ViewComponent
    private PasswordField passwordField;
    @ViewComponent
    private PasswordField confirmPasswordField;
    @ViewComponent
    private ComboBox<String> timeZoneField;
    @ViewComponent
    private MessageBundle messageBundle;

    @Autowired
    private EntityStates entityStates;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleAssignmentRepository roleAssignmentRepository;
    @Autowired
    private DatabaseResourceRoleProvider databaseResourceRoleProvider;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private InstanceContainer<User> userDc;
    @Autowired
    DatabaseUserRepository databaseUserRepository;
    @Autowired
    private RoleGrantedAuthorityUtils roleGrantedAuthorityUtils;

    @Subscribe
    public void onInit(final InitEvent event) {
        timeZoneField.setItems(List.of(TimeZone.getAvailableIDs()));
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<User> event) {
        usernameField.setReadOnly(false);
        passwordField.setVisible(true);
        confirmPasswordField.setVisible(true);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            usernameField.focus();
        }
    }

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        if (entityStates.isNew(getEditedEntity())
                && !Objects.equals(passwordField.getValue(), confirmPasswordField.getValue())) {
            event.getErrors().add(messageBundle.getMessage("passwordsDoNotMatch"));
        }
    }

    @Subscribe
    protected void onBeforeSave(final BeforeSaveEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            getEditedEntity().setPassword(passwordEncoder.encode(passwordField.getValue()));
        }
    }

    @Subscribe
    public void onAfterSave(final AfterSaveEvent event) {
        Customer customer = dataManager.create(Customer.class);
        customer.setUser(getEditedEntity());
        dataManager.save(customer);
        RoleAssignmentEntity uiMinimalRole = dataManager.create(RoleAssignmentEntity.class);
        uiMinimalRole.setUsername(getEditedEntity().getUsername());
        uiMinimalRole.setRoleType(RoleAssignmentRoleType.RESOURCE);
        uiMinimalRole.setRoleCode(CustomerRole.CODE);
        uiMinimalRole.setRoleCode(UiMinimalRole.CODE);
        dataManager.save(uiMinimalRole);
        RoleAssignmentEntity customerRole = dataManager.create(RoleAssignmentEntity.class);
        customerRole.setUsername(getEditedEntity().getUsername());
        customerRole.setRoleType(RoleAssignmentRoleType.RESOURCE);
        customerRole.setRoleCode(CustomerRole.CODE);
        dataManager.save(customerRole);
        RoleAssignmentEntity customerRowRole = dataManager.create(RoleAssignmentEntity.class);
        customerRowRole.setUsername(getEditedEntity().getUsername());
        customerRowRole.setRoleType(RoleAssignmentRoleType.ROW_LEVEL);
        customerRowRole.setRoleCode(CreatedByMeOrdersRole.CODE);
        dataManager.save(customerRowRole);
    }

    private Stream<String> baseCustomerRoles() {
        return Stream.of(
                UiMinimalRole.CODE,
                CustomerRole.CODE
        );
    }
}