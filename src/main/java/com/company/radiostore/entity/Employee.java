package com.company.radiostore.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@JmixEntity
@Table(name = "EMPLOYEE", indexes = {
        @Index(name = "IDX_EMPLOYEE_USER", columnList = "USER_ID")
})
@Entity
public class Employee extends StandardEntity {

    @NotNull
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "HIRE_DATE", nullable = false)
    private LocalDate hireDate;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "house", column = @Column(name = "ADDRESS_HOUSE"))
    })
    private Address address;

    @NotNull
    @JoinColumn(name = "POSITION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Position position;

    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @JmixProperty
    @Transient
    private String fullName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return user.firstName + " " + user.lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}