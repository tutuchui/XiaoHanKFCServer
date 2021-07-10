package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProductOrderDetailRepository productOrderDetailRepository;


    @GetMapping("/data")
    @CrossOrigin
    public String data(){
        int numberOfMan=0;
        int numberOfWomen=0;
        int numberOfDeliveryman=0;
        int numberOfCook=0;
        int numberOfWaiter=0;
        int numberOfState1=0;
        int numberOfState0=0;
        List<Customer> customerList =customerRepository.findAll();
        for(Customer customer:customerList)
        {
           if(customer.getGender() ==1) numberOfMan++;
           if(customer.getGender() ==0) numberOfWomen++;
        }
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee:employeeList)
        {
            if("配送员".equals(employee.getType())) numberOfDeliveryman++;
            if("服务员".equals(employee.getType())) numberOfWaiter ++;
            if("后厨".equals(employee.getType())) numberOfCook++;
            if("0".equals(employee.getState())) numberOfState0++;
            if("1".equals(employee.getState())) numberOfState1++;
        }

        return new Gson().toJson(numberOfState0);
    }
}
