package com.example.demo.services;

import com.example.demo.domain.OutStock;
import com.example.demo.domain.OutStockDetail;

import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/10 - 21:47
 */
public interface OutStockService {
    String initialization(); //返回单号

    int insertOutStock(OutStock outStock);//添加退库单返回id


    int insertOutStockDetail(OutStock outStock,OutStockDetail outStockDetail);//添加出库明细

    List<Map> selectNewOutStock(Integer outStockNweId);//得到退库单

    List<Map> selectNewDetail(Integer outStockNweId);//得到退库单明细
}
