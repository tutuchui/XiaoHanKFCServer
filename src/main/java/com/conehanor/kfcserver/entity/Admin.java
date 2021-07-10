package com.conehanor.kfcserver.entity;

import com.conehanor.kfcserver.dao.AdminRepository;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin{
  @Column(name = "admin_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long adminId;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "number", nullable = false)
  private String number;
  @Column(name = "password", nullable = false)
  private String password;


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
