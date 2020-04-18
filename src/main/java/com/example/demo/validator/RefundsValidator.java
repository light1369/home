package com.example.demo.validator;

import com.example.demo.domain.RefundsDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;


/**
 * @author Duan
 * @date 2020/4/14 - 10:28
 */
@Component
public class RefundsValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void validate(Object o, Errors errors) {
        if(o instanceof Map){
            Map<?,?> dd = (Map<?,?>)o;

            if(dd.containsKey("orderNumber")) {
                errors.reject("有销售单号1");
            }
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"orderNumber","506","没有销售单号");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userId","506","没有用户id");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"outlist","506","没有商品信息");




    }
}
