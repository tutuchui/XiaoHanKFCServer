package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_order_detail")
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
}
