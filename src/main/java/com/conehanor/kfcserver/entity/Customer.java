package com.conehanor.kfcserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "phone", nullable = false)
  @Id
  private String phone;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "address")
  private String address;

  @Column(name = "gender", nullable = false)
  private int gender;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }
}
