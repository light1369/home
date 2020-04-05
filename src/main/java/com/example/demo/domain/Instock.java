package com.example.demo.domain;

import java.util.Date;

/**
 * @author Duan
 * @date 2020/4/5 - 14:13
 */
public class Instock {
    private Integer id;
    private Integer supplierId;
    private double totalMoney;//总价
    private Date createTime;//入库时间
    private String orderNumber;//订单数
    private Integer status;//状态

    public Instock() {
    }

    public Instock(Integer id, Integer supplierId, double totalMoney, Date createTime, String orderNumber, Integer status) {
        this.id = id;
        this.supplierId = supplierId;
        this.totalMoney = totalMoney;
        this.createTime = createTime;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
