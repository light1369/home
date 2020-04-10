package com.example.demo.services;

import com.example.demo.domain.SalesDetail;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/9 - 15:54
 */
public interface SalesDetailServiceDao {
    int InsertSalesDetail(SalesDetail salesDetail);//添加明细

    List<SalesDetail> selectSalesDetail(Integer salesId);//查询销售明细
}
