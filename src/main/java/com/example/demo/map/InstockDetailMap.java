package com.example.demo.map;

import com.example.demo.domain.InstockDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/5 - 19:43
 */
@Repository
public interface InstockDetailMap {
    int insertDetail(Integer goodid,double OrPrice,double Amount,Integer instockNweId);//添加入库明细

    List<InstockDetail> selectNewDetail(Integer instockNweId);//返回入库明细

    int selectInstock(Integer goodId);//通过goodID判断商品是否在入库单
}
