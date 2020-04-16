package com.example.demo.services.impl;

import com.example.demo.domain.Refunds;
import com.example.demo.domain.RefundsDetail;
import com.example.demo.domain.SalesDetail;
import com.example.demo.map.RefundsMap;
import com.example.demo.map.StockMap;
import com.example.demo.services.RefundsService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Duan
 * @date 2020/4/10 - 16:14
 */
@Service
public class RefundsServiceImpl implements RefundsService {
    @Autowired
    RefundsMap refundsMap;
    @Autowired
    StockMap stockMap;


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

    @Override
    public int seleciOrderNumber(String orderNumber) {
        return refundsMap.seleciOrderNumber(orderNumber);
    }

    @Override
    public double selecAmount(SalesDetail salesDetail) {
        Double amount=refundsMap.selecAmount(salesDetail);
        if(amount ==null){
            return 0;
        }
        return (double)amount;
    }

    @Override
    public Integer insertRefunds( Refunds refunds)  {
        Integer refundsId=null;
        try {
            refundsId=refundsMap.insertRefunds(refunds);
        if (refundsId <= 0) {
            throw new SQLException();
        }
    }catch (SQLException e){
       e.printStackTrace();
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
        Result.createAddError().put("message", "添加退货投档失败");

    }
        return refundsId;
    }

    @Override
    public int insertRefundsDetail(Refunds refunds, RefundsDetail refundsDetail) {
        int num=0;
        if(refundsMap.insertRefundsDetail(refundsDetail)<=0){

        };

        //添加库存
        stockMap.updateStock(refundsDetail.getAmount(),refundsDetail.getGoodId());
        //累加退货投档金额
        num=refundsMap.insertRefundsMoney(refunds.getId(), refundsDetail.getAmount()*refundsDetail.getCurrentPrice());


        return num;
    }

    @Override
    public int updateOutGood(Integer goodId, Integer salesId,double outGoods) {
        return refundsMap.updateOutGood(goodId,salesId,outGoods);
    }

    @Override
    public List<Refunds> selestRefunds(Integer refundsId) {
        return refundsMap.selestRefunds(refundsId);
    }

    @Override
    public List<RefundsDetail> selestRefundsDetail(Integer refundsId) {
        return refundsMap.selestRefundsDetail(refundsId);
    }
}
