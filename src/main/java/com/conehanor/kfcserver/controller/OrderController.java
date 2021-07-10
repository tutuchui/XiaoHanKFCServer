package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.constant.OrderConstant;
import com.conehanor.kfcserver.dao.ProductOrderDetailRepository;
import com.conehanor.kfcserver.dao.ProductOrderRepository;
import com.conehanor.kfcserver.entity.ProductOrder;
import com.conehanor.kfcserver.entity.ProductOrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    ProductOrderDetailRepository productOrderDetailRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @PostMapping("/submitOrder")
    @CrossOrigin
    public String submitOrder(@RequestBody String requestBody){

        int count = productOrderRepository.getOrderCount();
        System.out.println(requestBody);
        String orderId = String.format("%06d", count + 1);
        double totalPrice = 0;
        Type listType = new TypeToken<List<ProductOrderDetail>>() {}.getType();
        List<ProductOrderDetail> productDetailOrders= new Gson().fromJson(requestBody, listType);

        for(ProductOrderDetail productOrderDetail : productDetailOrders){
            totalPrice += productOrderDetail.getPrice();
        }
        System.out.println(productDetailOrders.get(0).getCustomerName());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderId(orderId);
        productOrder.setCustomerId(productDetailOrders.get(0).getCustomerId());
        productOrder.setTotalPrice(totalPrice);
        productOrder.setCustomerName(productDetailOrders.get(0).getCustomerName());
        productOrder.setPaymentStatus(OrderConstant.UNPAID);
        productOrder.setOrderStatus(OrderConstant.NOT_PREPARED);
        productOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        productOrderRepository.saveAndFlush(productOrder);


        for(ProductOrderDetail productOrderDetail : productDetailOrders){
            productOrderDetail.setOrderId(orderId);
            productOrderDetailRepository.saveAndFlush(productOrderDetail);
        }

        return "Success";
    }

    @GetMapping("/getOrderByCustomer")
    @CrossOrigin
    public String getOrderByCustomer(@RequestParam("phone") String phone){
        List<ProductOrder> productOrderList = productOrderRepository.selectProductOrderByCustomer(phone);
        return new Gson().toJson(productOrderList);
    }

    @GetMapping("/getOrderDetailById")
    @CrossOrigin
    public String getOrderDetailById(@RequestParam("orderId") int orderId){
        String orderIdStr = String.format("%06d",orderId);
        List<ProductOrderDetail> productOrderDetailList = productOrderDetailRepository.selectProductOrderByOrderId(orderIdStr);
        return new Gson().toJson(productOrderDetailList);
    }

    @PostMapping("/updatePaymentStatus")
    @CrossOrigin
    public String updatePaymentStatus(@RequestParam("orderId") int orderId){
        String orderIdStr = String.format("%06d",orderId);
        int result = productOrderRepository.updatePaymentStatus(OrderConstant.PAID, orderIdStr);
        System.out.println(result);
        return "Success";
    }
}
