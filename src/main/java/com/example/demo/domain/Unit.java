package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/3/27 - 11:18
 */
public class Unit {
    private Integer id;
    private String name;
    private int status;

    public Unit() {
    }

    public Unit(Integer id, String name, int status) {
        this.id = id;
        this.name = name;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
