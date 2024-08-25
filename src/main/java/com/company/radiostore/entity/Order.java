package com.company.radiostore.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JmixEntity
@Table(name = "ORDER_", indexes = {
        @Index(name = "IDX_ORDER__CUSTOMER", columnList = "CUSTOMER_ID")
}, uniqueConstraints = {
        @UniqueConstraint(name = "IDX_ORDER_ORDER_NUMBER_UNQ", columnNames = {"NUMBER_"})
})
@Entity(name = "Order_")
public class Order extends StandardEntity {
    @Positive
    @NotNull
    @Column(name = "NUMBER_", nullable = false)
    private Long orderNumber;

    @Column(name = "ORDER_DATE", nullable = false)
    @NotNull
    private LocalDate orderDate;

    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    public void setOrderNumber(Long number) {
        this.orderNumber = number;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

}