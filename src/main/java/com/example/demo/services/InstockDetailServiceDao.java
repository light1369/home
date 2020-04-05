package com.example.demo.services;

/**
 * @author Duan
 * @date 2020/4/5 - 19:45
 */
public interface InstockDetailServiceDao {
    int insertDetail(Integer goodid,double OrPrice,Integer Amount,Integer instockNweId);//添加入库明细

}
