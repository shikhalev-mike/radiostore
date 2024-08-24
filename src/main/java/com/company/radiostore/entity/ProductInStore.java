package com.company.radiostore.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JmixEntity
@Table(name = "PRODUCT_IN_STORE", indexes = {
        @Index(name = "IDX_PRODUCT_IN_STORE_PRODUCT", columnList = "PRODUCT_ID")
})
@Entity
public class ProductInStore extends StandardEntity {
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @NotNull
    @Column(name = "PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}