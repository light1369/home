package com.example.demo.validator;

import com.example.demo.domain.Refunds;
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



    public boolean supports(Class<?> aClass) {
        return RefundsDetail.class.isAssignableFrom(aClass);
    }



    public void validate(Object o, Errors errors) {


        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"salesNumber","500","没有销售单号");



    }
}
