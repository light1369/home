package com.example.demo.controller;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Sales;
import com.example.demo.domain.SalesDetail;
import com.example.demo.map.UserMap;
import com.example.demo.util.Result;
import com.example.demo.services.GoodsServiceDao;
import com.example.demo.services.SalesDetailServiceDao;
import com.example.demo.services.SalesServiceDao;
import com.example.demo.services.StockServiceDao;
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
 * @date 2020/4/9 - 13:35
 */
@RestController
@RequestMapping(value = "/sales", produces = "application/json;charset=UTF-8")


public class SalesControl {
    Logger logger= LoggerFactory.getLogger(SalesControl.class);

    @Autowired
    SalesServiceDao salesServiceDao;
    @Autowired
    GoodsServiceDao goodsServiceDao;
    @Autowired
    SalesDetailServiceDao salesDetailServiceDao;
    @Autowired
    StockServiceDao stockServiceDao;
    @Autowired
    UserMap userMap;

    @RequestMapping("/sold")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result sales(@RequestBody HashMap hashMap) {


        List<Map<String, Object>> goodsList = null;
        Integer goodId = null;
        double amount = 0.00;
        double price = 0.00;
        double subtotal = 0.00;
        double cashierMoney = 0.00;


        //校验hashmap有无数据
        if (hashMap.isEmpty()) {
            return Result.createNotFoundError();
        }

//获取新的销售单号
        String salesSingle = salesServiceDao.salesSingle();


 //获取userid
        Integer userId = (Integer) hashMap.get("userId");
        if (userId == null) {
            return Result.createNotFoundError().put("pay", "请输入userId");
        }

        double pay = (double) hashMap.get("pay");//得到实付金额
        if (pay <= 0.00) {
            return Result.createNotFoundError().put("pay", "请输入实付金额");
        }
//生成销售单
        Sales sales = new Sales();
        sales.setUserId(userId);
        sales.setSingleNumber(salesSingle);
        salesServiceDao.insertSales(sales);
        //得到刚插入的信息的id
        Integer salesId = sales.getId();


        goodsList = (List) hashMap.get("Goods_list");//得到集合
//添加明细表信息
        if (goodsList instanceof List && goodsList != null) {

            for (Map<String, Object> map : goodsList) {
                goodId = (Integer) map.get("goodId");
                amount = (double) map.get("amount");//小计


                //if(true){return Result.createSuccess().put("ceshi",amount);}
                List<Goods> goods = goodsServiceDao.selectId(goodId);
                //校验商品表中是否存在此商品
                if (goods == null) {
                    return Result.createNotFoundGoodsError().put("goodId", goodId);
                }
                for (Goods l : goods) {
                    price = l.getPrice();
                    subtotal = price * amount;
                    cashierMoney = subtotal + cashierMoney;//此次的总营业额

                    //作为参数放入实体类
                    SalesDetail salesDetail = new SalesDetail();
                    salesDetail.setGoodId(goodId);
                    salesDetail.setAmount(amount);
                    salesDetail.setSalesId(salesId);
                    salesDetail.setPrice(price);
                    salesDetail.setSubtotal(subtotal);

                    //添加明细动作
                    salesDetailServiceDao.InsertSalesDetail(salesDetail);
                }

                //减少库存
                if (stockServiceDao.selectStockAmount(goodId) < amount) {//库存数量校验
                    return Result.creareParameterGoodsId().put("库存剩余：", stockServiceDao.selectStockAmount(goodId));
                }

                //判断实付金额是否大于消费金额
                if (pay < cashierMoney) {
                    return Result.createParameterError().put("message", "实付金额不足");
                }

                if (stockServiceDao.updateStock(-amount, goodId) <= 0) {
                    return Result.createUpdateError().put("message", "库存减少失败");
                }

            }

//销售单总消费
            //if(true){return Result.createSuccess().put("ceshi",cashierMoney);}
            if (salesServiceDao.addCashierMoney(cashierMoney, salesId) <= 0) {
                return Result.createUpdateError().put("message", cashierMoney+"销售单总销售额添加失败"+salesId);
            }



// 添加总营业额

            if (userMap.addCashierMoney(cashierMoney, userId) <= 0) {
                return Result.createUpdateError().put("message", cashierMoney+"销售员总销售额添加失败"+userId);
            }
        }


//返回数据
            return Result.createSuccess().put("头档", salesServiceDao.selectSales(salesId))
                    .put("明细档", salesDetailServiceDao.selectSalesDetail(salesId)).put("找零", pay - cashierMoney);
        }



}
