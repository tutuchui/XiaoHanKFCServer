package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.CustomerRepository;
import com.conehanor.kfcserver.entity.Customer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    Gson gson;

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody String body){
        Customer customer = gson.fromJson(body, Customer.class);
        customerRepository.saveAndFlush(customer);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body){
        Customer customer = gson.fromJson(body, Customer.class);
        Customer targetCustomer = customerRepository.findCustomerByPhone(customer.getPhone());
        if(targetCustomer!=null && targetCustomer.getPassword().equals(customer.getPassword())){
            return new ResponseEntity<>(gson.toJson(targetCustomer.getName()), HttpStatus.OK);
        }else if(targetCustomer == null){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        }else if(!targetCustomer.getPassword().equals(customer.getPassword())){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        }else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
