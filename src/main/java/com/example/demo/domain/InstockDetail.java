package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/4/5 - 14:21
 */
public class InstockDetail {

    private Integer id;
    private Integer goodsId;//商品id
    private double originalPrice;//商品原价
    private Integer amount;//总计
    private Integer instockId;//库存id
    private Integer status;//状态

    public InstockDetail() {
    }

    public InstockDetail(Integer id, Integer goodsId, double originalPrice, Integer amount, Integer instockId, Integer status) {
        this.id = id;
        this.goodsId = goodsId;
        this.originalPrice = originalPrice;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
