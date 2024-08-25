package com.company.radiostore.view.order;

import com.company.radiostore.app.CustomerFinder;
import com.company.radiostore.entity.*;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController("Order_.detail")
@ViewDescriptor("order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {
    private static final Logger log = LoggerFactory.getLogger(OrderDetailView.class);
    @ViewComponent
    private InstanceContainer<Order> orderDc;
    @ViewComponent
    private EntityPicker<Customer> customerField;
    @Autowired
    private CustomerFinder customerFinder;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private TypedTextField<BigDecimal> amountField;
    @ViewComponent
    private TypedDatePicker<LocalDate> orderDateField;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        amountField.setValue(String.valueOf(BigDecimal.ZERO));
        User currentUser = (User) currentAuthentication.getUser();
        Optional<Customer> customerOptional  = customerFinder.findCustomerByUser(currentUser.getId());
        if (customerOptional .isPresent()) {
            Customer customer = customerOptional.get();
            customerField.setValue(customer);
        } else {
            log.warn("Customer not found for user {}", currentUser.getId());
        }
        orderDateField.setValue(LocalDate.now());
        amountField.setEnabled(false);
        orderDateField.setEnabled(false);
        customerField.setEnabled(false);
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        List<OrderLine> list = orderDc.getItem().getOrderLines();
        for (OrderLine orderLine : list) {
            ProductInStore productInStore = orderLine.getProductInStore();
            productInStore.setQuantity(productInStore.getQuantity() - orderLine.getQuantity());
        }
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcCollectionChange(final CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        BigDecimal amount = BigDecimal.ZERO;
        List<OrderLine> orderLines = orderDc.getItem().getOrderLines();
        if (orderLines != null && !orderLines.isEmpty()) {
            for (OrderLine orderLine : orderLines) {
                ProductInStore productInStore = orderLine.getProductInStore();
                BigDecimal price = productInStore.getPrice();
                price = price.multiply(BigDecimal.valueOf(orderLine.getQuantity()));
                amount = amount.add(price);
            }
            getEditedEntity().setAmount(amount);
        }
    }
}