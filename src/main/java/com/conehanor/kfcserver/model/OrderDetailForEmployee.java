package com.conehanor.kfcserver.model;

import java.util.List;

public class OrderDetailForEmployee {
    private int orderId;
    private String customerName;
    private String orderTime;
    private int orderStatus;
    private int paymentStatus;
    private List<OrderStatus> historyOrderStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public List<OrderStatus> getHistoryOrderStatus() {
        return historyOrderStatus;
    }

    public void setHistoryOrderStatus(List<OrderStatus> historyOrderStatus) {
        this.historyOrderStatus = historyOrderStatus;
    }

    public static class OrderStatus{
        String time;
        int orderStatus;
        int paymentStatus;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
    }
}
