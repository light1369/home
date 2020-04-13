package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/10 - 21:38
 */
public class OutStockDetail {
    private Integer id;
    private Integer goodsId;//商品id
    private double originalPrice;//商品原价
    private double amount;//总计
    private Integer outStockId;//库存id
    private Integer status;//状态

    public OutStockDetail() {
    }

    public OutStockDetail(Integer id, Integer goodsId, double originalPrice, double amount, Integer outStockId, Integer status) {
        this.id = id;
        this.goodsId = goodsId;
        this.originalPrice = originalPrice;
        this.amount = amount;
        this.outStockId = outStockId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getOutStockId() {
        return outStockId;
    }

    public void setOutStockId(Integer outStockId) {
        this.outStockId = outStockId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
