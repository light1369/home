package com.example.demo.services;

import com.example.demo.domain.Stock;

/**
 * @author Duan
 * @date 2020/4/5 - 20:39
 */
public interface StockServiceDao {

    int selectStockId(Integer goodid);//通过id查询库存表是否存在此商品

    int insertStock(Integer goodid,double OrPrice,double Amount);//添加数据至仓库表

    int updateStock(double Amount,Integer goodid);//商品存在，修改库存

    double selectStockAmount(Integer goodId);//查询库存
}
