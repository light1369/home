package com.example.demo.services;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;

import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/5 - 16:14
 */
public interface InstockServiceDao {


    String initialization();//返回流水号


    int insertInstock(Instock instock);//添加入库信息

    //Integer selesctOrderNumberId(String orderNumber);//通过流水号查找相应id

    List<JSONObject> selectNewInstock(Integer instockNweId);

    int insertTotalMoney(Integer instockNweId,double totalPrice);//通过新id添加入库商品总金额






}
