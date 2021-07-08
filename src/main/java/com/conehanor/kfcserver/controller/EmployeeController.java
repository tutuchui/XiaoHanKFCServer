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
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ServletContext servletContext;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("/getAllEmployee")
    @CrossOrigin
    public String getAllEmployee(){
        List<Employee> employeeList = employeeRepository.findAll();
        return new Gson().toJson(employeeList);
    }

}
