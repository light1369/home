package com.example.demo.map;

import com.example.demo.domain.Goods;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<Goods> selectId(int id);

    //分页查找
    List<Goods> selectPage(int page,int num);

    //通过id修改信息
    int updateId(Goods goods);

    //修改状态
    int updateStatus(Goods goods);



    //查询名字是否存在
    int selectName(Goods goods);

    //校验商品表中是否存在此商品
    int selectGoodsId(Integer goodId);
}
