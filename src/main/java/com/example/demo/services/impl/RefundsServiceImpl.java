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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    RefundsService refundsService;



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
    public Integer seleciOrderNumber(String orderNumber) {
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
    @Transactional()
    public Integer insertRefundsDetail(Map map) throws Exception {

        Integer goodId;
        double amount;
        double price;
        Integer refundsId;
        Integer refundsNewId;

        String outNumber = refundsService.initialization();//得到退货单号
        Integer userId = (Integer) map.get("userId");//用户id
        String orderNumber = (String) map.get("orderNumber");//销售单号

        //校验销售单是否存在以及时效
        Integer salesId = refundsService.seleciOrderNumber(orderNumber);


        //退货投档
        Refunds refunds = new Refunds();
        refunds.setUserId(userId);
        refunds.setOrderNumber(outNumber);
        refunds.setSalesNumber(orderNumber);

        refundsId = refundsService.insertRefunds(refunds);
        if(refundsId<=0){throw new RuntimeException("头档添加失败！!");}
        refundsNewId=refunds.getId();

        //if(true){throw new RuntimeException("refundsNewId 数据不正确!");}
        List<Map<String, Object>> outlist = (List) map.get("outlist");
        for (Map<String, Object> o : outlist) {
            goodId = (Integer) o.get("goodId");
            amount = (double) o.get("amount");

            SalesDetail salesDetail = new SalesDetail();//添加为对象查询
            salesDetail.setGoodId(goodId);
            salesDetail.setAmount(amount);
            salesDetail.setSalesId(salesId);

            price = refundsMap.selecAmount(salesDetail);
            if (price <= 0) {
                 throw new RuntimeException("test exception!");
            }

//添加退货明细单，减少销售单中可退回数量,累加投档总退回数
            RefundsDetail refundsDetail = new RefundsDetail();
            refundsDetail.setGoodId(goodId);
            refundsDetail.setAmount(amount);
            refundsDetail.setCurrentPrice(price);
            refundsDetail.setRefundsId(refunds.getId());



            //减少销售单可退数量
            refundsMap.updateOutGood(goodId, salesId, amount);


            if(refundsMap.insertRefundsDetail(refundsDetail)<=0){
                throw new RuntimeException("明细添加失败！!");
            };

            //添加库存
            stockMap.updateStock(refundsDetail.getAmount(),refundsDetail.getGoodId());
            //累加退货投档金额
            refundsMap.insertRefundsMoney(refundsNewId, refundsDetail.getAmount()*refundsDetail.getCurrentPrice());

        }





        return refundsNewId;
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
