package com.conehanor.kfcserver.model;

import java.util.List;

public class ChartStatics {
    private int activeEmployee;
    private int inactiveEmployee;

    private int employeeType0Count;
    private int employeeType1Count;
    private int employeeType2Count;
    private int employeeType3Count;

    private int employeeBoy;
    private int employeeGirl;

    private int customerBoy;
    private int customerGirl;

    private List<ProductStatics> productStaticsList;

    public List<ProductStatics> getProductStaticsList() {
        return productStaticsList;
    }

    public void setProductStaticsList(List<ProductStatics> productStaticsList) {
        this.productStaticsList = productStaticsList;
    }

    public int getActiveEmployee() {
        return activeEmployee;
    }

    public void setActiveEmployee(int activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

    public int getInactiveEmployee() {
        return inactiveEmployee;
    }

    public void setInactiveEmployee(int inactiveEmployee) {
        this.inactiveEmployee = inactiveEmployee;
    }

    public int getEmployeeType0Count() {
        return employeeType0Count;
    }

    public void setEmployeeType0Count(int employeeType0Count) {
        this.employeeType0Count = employeeType0Count;
    }

    public int getEmployeeType1Count() {
        return employeeType1Count;
    }

    public void setEmployeeType1Count(int employeeType1Count) {
        this.employeeType1Count = employeeType1Count;
    }

    public int getEmployeeType2Count() {
        return employeeType2Count;
    }

    public void setEmployeeType2Count(int employeeType2Count) {
        this.employeeType2Count = employeeType2Count;
    }

    public int getEmployeeType3Count() {
        return employeeType3Count;
    }

    public void setEmployeeType3Count(int employeeType3Count) {
        this.employeeType3Count = employeeType3Count;
    }

    public int getEmployeeBoy() {
        return employeeBoy;
    }

    public void setEmployeeBoy(int employeeBoy) {
        this.employeeBoy = employeeBoy;
    }

    public int getEmployeeGirl() {
        return employeeGirl;
    }

    public void setEmployeeGirl(int employeeGirl) {
        this.employeeGirl = employeeGirl;
    }

    public int getCustomerBoy() {
        return customerBoy;
    }

    public void setCustomerBoy(int customerBoy) {
        this.customerBoy = customerBoy;
    }

    public int getCustomerGirl() {
        return customerGirl;
    }

    public void setCustomerGirl(int customerGirl) {
        this.customerGirl = customerGirl;
    }
}
