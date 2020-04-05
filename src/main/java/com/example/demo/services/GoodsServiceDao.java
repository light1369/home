package com.example.demo.services;

import com.example.demo.domain.Goods;
import com.example.demo.domain.User;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 14:55
 */
public interface GoodsServiceDao {
    int insert(Goods goods);

    //@Select("select * from")
    List<Goods> selectAll();

    List<Goods> selectId(int id);

    List<Goods> selectPage(int page,int num);

    int updateId(Goods goods);

    int updateStatus(Goods goods);



    //查询名字是否存在
    int selectName(Goods goods);

    //校验商品表中是否存在此商品
    int selectGoodsId(Integer goodId);
}
