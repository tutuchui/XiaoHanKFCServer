package com.conehanor.kfcserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", nullable =  false)
    private String number;


    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "phone", nullable =  false)
    private String phone;

    @Column(name = "email", nullable =  false)
    private String email;

    @Column(name = "state", nullable = false)
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
