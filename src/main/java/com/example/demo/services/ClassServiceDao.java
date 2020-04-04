package com.example.demo.services;

import com.example.demo.domain.Class;
import com.example.demo.domain.User;

import java.util.List;

/**
 * @author Duan
 * @date 2020/3/29 - 16:13
 */
public interface ClassServiceDao {
    //添加数据
    int insert(Class classI);

    //查找所有 @Select("select * from user")
    List<Class> selectAll();

    //通过id查找
    List<Class> selectId(int id);

    //分页查找
    List<Class> selectPage(int page,int num);

    //通过id修改信息
    int updateId(Class classI);

    //通过id删除信息
    int updateStatus(Class classI);


    //查询名字是否存在
    int selectName(Class name);
}
