package com.conehanor.kfcserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
  @Column(name = "customer_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long customerId;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "phone", nullable = false)
  private String phone;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "photo", nullable = false)
  private String photo;
  @Column(name = "address", nullable = false)
  private String address;
  @Column(name = "state", nullable = false)
  private long state;
  @Column(name = "birthday", nullable = false)
  private java.sql.Date birthday;
  @Column(name = "creat_time", nullable = false)
  private java.sql.Date creatTime;
  @Column(name = "end_time", nullable = false)
  private java.sql.Date endTime;


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }


  public java.sql.Date getCreatTime() {
    return creatTime;
  }

  public void setCreatTime(java.sql.Date creatTime) {
    this.creatTime = creatTime;
  }


  public java.sql.Date getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Date endTime) {
    this.endTime = endTime;
  }

}
