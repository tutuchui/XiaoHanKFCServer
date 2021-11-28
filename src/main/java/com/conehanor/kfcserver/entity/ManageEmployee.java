package com.conehanor.kfcserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "manage_employee", schema = "han_fc", catalog = "")
public class ManageEmployee {
    private int manageEmployeeId;
    private int manageType;
    private Timestamp manageTime;

    @Id
    @Column(name = "manage_employee_id")
    public int getManageEmployeeId() {
        return manageEmployeeId;
    }

    public void setManageEmployeeId(int manageEmployeeId) {
        this.manageEmployeeId = manageEmployeeId;
    }

    @Basic
    @Column(name = "manage_type")
    public int getManageType() {
        return manageType;
    }

    public void setManageType(int manageType) {
        this.manageType = manageType;
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
        ManageEmployee that = (ManageEmployee) o;
        return manageEmployeeId == that.manageEmployeeId && manageType == that.manageType && Objects.equals(manageTime, that.manageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manageEmployeeId, manageType, manageTime);
    }
}
