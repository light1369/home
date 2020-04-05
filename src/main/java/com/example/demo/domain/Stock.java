package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/5 - 20:32
 */

public class Stock {
    private Integer id;
    private Integer gId;//商品id
    private double originalPrice;//原价
    private double amount;//数量
    private double freezeCount;//冻结总数
    private Integer batchNum;//批次
    private Integer status;


    public Stock() {
    }

    public Stock(Integer id, Integer gId, double originalPrice, double amount, double freezeCount, Integer batchNum, Integer status) {
        this.id = id;
        this.gId = gId;
        this.originalPrice = originalPrice;
        this.amount = amount;
        this.freezeCount = freezeCount;
        this.batchNum = batchNum;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFreezeCount() {
        return freezeCount;
    }

    public void setFreezeCount(double freezeCount) {
        this.freezeCount = freezeCount;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
