package com.example.demo.services;

import com.example.demo.domain.Instock;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/5 - 16:14
 */
public interface InstockServiceDao {


    String initialization();//返回流水号

    int selectSupplierId(Integer supplierId);  //通过id查询生产商

    int insertInstock(Integer supplierId,String orderNumber);//添加入库信息

    Integer selesctOrderNumberId(String orderNumber);//通过流水号查找相应id

    List<Instock> selectNewInstock(Integer instockNweId);


}
