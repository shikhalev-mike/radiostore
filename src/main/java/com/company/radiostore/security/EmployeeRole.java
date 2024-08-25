package com.company.radiostore.security;

import com.company.radiostore.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Employee", code = EmployeeRole.CODE)
public interface EmployeeRole {
    String CODE = "employee";

    @MenuPolicy(menuIds = {"Customer.list", "User.list", "Employee.list", "Position_.list", "PriceHistory.list", "Product.list", "ProductInStore.list", "Brand.list", "Category.list", "Order_.list"})
    @ViewPolicy(viewIds = {"Customer.list", "User.list", "Employee.list", "Position_.list", "PriceHistory.list", "Product.list", "ProductInStore.list", "Brand.list", "Category.list", "Order_.list", "Brand.detail", "Category.detail", "Customer.detail", "Employee.detail", "Position_.detail", "Order_.detail", "OrderLine.detail", "Product.detail", "ProductInStore.detail", "User.detail", "MainView"})
    void screens();

    @EntityAttributePolicy(entityClass = Address.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Address.class, actions = EntityPolicyAction.ALL)
    void address();

    @EntityAttributePolicy(entityClass = Brand.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Brand.class, actions = EntityPolicyAction.ALL)
    void brand();

    @EntityAttributePolicy(entityClass = Category.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Category.class, actions = EntityPolicyAction.ALL)
    void category();

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.READ)
    void customer();

    @EntityAttributePolicy(entityClass = Employee.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Employee.class, actions = EntityPolicyAction.READ)
    void employee();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Order.class, actions = EntityPolicyAction.ALL)
    void order();

    @EntityAttributePolicy(entityClass = OrderLine.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = OrderLine.class, actions = EntityPolicyAction.ALL)
    void orderLine();

    @EntityAttributePolicy(entityClass = Position.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Position.class, actions = EntityPolicyAction.READ)
    void position();

    @EntityAttributePolicy(entityClass = PriceHistory.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PriceHistory.class, actions = EntityPolicyAction.READ)
    void priceHistory();

    @EntityAttributePolicy(entityClass = Product.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Product.class, actions = EntityPolicyAction.ALL)
    void product();

    @EntityAttributePolicy(entityClass = ProductInStore.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ProductInStore.class, actions = EntityPolicyAction.ALL)
    void productInStore();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();
}