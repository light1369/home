package com.example.demo.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Goods;
import com.example.demo.domain.User;
import com.example.demo.map.GoodsMap;
import com.example.demo.services.GoodsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 14:58
 */
@Service
public class GoodsServiceDaoImpl implements GoodsServiceDao {
    @Autowired
    GoodsMap goodsMap;


    @Override
    public int insert(Goods goods) {
        return goodsMap.insert(goods);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMap.selectAll();
    }

    @Override
    public List<Goods> selectId(int id) {
        return goodsMap.selectId(id);
    }

    @Override
    public List<Goods> selectPage(int page, int num, String gName) {
        return goodsMap.selectPage(page,num,gName);
    }

    @Override
    public int updateId(Goods goods) {
        return goodsMap.updateId(goods);
    }

    @Override
    public int updateStatus(Goods goods) {
        return goodsMap.updateStatus(goods);
    }

    @Override
    public int selectName(Goods goods) {
        return goodsMap.selectName(goods);
    }

    @Override
    public int selectGoodsId(Integer goodId) {
        return goodsMap.selectGoodsId(goodId);
    }
}
