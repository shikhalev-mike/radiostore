package com.company.radiostore.app;

import com.company.radiostore.entity.Customer;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerFinder {
    @Autowired
    private DataManager dataManager;

    public Optional<Customer> findCustomerByUser(UUID userId) {
        return dataManager.load(Customer.class).query("select c from Customer c where c.user.id = :userId").parameter("userId", userId).optional();
    }
}