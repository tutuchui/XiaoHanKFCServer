package com.conehanor.kfcserver.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderDetailPK implements Serializable {
    private int productOrderId;
    private int productId;

    @Column(name = "product_order_id")
    @Id
    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return productOrderId == that.productOrderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productOrderId, productId);
    }
}
