package com.example.demo.services.impl;

import com.example.demo.domain.Stock;
import com.example.demo.map.StockMap;
import com.example.demo.services.StockServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Duan
 * @date 2020/4/5 - 20:40
 */
@Service
public class StockServiceDaoImpl implements StockServiceDao {
    @Autowired
    StockMap stockMap;

    @Override
    public int selectStockId(Integer goodid) {
        return stockMap.selectStockId(goodid);
    }

    @Override
    public int insertStock(Integer goodid,double OrPrice,double Amount) {
        return stockMap.insertStock(goodid,OrPrice,Amount);
    }

    @Override
    public int updateStock(double Amount, Integer goodid) {
        return stockMap.updateStock(Amount,goodid);
    }

    @Override
    public double selectStockAmount(Integer goodId) {
        return stockMap.selectStockAmount(goodId);
    }
}
