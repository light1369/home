package com.example.demo.services.impl;

import com.example.demo.domain.Class;
import com.example.demo.domain.User;
import com.example.demo.map.ClassMap;
import com.example.demo.services.ClassServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duan
 * @date 2020/3/29 - 16:21
 */
@Service
public class ClassServiceDaoImpl implements ClassServiceDao {

    @Autowired
    private ClassMap classMap;

    @Override
    public int insert(Class classI) {
        return classMap.insert(classI);
    }

    @Override
    public List<Class> selectAll() {
        return classMap.selectAll();
    }

    @Override
    public List<Class> selectId(int id) {
        return classMap.selectId(id);
    }

    @Override
    public List<Class> selectPage(int page, int num) {
        return classMap.selectPage((page-1)*num,num);
    }

    @Override
    public int updateId(Class classIn) {
        return classMap.updateId(classIn);
    }

    @Override
    public int updateStatus(Class classI) {
        return classMap.updateStatus(classI);
    }



    //查找名字是否存在
    @Override
    public int selectName(Class name) {
        return classMap.selectName(name);
    }
}
