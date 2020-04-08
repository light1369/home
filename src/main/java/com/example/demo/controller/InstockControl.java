package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;
import com.example.demo.domain.InstockDetail;
import com.example.demo.result.Result;
import com.example.demo.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Duan
 * @date 2020/4/5 - 17:49
 */
@RestController
@RequestMapping(value = "/shoping", produces = "application/json;charset=UTF-8")//user根目录
public class InstockControl {
     Logger logger= LoggerFactory.getLogger(InstockControl.class);

    @Autowired
    InstockServiceDao instockServiceDao;
    @Autowired
    InstockDetailServiceDao instockDetailServiceDao;
    @Autowired
    GoodsServiceDao goodsServiceDao;
    @Autowired
    StockServiceDao stockServiceDao;
    @Autowired
    SupplierServiceDao supplierServiceDao;


    @RequestMapping("/instock")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result instock(@RequestBody HashMap hashMap) {
        //goodslist参数
        List<Map<String, Object>> goodsList = null;
        Integer goodid = null;
        double OrPrice = 0;
        double Amount = 0;
        double totalPrice = 0;

        //添加入库明细参数
        int instockId = 0;
        String seInstockId = null;
        String inInstock_detail = null;

        //校验hashmap有无数据
        if (hashMap.isEmpty()) {
            return Result.createNotFoundError();

        }

        //获取流水号
        String orderNumber = instockServiceDao.initialization();

        Integer SupplierId = (Integer) hashMap.get("Supplier_id");//得到供应商id

        if (SupplierId == null) {
            return Result.createNotFoundGoodsError().put("supplierId", SupplierId);
        }
//        if (!Result.verificationNumber((String) hashMap.get("Supplier_id"))) {
//            return Result.createFormatError().put("供应商id",(String) hashMap.get("Supplier_id"));
//        }


        //校验无效供应商
        if (supplierServiceDao.selectSupplierId(SupplierId) == 0) {
            return Result.createNotFoundSuppllierError();
        }


        if (hashMap.get("Goods_list") instanceof List) {//校验是否是list集合
            goodsList = (List) hashMap.get("Goods_list");//得到商品集合
            //遍历商品集合
            for (Map<String, Object> l : goodsList) {
                goodid = (Integer) l.get("Goods_id");
                OrPrice = (double) l.get("Original_price");
                Amount = (double) l.get("Amount");

                if (goodid == null || OrPrice == 0 || Amount == 0) {
                    return Result.createNotFoundGoodsError();
                }


                //校验商品表中是否存在此商品
                if (goodsServiceDao.selectGoodsId(goodid) == 0) {
                    return Result.createNotFoundGoodsError().put("goodId", goodid);
                }

                //校验仓库是否存在此商品
                int countt = stockServiceDao.selectStockId(goodid);
                if (countt == 0) {//不存在则添加
                    stockServiceDao.insertStock(goodid, OrPrice, Amount);
                }
                if (countt == 1) {//存在则修改总数
                    stockServiceDao.updateStock(Amount, goodid);
                }
            }

        } else {
            return Result.createFormatError();
        }


//添加入库单
        Instock instock = new Instock();
        instock.setOrderNumber(orderNumber);
        instock.setSupplierId(SupplierId);
        instockServiceDao.insertInstock(instock);

//查找刚入库的入库单id
        Integer instockNweId = instock.getId();//得到添加入库的id

//添加入库明细
        for (Map<String, Object> l : goodsList) {
            goodid = (Integer) l.get("Goods_id");
            OrPrice = (double) l.get("Original_price");
            Amount = (double) l.get("Amount");
            instockDetailServiceDao.insertDetail(goodid, OrPrice, Amount, instockNweId);//添加入库明细
        }

//返回入库单
        List<JSONObject> instockList = instockServiceDao.selectNewInstock(instockNweId);


//返回入库明细
        List<InstockDetail> instockDetailsList = instockDetailServiceDao.selectNewDetail(instockNweId);

        for (InstockDetail l : instockDetailsList) {
            Amount = l.getAmount();
            OrPrice = l.getOriginalPrice();

            totalPrice = totalPrice + (Amount * OrPrice);//总价

        }

        //为入库单添加总金额
        if (instockServiceDao.insertTotalMoney(instockNweId, totalPrice) < 1) {
            return Result.createAddError();
        }

        return Result.createSuccess().put("入库明细", instockDetailsList).put("入库单：", instockList).put("总价", totalPrice);
    }

//if(true){return Result.createSuccess().put("ceshi",instockDetailsList);}
}
