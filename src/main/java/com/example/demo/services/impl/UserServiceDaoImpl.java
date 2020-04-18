package com.example.demo.services.impl;

import com.example.demo.domain.Class;
import com.example.demo.domain.User;
import com.example.demo.map.UserMap;
import com.example.demo.services.UserServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duan
 * @date 2020/3/24 - 9:29
 */
@Service
public class UserServiceDaoImpl implements UserServiceDao {

    private Logger logger=LoggerFactory.getLogger(UserServiceDaoImpl.class);



    @Autowired
    private UserMap userMap;

    @Override
    public List<User> logSelect(User user) {
        return userMap.logSelect(user);
    }

    @Override
    public int insert(User user) {
        return userMap.insert(user);
    }

    @Override
    public List<User> selectAll() {
        return userMap.selectAll();
    }



    @Override
    public List<User> selectId(int id) {

        logger.debug("接收id:{id}",id );
        return userMap.selectId(id);
    }

    @Override
    public List<User> selectPage(int page, int num) {
        return userMap.selectPage(page,num);
    }

    //修改数据
    @Override
    public int updateId(User user) {
        return userMap.updateId(user);
    }

    //修改状态
    @Override
    public int updateStatus(User user) {
        return userMap.updateStatus(user);


    }


    //查询名字是否存在
    @Override
    public int selectName(User user) {
        return userMap.selectName(user);
    }

    @Override
    public int addCashierMoney(double cashierMoney, Integer userId) {
        return userMap.addCashierMoney(cashierMoney,userId);
    }


}
