package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_detail", schema = "han_fc", catalog = "")
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    private int productOrderId;
    private int productId;
    private int number;

    @Id
    @Column(name = "product_order_id")
    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return productOrderId == that.productOrderId && productId == that.productId && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productOrderId, productId, number);
    }
}
