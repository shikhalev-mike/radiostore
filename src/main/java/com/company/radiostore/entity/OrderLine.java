package com.company.radiostore.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "ORDER_LINE", indexes = {
        @Index(name = "IDX_ORDER_LINE_ORDER", columnList = "ORDER_ID"),
        @Index(name = "IDX_ORDER_LINE_PRODUCT_IN_STORE", columnList = "PRODUCT_IN_STORE_ID")
})
@Entity
public class OrderLine extends StandardEntity {
    @JoinColumn(name = "ORDER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @JoinColumn(name = "PRODUCT_IN_STORE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductInStore productInStore;

    public ProductInStore getProductInStore() {
        return productInStore;
    }

    public void setProductInStore(ProductInStore productInStore) {
        this.productInStore = productInStore;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}