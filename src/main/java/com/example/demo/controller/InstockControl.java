package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;
import com.example.demo.map.GoodsMap;
import com.example.demo.map.StockMap;
import com.example.demo.result.Result;
import com.example.demo.services.GoodsServiceDao;
import com.example.demo.services.InstockDetailServiceDao;
import com.example.demo.services.InstockServiceDao;
import com.example.demo.services.StockServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Duan
 * @date 2020/4/5 - 17:49
 */
@RestController
@RequestMapping(value = "/shoping", produces = "application/json;charset=UTF-8")//user根目录
public class InstockControl {
    @Autowired
    InstockServiceDao instockServiceDao;
    @Autowired
    InstockDetailServiceDao instockDetailServiceDao;
    @Autowired
    GoodsServiceDao goodsServiceDao;
    @Autowired
    StockServiceDao stockServiceDao;


    @RequestMapping("/instock")
    public Result instock(@RequestBody HashMap hashMap) {
        //goodslist参数
        Integer goodid = null;
        double OrPrice = 0;
        Integer Amount = null;

        //添加入库明细参数
        int instockId = 0;
        String seInstockId = null;
        String inInstock_detail = null;

        //获取流水号
        String orderNumber=instockServiceDao.initialization();

        Integer SupplierId=(Integer)hashMap.get("Supplier_id");//得到供应商id
        //校验无效供应商
        if (instockServiceDao.selectSupplierId(SupplierId) == 0) {
            return Result.createNotFoundSuppllierError();
        }


        HashMap map = (HashMap) hashMap.get("Goods_list");//得到商品集合

        while (HashMap map:map){

        }
        goodid=(Integer)map.get("Goods_id");
        OrPrice=(double)map.get("Original_price");
        goodid=(Integer)map.get("Amount");




        //校验商品表中是否存在此商品
            if (goodsServiceDao.selectGoodsId(goodid) == 0) {
                return Result.createNotFoundGoodsError();
            }

        //校验仓库是否存在此商品
            int countt=stockServiceDao.selectStockId(goodid);
            if (countt == 0) {//不存在则添加
                stockServiceDao.insertStock(goodid,OrPrice,Amount);
            }
            if (countt == 1) {//存在则修改总数
                stockServiceDao.updateStock(Amount,goodid);
            }

//添加入库单
        instockServiceDao.insertInstock(SupplierId,orderNumber);


        Integer instockNweId=instockServiceDao.selesctOrderNumberId(orderNumber);//查找刚入库的入库单id
//添加入库明细
        for (Object m : hashMap) {
            instockDetailServiceDao.insertDetail(goodid,OrPrice,Amount,instockNweId);//添加入库明细

            }
//返回入库单
        List<Instock> list=instockServiceDao.selectNewInstock(instockNweId);
            for (Object l:list){

                l.put("供应商id",rs1.getInt("supplier_id"));
                j.put("入库时间",rs1.getDate("create_time"));
                Date d=rs1.getDate("dates");
                document=d.toString()+orderNumber;
                j.put("单据号",document);

            }
            rs2=sta.executeQuery(seinstockDetail);
            while (rs2.next()){
                JSONObject json=new JSONObject();
                json.put("商品id",rs2.getInt("goods_id"));
                totalAmount=rs2.getInt("total");
                json.put("总数",totalAmount);
                OrPrice =rs2.getDouble("price");
                json.put("价格",OrPrice);
                resultList.add(json);
                j.put("明细",json);
                totalPrice=totalPrice+(totalAmount*OrPrice);//总价

            }
            //resultList.add(totalPrice);
            j.put("总价",totalPrice);




        return Result.createSuccess();
    }


}
