package com.company.radiostore.listener;

import com.company.radiostore.entity.Order;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.data.Sequence;
import io.jmix.data.Sequences;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("OrderNumberAssignment")
public class OrderNumberAssignment {
    private final Sequences sequences;

    public OrderNumberAssignment(Sequences sequences) {
        this.sequences = sequences;
    }

    @EventListener
    public void onOrderSaving(final EntitySavingEvent<Order> event) {
        if (event.isNewEntity() && event.getEntity().getOrderNumber() == null) {
            long nextOrderNumber = sequences.createNextValue(Sequence.withName("order_number_seq"));
            event.getEntity().setOrderNumber(nextOrderNumber);
        }
    }
}