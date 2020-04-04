package com.example.demo.domain;

/**
 * @author Duan
 * @date 2020/3/24 - 10:46
 */
public class User {
    private Integer id;
    private String name;
    private int status;
    private String password;

    public User() {
    }

    public User(Integer id, String name, int status, String password) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
