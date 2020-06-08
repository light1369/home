package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/3/24 - 10:46
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private double cashierMoney;
    private Integer jurisdictionId;
    private String perms;
    private int status;


    public User() {
    }

    public User(Integer id, String name, String password, double cashierMoney, Integer jurisdictionId, int status) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cashierMoney = cashierMoney;
        this.jurisdictionId = jurisdictionId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCashierMoney() {
        return cashierMoney;
    }

    public void setCashierMoney(double cashierMoney) {
        this.cashierMoney = cashierMoney;
    }

    public Integer getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Integer jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
