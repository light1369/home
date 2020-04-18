package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/10 - 13:17
 */
public class RefundsDetail {
    private Integer id;
    private Integer goodId;
    private double currentPrice;
    private double amount;
    private Integer refundsId;
    private Integer status;

    public RefundsDetail() {
    }

    public RefundsDetail(Integer id, Integer goodId, double currentPrice, double amount, Integer refundsId, Integer status) {
        this.id = id;
        this.goodId = goodId;
        this.currentPrice = currentPrice;
        this.amount = amount;
        this.refundsId = refundsId;
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getRefundsId() {
        return refundsId;
    }

    public void setRefundsId(Integer refundsId) {
        this.refundsId = refundsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
