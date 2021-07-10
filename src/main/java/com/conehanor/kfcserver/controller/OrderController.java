package com.conehanor.kfcserver.controller;

import com.conehanor.kfcserver.constant.OrderConstant;
import com.conehanor.kfcserver.dao.OrderStatusRepository;
import com.conehanor.kfcserver.dao.ProductOrderDetailRepository;
import com.conehanor.kfcserver.dao.ProductOrderRepository;
import com.conehanor.kfcserver.entity.OrderStatus;
import com.conehanor.kfcserver.entity.ProductOrder;
import com.conehanor.kfcserver.entity.ProductOrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @PostMapping("/submitOrder")
    @CrossOrigin
    public String submitOrder(@RequestBody String requestBody){

        int count = productOrderRepository.getOrderCount();
        System.out.println(requestBody);
        String orderId = "ORDER" + String.format("%06d", count + 1);
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

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCustomerId(productDetailOrders.get(0).getCustomerId());
        orderStatus.setPaymentStatus(OrderConstant.UNPAID);
        orderStatus.setOrderStatus(OrderConstant.NOT_PREPARED);
        orderStatus.setCustomerName(productDetailOrders.get(0).getCustomerName());
        orderStatus.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderStatusRepository.saveAndFlush(orderStatus);

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
    public String getOrderDetailById(@RequestParam("orderId") String orderId){
        List<ProductOrderDetail> productOrderDetailList = productOrderDetailRepository.selectProductOrderByOrderId(orderId);
        return new Gson().toJson(productOrderDetailList);
    }

    @PostMapping("/updatePaymentStatus")
    @CrossOrigin
    public String updatePaymentStatus(@RequestParam("orderId") String orderId){
        int result = productOrderRepository.updatePaymentStatus(OrderConstant.PAID, orderId);
        System.out.println(result);
        return "Success";
    }

    @GetMapping("/getAllOrders")
    @CrossOrigin

    public String getAllOrders(){
        List<ProductOrder> productOrderList = productOrderRepository.findAll();
        return new Gson().toJson(productOrderList);
    }

    @GetMapping("/getOrderStatusDetail")
    @CrossOrigin
    public String getOrderStatus(@RequestParam("orderId") String orderId){
        ProductOrder order = productOrderRepository.findById(orderId).get();
        return new Gson().toJson(order);
    }

    @GetMapping("/getHistoryOrderStatus")
    @CrossOrigin
    public String getHistoryOrderStatus(@RequestParam("orderId") String orderId){
        List<OrderStatus> orderStatusList = orderStatusRepository.selectOrderStatusById(orderId);
        return new Gson().toJson(orderStatusList);
    }

    @PostMapping("/updateOrderStatus")
    @CrossOrigin
    public String updateOrderStatus(@RequestParam("paymentStatus")int paymentStatus, @RequestParam("orderStatus") int orderStatus, @RequestParam("orderId")String orderId,
                                    @RequestParam("customerId")String customerId, @RequestParam("customerName")String customerName)
    {
        OrderStatus orderStatusRecord = new OrderStatus();
        orderStatusRecord.setOrderId(orderId);
        orderStatusRecord.setCustomerId(customerId);
        orderStatusRecord.setCustomerName(customerName);
        orderStatusRecord.setOrderStatus(orderStatus);
        orderStatusRecord.setPaymentStatus(paymentStatus);
        orderStatusRecord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderStatusRepository.saveAndFlush(orderStatusRecord);
        productOrderRepository.updatePaymentOrderStatus(paymentStatus, orderStatus, orderId);
        return "Success";
    }


}
