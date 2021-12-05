package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.dao.CustomerRepository;
import com.conehanor.kfcserver.dao.ManageOrderRepository;
import com.conehanor.kfcserver.dao.OrderDetailRepository;
import com.conehanor.kfcserver.dao.ProductOrderRepository;
import com.conehanor.kfcserver.entity.Customer;
import com.conehanor.kfcserver.entity.ManageOrder;
import com.conehanor.kfcserver.entity.OrderDetail;
import com.conehanor.kfcserver.entity.ProductOrder;
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
    ManageOrderRepository manageOrderRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody String body) {
        Customer customer = gson.fromJson(body, Customer.class);
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

    private int generateOrderId() {
        return productOrderRepository.getOrderCount() + 1;
    }

}
