package com.example.demo.controller;
import com.example.demo.domain.OutStock;
import com.example.demo.domain.OutStockDetail;
import com.example.demo.util.Result;
import com.example.demo.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/10 - 13:39
 */
@RestController
@RequestMapping(value = "/outStock", produces = "application/json;charset=UTF-8")
public class OutStockControl {
    Logger logger= LoggerFactory.getLogger(SalesControl.class);

    @Autowired
    OutStockService outStockService;
    @Autowired
    SupplierServiceDao supplierServiceDao;
    @Autowired
    InstockDetailServiceDao instockDetailServiceDao;
    @Autowired
    StockServiceDao stockServiceDao;
    @Autowired
    GoodsServiceDao goodsServiceDao;
    @Autowired
    UserServiceDao userServiceDao;



    @PostMapping("/outStocks")
    public Result refunds(@RequestBody HashMap hashMap){
        Integer goodId=null;
        double amount=0.00;

//得到退库单号
        String orderNumber = outStockService.initialization();



        //得到前台数据
        Integer SupplierId=(Integer) hashMap.get("SupplierId");
        if (SupplierId == null) {
            return Result.createNotFoundError().put("message", "请输入SupplierId");
        }
        //校验无效供应商
        if (supplierServiceDao.selectSupplier(SupplierId) == 0) {
            return Result.createNotFoundSuppllierError();
        }
        Integer userId=(int)hashMap.get("userId");
        if(userServiceDao.selectId(userId)==null){
            return Result.createNotFoundError().put("message", "请输入正确userID");
        }

        List<Map<String,Object>> outStockList=(List) hashMap.get("outStockList");
        if(outStockList instanceof List){
        for(Map<String,Object> l: outStockList){
            goodId=(Integer) l.get("goodsid");
            amount=(double)l.get("amount");

            if (goodId == null ||  amount == 0.0) {
                return Result.createNotFoundGoodsError();
            }
            //判断goodid是不是供应商供的货
            if(goodsServiceDao.selectSupplierId(goodId,SupplierId)<=0){
                return Result.createParameterError().put("message","商品id与供应商不匹配");
            }

            //判断商品是否在入库单
            if (instockDetailServiceDao.selectInstock(goodId)<=0){
                return Result.createParameterError().put("message","此商品未入过库");
            }



            //得到入库价格
            if(stockServiceDao.selectPrice(goodId)<=0){
                return Result.createNotFoundError().put("price价格id",goodId);
            }


            //校验商品表中是否存在此商品
            if (goodsServiceDao.selectGoodsId(goodId) == 0) {
                return Result.createNotFoundGoodsError().put("goodId", goodId);
            }

            //校验仓库库存
            double countt = stockServiceDao.selectStockAmount(goodId);
            if (countt < amount) {//不足则推出
                return Result.createParameterError().put("message","库存数量不足：");
            }

        }
        }else {
            return Result.createFormatError();
        }

//添加退库单
        OutStock outStock = new OutStock();
        outStock.setOrderNumber(orderNumber);
        outStock.setSupplierId(SupplierId);
        outStock.setUserId(userId);

        if(outStockService.insertOutStock(outStock)==0){
            return Result.createAddError().put("message","退库单添加异常");
        };
//查找刚退库的入库单id
        Integer outStockNweId = outStock.getId();//得到添加入库的id

//添加退库明细
        for(Map<String,Object> l: outStockList) {
            goodId=(Integer) l.get("goodsid");
            amount=(double)l.get("amount");
//if(true){return  Result.createSuccess().put("goodId",outStockList);}
            OutStockDetail outStockDetail = new OutStockDetail();
            outStockDetail.setGoodsId(goodId);
            outStockDetail.setAmount(amount);
            outStockDetail.setOutStockId(outStockNweId);

      outStockService.insertOutStockDetail(outStock, outStockDetail);

//                return Result.createAddError().put("message", "退库明细添加异常");
//            }

        }

//减少库存
        if(stockServiceDao.updateStock(-amount,goodId)<=0){
            return Result.createUpdateError().put("message","修改库存异常");
        }

//返回成功给前台

        return Result.createSuccess().put("退库投档",outStockService.selectNewOutStock(outStockNweId)).put("退库明细档",outStockService.selectNewDetail(outStockNweId));
    }

}
