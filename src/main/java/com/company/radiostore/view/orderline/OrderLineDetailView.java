package com.company.radiostore.view.orderline;

import com.company.radiostore.app.OrderLineManager;
import com.company.radiostore.entity.Order;
import com.company.radiostore.entity.OrderLine;
import com.company.radiostore.view.main.MainView;
import com.company.radiostore.view.order.OrderDetailView;
import com.vaadin.flow.router.Route;
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
    private OrderLineManager orderLineManager;
    @ViewComponent
    private DataContext dataContext;

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        OrderLine newOrderLine = orderLineDc.getItem();
        Order parentOrder = newOrderLine.getOrder();

        if (parentOrder.getOrderLines() != null && !parentOrder.getOrderLines().isEmpty()) {
            orderLineManager.addOrderLineToOrder(parentOrder, newOrderLine);

            dataContext.save();

            event.preventSave();

            closeWithDiscard();
        }

    }
}