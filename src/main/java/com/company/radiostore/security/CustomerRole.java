package com.company.radiostore.security;

import com.company.radiostore.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Customer", code = CustomerRole.CODE)
public interface CustomerRole {
    String CODE = "customer";

    @EntityAttributePolicy(entityClass = Category.class, attributes = {"name", "description"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Category.class, actions = EntityPolicyAction.READ)
    void category();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Order.class, actions = {EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void order();

    @EntityAttributePolicy(entityClass = Product.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Product.class, actions = EntityPolicyAction.ALL)
    void product();

    @EntityAttributePolicy(entityClass = ProductInStore.class, attributes = {"version", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate", "deletedBy", "deletedDate", "product", "price"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ProductInStore.class, actions = EntityPolicyAction.ALL)
    void productInStore();

    @EntityAttributePolicy(entityClass = OrderLine.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = OrderLine.class, actions = EntityPolicyAction.ALL)
    void orderLine();

    @EntityAttributePolicy(entityClass = Brand.class, attributes = {"name", "website"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Brand.class, actions = EntityPolicyAction.READ)
    void brand();

    @MenuPolicy(menuIds = {"Brand.list", "Category.list", "Order_.list"})
    @ViewPolicy(viewIds = {"Brand.list", "Category.list", "Order_.list", "Order_.detail", "OrderLine.detail", "User.list", "Customer.list", "ProductInStore.list", "MainView"})
    void screens();

    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void user();

    @EntityPolicy(entityClass = StandardEntity.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = StandardEntity.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void standardEntity();

    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void customer();

    @EntityPolicy(entityClass = Address.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Address.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void address();

    @EntityPolicy(entityClass = Employee.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Employee.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void employee();

    @EntityAttributePolicy(entityClass = PriceHistory.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PriceHistory.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void priceHistory();

    @EntityAttributePolicy(entityClass = Position.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Position.class, actions = EntityPolicyAction.READ)
    void position();

}