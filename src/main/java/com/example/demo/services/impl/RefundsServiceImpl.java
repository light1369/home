package com.example.demo.services.impl;

import com.example.demo.map.RefundsMap;
import com.example.demo.services.RefundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Duan
 * @date 2020/4/10 - 16:14
 */
@Service
public class RefundsServiceImpl implements RefundsService {
    @Autowired
    RefundsMap refundsMap;

    @Override
    public String initialization() {
        String number = "0001";
        int num = 0;
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime)+"R";

        String oldnumber = refundsMap.initialization();
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
}
