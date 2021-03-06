package com.conehanor.kfcserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_order_detail")
@IdClass(ProductOrderDetailId.class)
public class ProductOrderDetail {
    @Column(name = "order_id", nullable = false)
    @Id
    private String orderId;

    @Column(name = "product_id", nullable = false)
    @Id
    private int productId;

    @Column(name = "customer_id", nullable = false)
    @Id
    private String customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_count", nullable = false)
    private int productCount;

    @Column(name = "price", nullable = false)
    private double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
