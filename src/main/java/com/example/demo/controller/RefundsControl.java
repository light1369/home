package com.example.demo.controller;

import com.example.demo.domain.Refunds;
import com.example.demo.domain.RefundsDetail;
import com.example.demo.domain.SalesDetail;
import com.example.demo.services.RefundsService;
import com.example.demo.util.Result;
import com.example.demo.validator.RefundsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/13 - 14:00
 */
@RestController
@RequestMapping(value = "/outgoods", produces = "application/json;charset=UTF-8")
public class RefundsControl {
    Logger logger = LoggerFactory.getLogger(SalesControl.class);
    @Autowired
    RefundsService refundsService;
    @Autowired
    RefundsValidator refundsValidator;


    @InitBinder
    public void setupBinder(WebDataBinder binder) {
     if (binder.getTarget()==null){
         return;
     }

     if(refundsValidator.supports(binder.getTarget().getClass())){
         binder.addValidators(refundsValidator);

     }

    }


    @RequestMapping("/outgood")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result outGoods(@Valid @RequestBody Map map) throws Exception {
//
            Integer goodId = null;
            double amount = 0.00;
            double price = 0.00;
            Integer refundsId = null;

            //@NotBlank(message = "销售单不能为空")
            String orderNumber = (String) map.get("orderNumber");//销售单号
            if (orderNumber == null) {
                throw new Exception("test exception!");
                //return Result.createNotFoundError().put("message","没有："+orderNumber);
            }


            String outNumber = refundsService.initialization();//得到退货单号
            Integer userId = (Integer) map.get("userId");//用户id

            //校验销售单是否存在以及时效
            Integer salesId = refundsService.seleciOrderNumber(orderNumber);
            if (salesId <= 0) {

                return Result.createParameterError().put("message", "无效单号或商品已超出退货时间段");
            }

//退货投档
            Refunds refunds = new Refunds();
            refunds.setUserId(userId);
            refunds.setOrderNumber(outNumber);


            refundsId = refundsService.insertRefunds(refunds);



            List<Map<String, Object>> outlist = (List) map.get("outlist");
            for (Map<String, Object> o : outlist) {
                goodId = (Integer) o.get("goodId");
                amount = (double) o.get("amount");

                SalesDetail salesDetail = new SalesDetail();//添加为对象查询
                salesDetail.setGoodId(goodId);
                salesDetail.setAmount(amount);
                salesDetail.setSalesId(salesId);

                price = refundsService.selecAmount(salesDetail);
                if (price <= 0) {
                    return Result.createNotFoundError().put("message", "没有找到对应商品，请检查数量,id:" + goodId);
                }

//添加退货明细单，减少销售单中可退回数量,累加投档总退回数
                RefundsDetail refundsDetail = new RefundsDetail();
                refundsDetail.setGoodId(goodId);
                refundsDetail.setAmount(amount);
                refundsDetail.setCurrentPrice(price);
                refundsDetail.setRefundsId(refundsId);
                refundsDetail.setSalesNumber(orderNumber);

                refundsService.insertRefundsDetail(refunds, refundsDetail);

                //减少销售单可退数量
                refundsService.updateOutGood(goodId, salesId, amount);

            }

            List<Refunds> Refundslist = refundsService.selestRefunds(refundsId);

            List<RefundsDetail> RefundsDetail = refundsService.selestRefundsDetail(refundsId);


            return Result.createSuccess().put("退货投档", Refundslist).put("退货明细", RefundsDetail);
//        } catch (Exception e) {
//            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
//        }
//        return Result.createSuccess();
    }


}
