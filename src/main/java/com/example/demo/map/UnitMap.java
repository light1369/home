package com.example.demo.map;

import com.example.demo.domain.Unit;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 10:30
 */
@Repository
public interface UnitMap {
    //添加数据
    int insert(Unit unit);

    //查找所有 @Select("select * from user")
    List<User> selectAll();

    //通过id查找
    List<User> selectId(int id);

    //分页查找
    List<User> selectPage(int page,int num);

    //通过id修改信息
    int updateId(Unit unit);

    //修改状态
    int updateStatus(Unit unit);



    //查询名字是否存在
    int selectName(Unit unit);
}
