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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        if (binder.getTarget() == null) {
            return;
        }
        binder.addValidators(refundsValidator);

    }


    @RequestMapping("/outgood")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result outGoods(@RequestBody Map map) throws Exception {

        Integer refundsId = null;


        String orderNumber = (String) map.get("orderNumber");//销售单号
            if (orderNumber==null || orderNumber=="") {
//                throw new Exception("test exception!");
                    return Result.createNotFoundError().put("message","没有商品销售单");
            }


        //校验销售单是否存在以及时效
        Integer salesId = refundsService.seleciOrderNumber(orderNumber);
        if (salesId == null) {

            return Result.createParameterError().put("message", "无效单号或商品已超出退货时间段");
        }

//执行退货操作
        refundsId=refundsService.insertRefundsDetail(map);

//得到添加的明细以及投档
        List<Refunds> Refundslist = refundsService.selestRefunds(refundsId);

        List<RefundsDetail> RefundsDetail = refundsService.selestRefundsDetail(refundsId);


        return Result.createSuccess().put("退货投档", Refundslist).put("退货明细", RefundsDetail);

    }


    //@RequestMapping("/outgood")
    public Refunds out(@RequestBody Refunds refunds, BindingResult result, HttpServletResponse response) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println(field + ":" + message);
            });

        }
        // ...


        List<RefundsDetail> refundsDetail = refunds.getRefundsDetail();
        for (RefundsDetail l : refundsDetail) {
            l.getGoodId();

        }

        return refunds;
    }


}
