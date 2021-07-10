package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductOrderDetailId implements Serializable {

    private String orderId;

    private int productId;

    private String customerId;

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
}
