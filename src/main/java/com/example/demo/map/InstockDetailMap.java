package com.example.demo.map;

import org.springframework.stereotype.Repository;

/**
 * @author Duan
 * @date 2020/4/5 - 19:43
 */
@Repository
public interface InstockDetailMap {
    int insertDetail(Integer goodid,double OrPrice,Integer Amount,Integer instockNweId);//添加入库明细
}
