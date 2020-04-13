package com.example.demo.services;

import com.example.demo.domain.Refunds;
import com.example.demo.domain.RefundsDetail;
import com.example.demo.domain.SalesDetail;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/10 - 16:13
 */
public interface RefundsService {
    String initialization();//返回退货单号

    int seleciOrderNumber(String orderNumber);//查询销售单的时效性

    double selecAmount(SalesDetail salesDetail);//校验商品id,和数量是否正确

    Integer insertRefunds(Refunds refunds);//添加退货投档

    //添加退货明细单，累加退货单总退回金额
    int insertRefundsDetail(Refunds refunds, RefundsDetail refundsDetail);

    int updateOutGood(Integer goodId,Integer salesId,double outGoods);//减少销售明细可退数量

    List<Refunds> selestRefunds(Integer refundsId); //查询退货投档

    List<RefundsDetail> selestRefundsDetail(Integer refundsId); //查询退货明细
}
