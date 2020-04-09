package com.example.demo.domain;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Duan
 * @date 2020/4/4 - 10:58
 */
public class test {
    public static void main(String[] args) {

        Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
            String dateString = formatter.format(currentTime);
            System.out.println(dateString);
            
        
    }
}
