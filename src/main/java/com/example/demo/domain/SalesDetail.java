package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/9 - 11:15
 */
public class SalesDetail {
    private Integer id;
    private Integer goodId;
    private Integer salesId;
    private double amount;
    private double price;//价格
    private double subtotal;//小计
    private Integer status;

    public SalesDetail() {
    }

    public SalesDetail(Integer id, Integer goodId, Integer salesId, double amount, double price, double subtotal, Integer status) {
        this.id = id;
        this.goodId = goodId;
        this.salesId = salesId;
        this.amount = amount;
        this.price = price;
        this.subtotal = subtotal;
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

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice(double price) {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
