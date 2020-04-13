package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Duan
 * @date 2020/4/10 - 21:32
 */
public class OutStock {
    private Integer id;
    private Integer supplierId;
    private Integer userId;
    private double totalMoney;//总价
    private Date createTime;//入库时间
    private String orderNumber;//订单数
    private Integer status;//状态

    private List<OutStockDetail> outStockDetails=new ArrayList<>();

    public OutStock() {
    }

    public OutStock(Integer id, Integer supplierId, Integer userId, double totalMoney, Date createTime, String orderNumber, Integer status, List<OutStockDetail> outStockDetails) {
        this.id = id;
        this.supplierId = supplierId;
        this.userId = userId;
        this.totalMoney = totalMoney;
        this.createTime = createTime;
        this.orderNumber = orderNumber;
        this.status = status;
        this.outStockDetails = outStockDetails;
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

    public List<OutStockDetail> getOutStockDetails() {
        return outStockDetails;
    }

    public void setOutStockDetails(List<OutStockDetail> outStockDetails) {
        this.outStockDetails = outStockDetails;
    }
}
