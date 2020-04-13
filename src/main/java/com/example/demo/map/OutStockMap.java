package com.example.demo.map;

import com.example.demo.domain.OutStock;
import com.example.demo.domain.OutStockDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/10 - 21:47
 */
@Repository
public interface OutStockMap {
    String initialization(); //返回单号

     int insertOutStock(OutStock outStock);//添加退库单返回id

    int insertOutStockDetail(Integer goodId,double price,double amount,Integer outStockNweId);//添加出库明细

    int insertTotalMoney(double totalMoney,Integer outStockNweId);//添加退库总金额


    List<Map> selectNewOutStock(Integer outStockNweId);//得到退库单

    List<Map> selectNewDetail(Integer outStockNweId);//得到退库单明细

}
