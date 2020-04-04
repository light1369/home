package com.example.demo.services.impl;

import com.example.demo.domain.Unit;
import com.example.demo.domain.User;
import com.example.demo.map.UnitMap;
import com.example.demo.services.UnitServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 10:35
 */
@Service
public class UnitServiceDaoImpl implements UnitServiceDao {

    @Autowired
    UnitMap unitMap;

    @Override
    public int insert(Unit unit) {
        return unitMap.insert(unit);
    }

    @Override
    public List<User> selectAll() {
        return unitMap.selectAll();
    }

    @Override
    public List<User> selectId(int id) {
        return unitMap.selectId(id);
    }

    @Override
    public List<User> selectPage(int page, int num) {
        return unitMap.selectPage(page,num);
    }

    @Override
    public int updateId(Unit unit) {
        return unitMap.updateId(unit);
    }

    @Override
    public int updateStatus(Unit unit) {
        return unitMap.updateStatus(unit);
    }

    @Override
    public int selectName(Unit unit) {
        return unitMap.selectName(unit);
    }
}
