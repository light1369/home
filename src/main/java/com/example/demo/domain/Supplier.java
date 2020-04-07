package com.example.demo.domain;

import org.springframework.stereotype.Repository;

/**
 * @author Duan
 * @date 2020/4/6 - 17:26
 */

public class Supplier {
    private Integer id;
    private String name;//供应商名
    private String address;//地址
    private String contact;//联系方式
    private String tel;////电话
    private double deliverMoney;//交付金额
    private Integer status;

    public Supplier() {
    }

    public Supplier(Integer id, String name, String address, String contact, String tel, double deliverMoney, Integer status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.tel = tel;
        this.deliverMoney = deliverMoney;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(double deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
