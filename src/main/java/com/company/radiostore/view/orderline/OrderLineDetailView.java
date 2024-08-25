package com.company.radiostore.view.orderline;

import com.company.radiostore.entity.Order;
import com.company.radiostore.entity.OrderLine;
import com.company.radiostore.entity.ProductInStore;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "orderLines/:id", layout = MainView.class)
@ViewController("OrderLine.detail")
@ViewDescriptor("order-line-detail-view.xml")
@EditedEntityContainer("orderLineDc")
public class OrderLineDetailView extends StandardDetailView<OrderLine> {
    @ViewComponent
    private InstanceContainer<OrderLine> orderLineDc;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private DataContext dataContext;

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        OrderLine editedEntity = orderLineDc.getItem();
        Order order = editedEntity.getOrder();
        ProductInStore productInStore = editedEntity.getProductInStore();

        if (order.getOrderLines() != null && !order.getOrderLines().isEmpty()) {
            if (editedEntity.getQuantity() > productInStore.getQuantity()) {
                notifications.create(productInStore.getProduct().getName() + " - не хватает товара").show();
            } else {
                if (getDuplicate(order, editedEntity) != null) {
                    OrderLine p = getDuplicate(order, editedEntity);
                    assert p != null;
                    if ((p.getQuantity() + editedEntity.getQuantity()) > productInStore.getQuantity()) {
                        notifications.create(productInStore.getProduct().getName() + " - не хватает товара").show();
                    } else {
                        p.setQuantity(p.getQuantity() + editedEntity.getQuantity());
                        dataContext.remove(editedEntity);
                    }
                } else {
                    order.getOrderLines().add(dataContext.merge(editedEntity));
                }
            }
            dataContext.save();
            event.preventSave();
            closeWithDiscard();
        }


    }

    private OrderLine getDuplicate(Order order, OrderLine orderLine) {
        for (OrderLine p : order.getOrderLines()) {
            if (p.getProductInStore().equals(orderLine.getProductInStore())) {
                return p;
            }
        }
        return null;
    }
}