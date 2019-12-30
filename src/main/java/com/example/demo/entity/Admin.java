package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private String adminName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    //是否有效
    private BooleanEnum isEffective;

    public BooleanEnum getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(BooleanEnum isEffective) {
        this.isEffective = isEffective;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
