package com.example.demo.domain;

import java.util.Date;

/**
 * @author Duan
 * @date 2020/4/9 - 11:08
 */
public class sales {

    private Integer id;
    private Integer staffId;
    private Date createTime;
    private double totalAmount;
    private String singleNumber;
    private Integer status;

    public sales() {
    }

    public sales(Integer id, Integer staffId, Date createTime, double totalAmount, String singleNumber, Integer status) {
        this.id = id;
        this.staffId = staffId;
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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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
