package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "manage_order", schema = "han_fc", catalog = "")
public class ManageOrder {
    private int manageOrderId;
    private int productOrderId;
    private int orderStatus;
    private int paymentStatus;
    private Timestamp manageTime;

    @Id
    @Column(name = "manage_order_id")
    public int getManageOrderId() {
        return manageOrderId;
    }

    public void setManageOrderId(int manageOrderId) {
        this.manageOrderId = manageOrderId;
    }

    @Column(name = "product_order_id")
    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Basic
    @Column(name = "order_status")
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "payment_status")
    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Basic
    @Column(name = "manage_time")
    public Timestamp getManageTime() {
        return manageTime;
    }

    public void setManageTime(Timestamp manageTime) {
        this.manageTime = manageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManageOrder that = (ManageOrder) o;
        return manageOrderId == that.manageOrderId && orderStatus == that.orderStatus && paymentStatus == that.paymentStatus && Objects.equals(manageTime, that.manageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manageOrderId, orderStatus, paymentStatus, manageTime);
    }
}
