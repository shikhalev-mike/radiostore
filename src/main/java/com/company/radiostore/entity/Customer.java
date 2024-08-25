package com.company.radiostore.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "CUSTOMER", indexes = {
        @Index(name = "IDX_CUSTOMER_USER", columnList = "USER_ID")
})
@Entity
public class Customer extends StandardEntity {

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "house", column = @Column(name = "ADDRESS_HOUSE"))
    })
    private Address address;

    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JmixProperty
    @Transient
    public String getFullName() {
        if (user != null) {
            return user.getFirstName() + " " + user.getLastName();
        } else {
            return null;
        }
    }

    @InstanceName
    @DependsOnProperties({"user"})
    public String getInstanceName() {
        return getFullName();
    }
}