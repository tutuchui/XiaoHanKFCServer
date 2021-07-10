package com.conehanor.kfcserver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderStatusId implements Serializable {

    private String orderId;
    private Timestamp updateTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
