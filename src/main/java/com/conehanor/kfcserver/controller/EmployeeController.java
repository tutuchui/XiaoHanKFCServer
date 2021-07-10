package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.util.Password;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ServletContext servletContext;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("/getAllEmployee")
    @CrossOrigin
    public String getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return new Gson().toJson(employeeList);
    }

    @PostMapping("/employee_login")
    @CrossOrigin
    public ResponseEntity<String> employeeLogin(@RequestParam("number") String number,
                                                @RequestParam("password") String password) {
        System.out.println(number);
        String password1 = employeeRepository.selectByNumber(number);
        if (password1.equals(password)) {
            System.out.println(1);
            return ResponseEntity.status(200).body("Success");
        } else {
            System.out.println(2);
            return ResponseEntity.status(500).body("Error");
        }

    }

    @PostMapping("/fireEmployee")
    @CrossOrigin
    public ResponseEntity<String> fireEmployee(@RequestParam("number") String number) {
        employeeRepository.deleteByNumber(number);
        return ResponseEntity.status(200).body("Success");
    }
}
