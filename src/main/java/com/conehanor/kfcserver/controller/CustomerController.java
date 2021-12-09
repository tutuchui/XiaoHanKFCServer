package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.*;
import com.conehanor.kfcserver.entity.*;
import com.conehanor.kfcserver.model.OrderDetailForCustomer;
import com.conehanor.kfcserver.model.OrderForCustomer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    Gson gson;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ManageOrderRepository manageOrderRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody String body) {
        Customer customer = gson.fromJson(body, Customer.class);
        if(customerRepository.findCustomerByPhone(customer.getPhone())!=null){
            return new ResponseEntity<>(gson.toJson("Phone Number Already Register"), HttpStatus.SERVICE_UNAVAILABLE);
        }
        customerRepository.saveAndFlush(customer);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String body) {
        Customer customer = gson.fromJson(body, Customer.class);
        Customer targetCustomer = customerRepository.findCustomerByPhone(customer.getPhone());
        if (targetCustomer != null && targetCustomer.getPassword().equals(customer.getPassword())) {
            return new ResponseEntity<>(gson.toJson(targetCustomer), HttpStatus.OK);
        } else if (targetCustomer == null) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.NOT_IMPLEMENTED);
        } else if (!targetCustomer.getPassword().equals(customer.getPassword())) {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.BAD_GATEWAY);
        } else {
            return new ResponseEntity<>(gson.toJson("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/submitOrder")
    @Transactional
    public ResponseEntity<String> submitOrder(@RequestBody String requestBody) {
        try {
            JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
            JsonArray jsonOrderDetails = jsonObject.getAsJsonArray("orderProductList");
            int productOrderId = generateOrderId();
            int totalPrice = jsonObject.get("totalPrice").getAsInt();
            int customerId = jsonObject.get("customerId").getAsInt();

            ProductOrder productOrder = new ProductOrder();
            productOrder.setProductOrderId(productOrderId);
            productOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
            productOrder.setPrice(totalPrice);
            productOrder.setCustomerId(customerId);
            productOrderRepository.saveAndFlush(productOrder);

            for (JsonElement curOrderEle : jsonOrderDetails) {
                JsonObject curOrder = curOrderEle.getAsJsonObject();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProductOrderId(productOrderId);
                orderDetail.setProductId(curOrder.get("productId").getAsInt());
                orderDetail.setNumber(curOrder.get("productCount").getAsInt());
                orderDetailRepository.saveAndFlush(orderDetail);
            }

            ManageOrder manageOrder = new ManageOrder();
            manageOrder.setProductOrderId(productOrderId);
            manageOrder.setOrderStatus(0);
            manageOrder.setPaymentStatus(0);
            manageOrder.setManageTime(new Timestamp(System.currentTimeMillis()));
            manageOrderRepository.saveAndFlush(manageOrder);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrdersForCustomer")
    public ResponseEntity<String> getOrdersForCustomer(@RequestParam("customerId")int customerId){
        Customer customer = customerRepository.findById(customerId).get();
        List<ProductOrder> productOrderList = productOrderRepository.getOrdersByCustomerId(customerId);
        List<OrderForCustomer> orderForCustomerList = new ArrayList<>();
        for(ProductOrder productOrder: productOrderList){
            ManageOrder manageOrder = manageOrderRepository.findAllByOrderId(productOrder.getProductOrderId()).get(0);
            OrderForCustomer orderForCustomer = new OrderForCustomer();
            orderForCustomer.setCustomerName(customer.getName());
            orderForCustomer.setOrderId(productOrder.getProductOrderId());
            orderForCustomer.setOrderTime(productOrder.getOrderDate());
            orderForCustomer.setPhone(customer.getPhone());
            orderForCustomer.setPaymentStatus(manageOrder.getPaymentStatus());
            orderForCustomer.setOrderStatus(manageOrder.getOrderStatus());
            orderForCustomer.setTotalPrice(productOrder.getPrice());
            orderForCustomerList.add(orderForCustomer);
        }

        return new ResponseEntity<>(gson.toJson(orderForCustomerList), HttpStatus.OK);
    }

    private int generateOrderId() {
        return productOrderRepository.generateOrderId() + 1;

    }

    @GetMapping("/getOrderDetail")
    public ResponseEntity<String> getOrderDetail(@RequestParam("orderId")int orderId){
        List<OrderDetail> orderDetailList = orderDetailRepository.getOrderDetailByOrderId(orderId);
        List<OrderDetailForCustomer> orderDetailForCustomerList = new ArrayList<>();
        for(OrderDetail orderDetail: orderDetailList){
            OrderDetailForCustomer orderDetailForCustomer = new OrderDetailForCustomer();
            orderDetailForCustomer.setProductId(orderDetail.getProductId());
            orderDetailForCustomer.setNumber(orderDetail.getNumber());
            String productName = productRepository.findById(orderDetail.getProductId()).get().getName();
            orderDetailForCustomer.setProductName(productName);
            orderDetailForCustomerList.add(orderDetailForCustomer);
        }

        return new ResponseEntity<>(gson.toJson(orderDetailForCustomerList), HttpStatus.OK);
    }

    @PostMapping("/payOrder")
    public ResponseEntity<String> payOrder(@RequestBody String requestBody){
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        int orderId = jsonObject.get("orderId").getAsInt();
        int orderStatus = jsonObject.get("orderStatus").getAsInt();

        ManageOrder manageOrder = new ManageOrder();
        manageOrder.setProductOrderId(orderId);
        manageOrder.setOrderStatus(orderStatus);
        manageOrder.setPaymentStatus(1);
        manageOrder.setManageTime(new Timestamp(System.currentTimeMillis()));

        manageOrderRepository.saveAndFlush(manageOrder);
        return new ResponseEntity<>(gson.toJson("SUCCESS"), HttpStatus.OK);
    }

}
