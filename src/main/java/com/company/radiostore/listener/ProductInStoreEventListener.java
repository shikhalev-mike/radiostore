package com.company.radiostore.listener;

import com.company.radiostore.entity.PriceHistory;
import com.company.radiostore.entity.ProductInStore;
import io.jmix.core.DataManager;
import io.jmix.core.event.AttributeChanges;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ProductInStoreEventListener {
    private final DataManager dataManager;

    @Autowired
    public ProductInStoreEventListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventListener
    public void onProductInStoreChangedAfterCommit(final EntityChangedEvent<ProductInStore> event) {
        if (event.getType() == EntityChangedEvent.Type.CREATED || event.getType() == EntityChangedEvent.Type.UPDATED) {
            ProductInStore productInStore = dataManager.load(event.getEntityId()).one();
            PriceHistory priceHistory = dataManager.create(PriceHistory.class);
            priceHistory.setProduct(productInStore.getProduct());
            priceHistory.setPriceChangeDate(LocalDate.now());
            AttributeChanges changes = event.getChanges();
            if (event.getType() == EntityChangedEvent.Type.UPDATED && changes.isChanged("price")) {
                BigDecimal oldPrice = changes.getOldValue("price");
                priceHistory.setOldPrice(oldPrice);
            } else if (event.getType() == EntityChangedEvent.Type.CREATED) {
                priceHistory.setOldPrice(BigDecimal.ZERO);
            }
            priceHistory.setNewPrice(productInStore.getPrice());
            dataManager.save(priceHistory);
        }
    }
}