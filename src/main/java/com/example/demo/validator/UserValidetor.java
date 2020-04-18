package com.example.demo.validator;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Duan
 * @date 2020/4/17 - 14:23
 */
@Component
public class UserValidetor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","606","没有用户名");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","607","没有密码");
    }
}
