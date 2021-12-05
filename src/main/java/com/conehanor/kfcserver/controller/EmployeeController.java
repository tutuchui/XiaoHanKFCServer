package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.EmployeeRepository;
import com.conehanor.kfcserver.entity.Admin;
import com.conehanor.kfcserver.entity.Employee;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    Gson gson;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body){
        Employee employee = gson.fromJson(body, Employee.class);
        Employee targetEmployee = employeeRepository.findByNumber(employee.getNumber());
        if(targetEmployee != null && employee.getPassword().equals(targetEmployee.getPassword())){
            return new ResponseEntity<>(gson.toJson(targetEmployee.getName()), HttpStatus.OK);
        }else if(targetEmployee == null){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        }else if(!targetEmployee.getPassword().equals(employee.getPassword())){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        }else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
