package com.company.radiostore.view.customer;

import com.company.radiostore.entity.Customer;
import com.company.radiostore.entity.User;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;

@Route(value = "customers/:id", layout = MainView.class)
@ViewController("Customer.detail")
@ViewDescriptor("customer-detail-view.xml")
@EditedEntityContainer("customerDc")
public class CustomerDetailView extends StandardDetailView<Customer> {
    @ViewComponent
    private TypedTextField<String> fullNameField;

    @Subscribe("userField")
    public void onUserFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<User>, User> event) {
        fullNameField.setValue(getEditedEntity().getFullName());
    }
}