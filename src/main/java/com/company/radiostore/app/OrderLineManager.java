package com.company.radiostore.app;

import com.company.radiostore.entity.Order;
import com.company.radiostore.entity.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineManager {
    public void addOrderLineToOrder(Order order, OrderLine newOrderLine) {
        for (OrderLine existingOrderLine : order.getOrderLines()) {
            if (existingOrderLine.getProductInStore().equals(newOrderLine.getProductInStore())) {
                existingOrderLine.setQuantity(existingOrderLine.getQuantity() + newOrderLine.getQuantity());
                return;
            }
        }
        order.getOrderLines().add(newOrderLine);
    }
}