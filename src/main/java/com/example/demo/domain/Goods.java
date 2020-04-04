package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/3/27 - 11:05
 */
public class Goods {
    private Integer id;
    private String name;
    private Integer unitId;
    private Integer classId;
    private double price;
    private Integer supplierId;
    private int status;

    public Goods() {
    }

    public Goods(Integer id, String name, Integer unitId, Integer classId, double price, Integer supplierId, int status) {
        this.id = id;
        this.name = name;
        this.unitId = unitId;
        this.classId = classId;
        this.price = price;
        this.supplierId = supplierId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
