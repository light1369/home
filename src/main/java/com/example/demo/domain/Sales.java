package com.example.demo.domain;

import java.util.Date;

/**
 * @author Duan
 * @date 2020/4/9 - 11:08
 */
public class Sales {

    private Integer id;
    private Integer userId;
    private Date createTime;
    private double totalAmount;
    private String singleNumber;
    private Integer status;

    public Sales() {
    }

    public Sales(Integer id, Integer userId, Date createTime, double totalAmount, String singleNumber, Integer status) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.totalAmount = totalAmount;
        this.singleNumber = singleNumber;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSingleNumber() {
        return singleNumber;
    }

    public void setSingleNumber(String singleNumber) {
        this.singleNumber = singleNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
