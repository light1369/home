package com.example.demo.services;

import com.example.demo.domain.Class;
import com.example.demo.domain.User;

import java.util.List;

/**
 * @author Duan
 * @date 2020/3/24 - 9:24
 */
public interface UserServiceDao {
    //@Insert("")
    int insert(User user);

    //@Select("select * from")
    List<User> selectAll();

    List<User> selectId(int id);

    List<User> selectPage(int page,int num);

    int updateId(User user);

    int updateStatus(User user);

    //查询名字是否存在
    int selectName(User user);

    //添加销售单此次消费总金额
    int addCashierMoney(double cashierMoney,Integer userId);
}
