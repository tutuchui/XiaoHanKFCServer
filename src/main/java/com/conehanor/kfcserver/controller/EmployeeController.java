package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.CustomerRepository;
import com.conehanor.kfcserver.dao.EmployeeRepository;
import com.conehanor.kfcserver.dao.ManageOrderRepository;
import com.conehanor.kfcserver.dao.ProductOrderRepository;
import com.conehanor.kfcserver.entity.*;
import com.conehanor.kfcserver.model.OrderDetailForEmployee;
import com.conehanor.kfcserver.model.OrderForEmployee;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/getAllOrders")
    public ResponseEntity<String> getOrder(){
        List<ProductOrder> productOrderList = productOrderRepository.findAll();
        List<OrderForEmployee> orderForEmployeeList = new ArrayList<>();
        for(ProductOrder productOrder: productOrderList){
            Customer customer = customerRepository.findById(productOrder.getCustomerId()).get();
            ManageOrder manageOrder = manageOrderRepository.findLatestStatusByProductOrderId(productOrder.getProductOrderId());
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

}
