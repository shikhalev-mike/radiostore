package com.company.radiostore.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@JmixEntity
@Table(name = "PRICE_HISTORY", indexes = {
        @Index(name = "IDX_PRICE_HISTORY_PRODUCT", columnList = "PRODUCT_ID")
})
@Entity
public class PriceHistory extends StandardEntity {
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @Column(name = "PRICE_CHANGE_DATE", nullable = false)
    @NotNull
    private LocalDate priceChangeDate;

    @Column(name = "OLD_PRICE", precision = 19, scale = 2)
    private BigDecimal oldPrice;

    @Column(name = "NEW_PRICE", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal newPrice;

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public LocalDate getPriceChangeDate() {
        return priceChangeDate;
    }

    public void setPriceChangeDate(LocalDate priceChangeDate) {
        this.priceChangeDate = priceChangeDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}