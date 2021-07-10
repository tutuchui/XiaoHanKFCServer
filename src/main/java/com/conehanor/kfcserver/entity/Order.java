package com.conehanor.kfcserver.entity;

import com.conehanor.kfcserver.dao.OrderRepository;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
  @Column(name = "order_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long orderId;
  @Column(name = "number", nullable = false)
  private String number;
  @Column(name = "customerId", nullable = false)
  private int customerId;
  @Column(name = "employeeId", nullable = false)
  private int employeeId;
  @Column(name = "paymentState", nullable = false)
  private String paymentState;
  @Column(name = "distributionMode", nullable = false)
  private String distributionMode;
  @Column(name = "price", nullable = false)
  private long price;

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }


  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }


  public String getPaymentState() {
    return paymentState;
  }

  public void setPaymentState(String paymentState) {
    this.paymentState = paymentState;
  }


  public String getDistributionMode() {
    return distributionMode;
  }

  public void setDistributionMode(String distributionMode) {
    this.distributionMode = distributionMode;
  }


  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

}
