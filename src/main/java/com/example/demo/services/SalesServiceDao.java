package com.example.demo.services;

import com.example.demo.domain.Sales;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/9 - 12:56
 */
public interface SalesServiceDao {
    String salesSingle();

    int insertSales(Sales sales);//添加信息到销售单

    int addCashierMoney(double cashierMoney,Integer salesId);//添加销售单此次消费总金额

    List<Sales> selectSales(Integer salesId);//通过salesIdc查询销售单


}
