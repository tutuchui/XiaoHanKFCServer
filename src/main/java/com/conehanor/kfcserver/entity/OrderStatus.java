package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_status")
@IdClass(OrderStatusId.class)
public class OrderStatus {
    @Column(name = "order_id", nullable = false)
    @Id
    private String orderId;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "order_status", nullable = false)
    private int orderStatus;

    @Column(name = "payment_status", nullable = false)
    private int paymentStatus;

    @Column(name = "update_time", nullable = false)
    @Id
    private Timestamp updateTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
