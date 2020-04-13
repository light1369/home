package com.example.demo.services.impl;

import com.example.demo.domain.OutStock;
import com.example.demo.domain.OutStockDetail;
import com.example.demo.map.InstockMap;
import com.example.demo.map.OutStockMap;
import com.example.demo.services.OutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/10 - 21:48
 */
@Service
public class OutStockServiceImpl implements OutStockService {
    @Autowired
    OutStockMap outStockMap;
    @Autowired
    InstockMap instockMap;

    @Override
    public String initialization() {
        String number = "0001";
        int num = 0;
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime)+"O";

        String oldnumber = outStockMap.initialization();
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
    public int insertOutStock(OutStock outStock) {
        return outStockMap.insertOutStock(outStock);
    }

//    @Override
//    public List<Map> selecAmountPrice(Integer goodId, Integer SupplierId, double amount) {



    //添加退库明细
        @Override
        public int insertOutStockDetail (OutStock outStock,OutStockDetail outStockDetail){
        int result=0;
        double OutAmount=outStockDetail.getAmount();
        double stockAmount=0.00;
        double price=0.00;
        double totalMoney=0.0;


            //查询入库单纪录
            List<Map<String,Object>> list = instockMap.selecAmountPrice(outStockDetail.getGoodsId(), outStock.getSupplierId());
            for (Map<String,Object> l : list) {
                stockAmount =((BigDecimal)l.get("amount")).doubleValue();
                price=((BigDecimal)l.get("price")).doubleValue();

                if (stockAmount < OutAmount) {//如果最早入库数量小于退货数量，将最早入库数量先退回，再减其次的
                    //价格为入库时的价格l.get("price")
                    result=outStockMap.insertOutStockDetail(outStockDetail.getGoodsId(),price,stockAmount,outStock.getId());
                    instockMap.updateStatus(((Integer)l.get("id")),stockAmount);
                    OutAmount = OutAmount - stockAmount;//剩余要退数量
                    outStock.setTotalMoney(outStock.getTotalMoney()+(price*stockAmount));//部分退库金额
                } else {
                    result=outStockMap.insertOutStockDetail(outStockDetail.getGoodsId(),price,OutAmount,outStock.getId());
                    instockMap.updateStatus(((Integer)l.get("id")),OutAmount);
                    outStock.setTotalMoney(outStock.getTotalMoney()+price*OutAmount);//部分退库金额
                    OutAmount = 0;
                    break;
                }


            }

            outStockMap.insertTotalMoney(outStock.getTotalMoney(),outStock.getId());

            return result;
        }

    @Override
    public List<Map> selectNewOutStock(Integer outStockNweId) {
        return outStockMap.selectNewOutStock(outStockNweId);
    }

    @Override
    public List<Map> selectNewDetail(Integer outStockNweId) {
        return outStockMap.selectNewDetail(outStockNweId);
    }

}
