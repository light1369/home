package com.example.demo.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;
import com.example.demo.map.InstockMap;
import com.example.demo.services.InstockServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        String number = "0001";
        int num = 0;
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime)+"I";

        String oldnumber = instockMap.initialization();
        if (oldnumber != null) {

            //类型转换再加一
            //将得到的入库单取最后的流水号
            num = Integer.parseInt(oldnumber.substring(oldnumber.length() - 4)) + 1;
            String number1 = "000" + Integer.toString(num);//转为String类型
            number = number1.substring(number1.length() - 4);//取后三位
            return dateString+number;

        }
        return dateString+number;
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

    @Override
    public int insertTotalMoney(Integer instockNweId, double totalPrice) {
        return instockMap.insertTotalMoney(instockNweId,totalPrice);
    }


}
