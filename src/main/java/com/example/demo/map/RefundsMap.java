package com.example.demo.map;

import com.example.demo.domain.Refunds;
import com.example.demo.domain.RefundsDetail;
import com.example.demo.domain.SalesDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/10 - 16:12
 */
@Repository
public interface RefundsMap {

    String initialization();//返回退货单号

    Integer seleciOrderNumber(String orderNumber);//查询销售单的时效性

    Double selecAmount(SalesDetail salesDetail);//校验商品id,和数量是否正确,返回价格

    Integer insertRefunds(Refunds refunds);//添加退货投档

    int insertRefundsDetail(RefundsDetail refundsDetail);//添加退货明细单

    int updateOutGood(Integer goodId,Integer salesId,double outGoods);//减少销售明细可退数量

    int insertRefundsMoney(Integer refundsId,double totalMoney);//累加退货单金额

    List<Refunds> selestRefunds(Integer refundsId); //查询退货投档

    List<RefundsDetail> selestRefundsDetail(Integer refundsId); //查询退货明细
}
