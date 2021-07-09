package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.CustomerRepository;
import com.conehanor.kfcserver.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register( @RequestParam("name")String name,
                                            @RequestParam("phone")String phone,
                                            @RequestParam("password")String password,
                                            @RequestParam("email")String email,
                                            @RequestParam("address")String address)
    {
        java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPhoto("1");
        customer.setState(1);
        customer.setBirthday(d);
        customer.setCreatTime(d);
        customer.setEndTime(d);
        customerRepository.saveAndFlush(customer);
        return ResponseEntity.status(200).body("Success");
    }
}
