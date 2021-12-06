package com.conehanor.kfcserver.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Production {
    private int productionId;
    private int productId;
    private int adminId;
    private int number;
    private Timestamp productionTime;

    @Id
    @Column(name = "production_id")
    public int getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "admin_id")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "production_time")
    public Timestamp getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Timestamp productionTime) {
        this.productionTime = productionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Production that = (Production) o;
        return productionId == that.productionId && number == that.number && Objects.equals(productionTime, that.productionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionId, number, productionTime);
    }
}
