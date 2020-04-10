package com.example.demo.services.impl;

import com.example.demo.domain.Sales;
import com.example.demo.map.SalesMap;
import com.example.demo.services.SalesServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Duan
 * @date 2020/4/9 - 12:56
 */
@Service
public class SalesServiceDaoImpl implements SalesServiceDao {
    @Autowired
    SalesMap salesMap;


    @Override
    public String salesSingle() {

        String number = "0001";
        int num = 0;
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime)+"S";

        String oldnumber = salesMap.salesSingle();
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
    public int insertSales(Sales sales) {
        return salesMap.insertSales(sales);
    }

    @Override
    public int addCashierMoney(double cashierMoney, Integer salesId) {
        return salesMap.addCashierMoney(cashierMoney,salesId);
    }

    @Override
    public List<Sales> selectSales(Integer salesId) {
        return salesMap.selectSales(salesId);
    }
}
