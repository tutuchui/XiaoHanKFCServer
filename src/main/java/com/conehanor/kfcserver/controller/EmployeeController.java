package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.conehanor.kfcserver.model.HanfcStatics;
import com.conehanor.kfcserver.model.OrderDetailForEmployee;
import com.conehanor.kfcserver.model.OrderForEmployee;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    Gson gson;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ManageOrderRepository manageOrderRepository;

    @Autowired
    ManageEmployeeRepository manageEmployeeRepository;

    @Autowired
    SuggestionRepository suggestionRepository;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body){
        Employee employee = gson.fromJson(body, Employee.class);
        Employee targetEmployee = employeeRepository.findByNumber(employee.getNumber());
        if(targetEmployee != null && employee.getPassword().equals(targetEmployee.getPassword()) && targetEmployee.getState() != 1){
            return new ResponseEntity<>(gson.toJson(targetEmployee.getName()), HttpStatus.OK);
        }else if(targetEmployee == null || targetEmployee.getState() == 1){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        }else if(!targetEmployee.getPassword().equals(employee.getPassword())){
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        }else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<String> getOrder(){
        List<ProductOrder> productOrderList = productOrderRepository.findAll();
        List<OrderForEmployee> orderForEmployeeList = new ArrayList<>();
        for(ProductOrder productOrder: productOrderList){
            Customer customer = customerRepository.findById(productOrder.getCustomerId()).get();
            ManageOrder manageOrder = manageOrderRepository.findAllByOrderId(productOrder.getProductOrderId()).get(0);
            OrderForEmployee orderForEmployee = new OrderForEmployee();
            orderForEmployee.setOrderId(productOrder.getProductOrderId());
            orderForEmployee.setCustomerName(customer.getName());
            orderForEmployee.setPhone(customer.getPhone());
            orderForEmployee.setPaymentStatus(manageOrder.getPaymentStatus());
            orderForEmployee.setOrderStatus(manageOrder.getOrderStatus());
            orderForEmployeeList.add(orderForEmployee);
        }
        return new ResponseEntity<>(gson.toJson(orderForEmployeeList),HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails")
    public ResponseEntity<String> getOrderDetails(@RequestParam("orderId") int orderId){
        ProductOrder productOrder = productOrderRepository.findById(orderId).get();
        Customer customer = customerRepository.findById(productOrder.getCustomerId()).get();
        List<ManageOrder> manageOrderList = manageOrderRepository.findAllByOrderId(orderId);
        OrderDetailForEmployee orderDetailForEmployee = new OrderDetailForEmployee();
        orderDetailForEmployee.setOrderId(orderId);
        orderDetailForEmployee.setCustomerName(customer.getName());
        orderDetailForEmployee.setOrderTime(productOrder.getOrderDate().toString());
        orderDetailForEmployee.setOrderStatus(manageOrderList.get(0).getOrderStatus());
        orderDetailForEmployee.setPaymentStatus(manageOrderList.get(0).getPaymentStatus());
        orderDetailForEmployee.setHistoryOrderStatus(new ArrayList<>());
        for(ManageOrder manageOrder : manageOrderList){
            OrderDetailForEmployee.OrderStatus orderStatus = new OrderDetailForEmployee.OrderStatus();
            orderStatus.setOrderStatus(manageOrder.getOrderStatus());
            orderStatus.setTime(manageOrder.getManageTime().toString());
            orderStatus.setPaymentStatus(manageOrder.getPaymentStatus());
            orderDetailForEmployee.getHistoryOrderStatus().add(orderStatus);
        }

        return new ResponseEntity<>(gson.toJson(orderDetailForEmployee), HttpStatus.OK);
    }

    @PostMapping("/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestBody String body){
        OrderDetailForEmployee orderDetailForEmployee = gson.fromJson(body, OrderDetailForEmployee.class);
        ManageOrder manageOrder = new ManageOrder();
        manageOrder.setProductOrderId(orderDetailForEmployee.getOrderId());
        manageOrder.setPaymentStatus(orderDetailForEmployee.getPaymentStatus());
        manageOrder.setOrderStatus(orderDetailForEmployee.getOrderStatus());
        manageOrder.setManageTime(new Timestamp(System.currentTimeMillis()));
        manageOrderRepository.saveAndFlush(manageOrder);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }


    @PostMapping("/addEmployee")
    @Transactional
    public ResponseEntity<String> addEmployee(@RequestBody String body){
        Employee employee = gson.fromJson(body, Employee.class);
        employee.setEmployeeId(generateEmployeeId());
        employeeRepository.saveAndFlush(employee);
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.setEmployeeId(employee.getEmployeeId()); ;
        manageEmployee.setAdminId(1);
        manageEmployee.setManageTime(new Timestamp(System.currentTimeMillis()));
        manageEmployee.setManageType(0);
        manageEmployeeRepository.saveAndFlush(manageEmployee);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    private int generateEmployeeId(){
        if(employeeRepository.findLatestEmployeeId().size() > 0){
            return employeeRepository.findLatestEmployeeId().get(0).getEmployeeId() + 1;
        }else{
            return 1;
        }
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<String> getAllEmployee(){
        return new ResponseEntity<>(gson.toJson(employeeRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/fire")
    @Transactional
    public ResponseEntity<String> fireEmployee(@RequestParam("employeeNumber") String employeeNumber)
    {
        try {
            employeeRepository.updateProductState(1,employeeNumber);
            Employee employee = employeeRepository.findByNumber(employeeNumber);
            ManageEmployee manageEmployee = new ManageEmployee();
            manageEmployee.setEmployeeId(employee.getEmployeeId());
            manageEmployee.setAdminId(1);
            manageEmployee.setManageType(1);
            manageEmployee.setManageTime(new Timestamp(System.currentTimeMillis()));
            manageEmployeeRepository.saveAndFlush(manageEmployee);
            return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity<String> getEmployeeById(@RequestParam("employeeId") int employeeId)
    {
//          Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee employee = employeeRepository.findById(employeeId).get();
        return new ResponseEntity<>(gson.toJson(employee), HttpStatus.OK);
    }

    @GetMapping("/getAddTimeById")
    public ResponseEntity<String> getAddTimeById(@RequestParam("employeeId") int employeeId)
    {
        Timestamp addTime = manageEmployeeRepository.getAddTimeById(employeeId);
        return new ResponseEntity<>(gson.toJson(addTime.toString()), HttpStatus.OK);
    }

    @GetMapping("/getFireTimeById")
    public ResponseEntity<String> getFireTimeById(@RequestParam("employeeId") int employeeId)
    {
        Timestamp timestamp = manageEmployeeRepository.getFireTimeById(employeeId);
        String string = "暂时没有解聘";
        if(timestamp == null)
        {
            return new ResponseEntity<>(gson.toJson(string), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(gson.toJson(timestamp.toString()), HttpStatus.OK);
        }
    }


}
