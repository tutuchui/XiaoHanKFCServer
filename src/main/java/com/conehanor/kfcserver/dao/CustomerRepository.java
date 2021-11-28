package com.conehanor.kfcserver.dao;

import com.conehanor.kfcserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.phone = :phone")
    Customer findCustomerByPhone(String phone);
}
