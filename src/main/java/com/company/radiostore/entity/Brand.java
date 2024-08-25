package com.company.radiostore.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "BRAND")
@Entity
public class Brand extends StandardEntity {
    @NotNull
    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String description) {
        this.website = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}