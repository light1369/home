package com.example.demo.services.impl;

import com.example.demo.domain.InstockDetail;
import com.example.demo.map.InstockDetailMap;
import com.example.demo.services.InstockDetailServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/5 - 19:45
 */
@Service
public class InstockDetailServiceDaoImpl implements InstockDetailServiceDao {
    @Autowired
    InstockDetailMap instockDetailMap;


    @Override
    public int insertDetail(Integer goodid, double OrPrice, double Amount, Integer instockNweId) {
        return instockDetailMap.insertDetail(goodid,OrPrice,Amount,instockNweId);
    }

    @Override
    public List<InstockDetail> selectNewDetail(Integer instockNweId) {
        return instockDetailMap.selectNewDetail(instockNweId);
    }

    @Override
    public int selectInstock(Integer goodId) {//通过goodID判断商品是否在入库单
        return instockDetailMap.selectInstock(goodId);
    }
}
