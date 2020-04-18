package com.example.demo.domain;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Duan
 * @date 2020/4/10 - 12:50
 */
public class Refunds {

    private Integer id;
    @NotBlank//(message = "用户id不能为空")
    private Integer userId;
    private double totalMoney;
    private double payment;
    private double changeMoney;
    private Date createTime;
    private String orderNumber;
    private String salesNumber;
    private Integer status;

    private List<RefundsDetail> refundsDetail=new ArrayList<>();


    public Refunds(){}

    public Refunds(Integer id, @NotBlank Integer userId, double totalMoney, double payment, double changeMoney, Date createTime, String orderNumber, String salesNumber, Integer status, List<RefundsDetail> refundsDetail) {
        this.id = id;
        this.userId = userId;
        this.totalMoney = totalMoney;
        this.payment = payment;
        this.changeMoney = changeMoney;
        this.createTime = createTime;
        this.orderNumber = orderNumber;
        this.salesNumber = salesNumber;
        this.status = status;
        this.refundsDetail = refundsDetail;
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

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RefundsDetail> getRefundsDetail() {
        return refundsDetail;
    }

    public void setRefundsDetail(List<RefundsDetail> refundsDetail) {
        this.refundsDetail = refundsDetail;
    }
}
