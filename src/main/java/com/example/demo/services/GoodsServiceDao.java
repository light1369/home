package com.example.demo.services;
import com.example.demo.domain.Goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/2 - 14:55
 */
public interface GoodsServiceDao {
    int insert(Goods goods);

    //@Select("select * from")
    List<Goods> selectAll();

    List<Goods> selectId(Integer id);

    List<Map> selectPage(Map map);

    int updateId(Goods goods);

    int updateStatus(Goods goods);



    //查询名字是否存在
    int selectName(Goods goods);

    //校验商品表中是否存在此商品
    int selectGoodsId(Integer goodId);

    //查询供应商id
    int selectSupplierId(Integer goodId,Integer SupplierId);
}
