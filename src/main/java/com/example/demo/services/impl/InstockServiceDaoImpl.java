package com.example.demo.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;
import com.example.demo.map.InstockMap;
import com.example.demo.services.InstockServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Duan
 * @date 2020/4/5 - 16:15
 */

@Service
public class InstockServiceDaoImpl implements InstockServiceDao {

    @Autowired
    InstockMap instockMap;

  //返回流水号
    @Override
    public String initialization() {

        String number = "001";
        int num = 0;

        String oldnumber = instockMap.initialization();
        if (oldnumber != null) {

            //类型转换再加一
            num = Integer.parseInt(oldnumber) + 1;
            String number1 = "00" + Integer.toString(num);//转为String类型
            number = number1.substring(number1.length() - 3);//取后三位
            return number;

        }
        return number;
    }



    @Override
    public int insertInstock(Instock instock) {
        return instockMap.insertInstock(instock);
    }

//    @Override
//    public Integer selesctOrderNumberId(String orderNumber) {
//        return instockMap.selesctOrderNumberId(orderNumber);
//    }

    @Override
    public List<JSONObject> selectNewInstock(Integer instockNweId) {
        return instockMap.selectNewInstock(instockNweId);
    }


}
