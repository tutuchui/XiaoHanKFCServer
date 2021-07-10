package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.CustomerRepository;
import com.conehanor.kfcserver.entity.Customer;
import com.conehanor.kfcserver.util.CustomEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomEncryptor customEncryptor;

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestParam("name") String name,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email,
                                           @RequestParam("address") String address) {
        try {
            String encrytedPassword = customEncryptor.SHA256(password);
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPassword(encrytedPassword);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setPhone(phone);
            customerRepository.saveAndFlush(customer);
            return ResponseEntity.status(200).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }

    }

    @PostMapping("/customer_login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam("number") String phone, @RequestParam("password") String password) {
        try {
            System.out.println("lalalalala");
            Optional<Customer> optionalCustomer = customerRepository.findById(phone);
            if (!optionalCustomer.isPresent()) {
                return ResponseEntity.status(501).body("Customer Not Exists");
            }
            String correctPassword = optionalCustomer.get().getPassword();
            String givenPassword = customEncryptor.SHA256(password);
            if (correctPassword.equals(givenPassword)) {
                return ResponseEntity.status(200).body("Success");
            } else {
                return ResponseEntity.status(502).body("Incorrect Password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }

    }

}
