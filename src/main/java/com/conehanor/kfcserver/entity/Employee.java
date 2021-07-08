package com.conehanor.kfcserver.entity;


import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "number", nullable = false)
  private String number;
  @Column(name = "image_url", nullable = false)
  private String imageUrl;
  @Column(name = "type", nullable = false)
  private String type;
  @Column(name = "phone", nullable = false)
  private String phone;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "introduction", nullable = false)
  private String introduction;
  @Column(name = "state", nullable = false)
  private long state;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
