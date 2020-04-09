package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/9 - 11:15
 */
public class salesDetail {
    private Integer id;
    private Integer goodId;
    private double orignalPrice;//小计
    private double amount;
    private Integer instockId;
    private Integer status;

    public salesDetail() {
    }

    public salesDetail(Integer id, Integer goodId, double orignalPrice, double amount, Integer instockId, Integer status) {
        this.id = id;
        this.goodId = goodId;
        this.orignalPrice = orignalPrice;
        this.amount = amount;
        this.instockId = instockId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public double getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(double orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getInstockId() {
        return instockId;
    }

    public void setInstockId(Integer instockId) {
        this.instockId = instockId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
