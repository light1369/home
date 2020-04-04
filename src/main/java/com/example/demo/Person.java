package com.example.demo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Duan
 * @date 2020/4/2 - 15:54
 */


public class Person {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;


    @DecimalMin(value="0",message = "数据异常")
    Integer a;


}