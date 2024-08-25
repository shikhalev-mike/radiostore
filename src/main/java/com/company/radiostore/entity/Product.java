package com.company.radiostore.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "PRODUCT", indexes = {
        @Index(name = "IDX_PRODUCT_", columnList = "")
})
@Entity
public class Product extends StandardEntity {
    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    @NotNull
    @JoinColumn(name = "BRAND_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Brand brand;

    @Column(name = "DESCRIPTION")
    private String description;

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}