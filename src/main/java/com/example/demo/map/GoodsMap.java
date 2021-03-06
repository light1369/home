package com.example.demo.map;
import com.example.demo.domain.Goods;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/2 - 14:46
 */

@Repository
public interface GoodsMap {
    //添加数据
    int insert(Goods goods);

    //查找所有 @Select("select * from user")
    List<Goods> selectAll();

    //通过id查找
    List<Goods> selectId(Integer id);

    //分页查找
    List<Map> selectPage(Map map);

    //通过id修改信息
    int updateId(Goods goods);

    //修改状态
    int updateStatus(Goods goods);



    //查询名字是否存在
    int selectName(Goods goods);

    //校验商品表中是否存在此商品
    int selectGoodsId(Integer goodId);

    //查询供应商id
    int selectSupplierId(Integer goodId,Integer SupplierId);
}
