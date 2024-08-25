package com.company.radiostore.security;

import com.company.radiostore.entity.Order;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "OrderSplit", code = CreatedByMeOrdersRole.CODE)
public interface CreatedByMeOrdersRole {
    String CODE = "order-split";

    @JpqlRowLevelPolicy(
            entityClass = Order.class,
            where = "{E}.createdBy = :current_user_username")
    void order();
}