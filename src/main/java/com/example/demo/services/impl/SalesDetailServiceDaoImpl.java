package com.example.demo.services.impl;

import com.example.demo.domain.SalesDetail;
import com.example.demo.map.SalesDetailMap;
import com.example.demo.services.SalesDetailServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/9 - 15:55
 */
@Service
public class SalesDetailServiceDaoImpl implements SalesDetailServiceDao {
    @Autowired
    SalesDetailMap salesDetailMap;

    @Override
    public int InsertSalesDetail(SalesDetail salesDetail) {
        return salesDetailMap.InsertSalesDetail(salesDetail);
    }

    @Override
    public List<SalesDetail> selectSalesDetail(Integer salesId) {
        return salesDetailMap.selectSalesDetail(salesId);
    }
}
