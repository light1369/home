package com.example.demo.services;

import com.example.demo.domain.Unit;
import com.example.demo.domain.User;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 10:32
 */
public interface UnitServiceDao {
    //@Insert("")
    int insert(Unit unit);

    //@Select("select * from")
    List<User> selectAll();

    List<User> selectId(int id);

    List<User> selectPage(int page,int num);

    int updateId(Unit unit);

    int updateStatus(Unit unit);



    //查询名字是否存在
    int selectName(Unit unit);
}
