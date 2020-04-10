package com.example.demo.map;


import com.example.demo.domain.SalesDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Duan
 * @date 2020/4/9 - 15:22
 */
@Repository
public interface SalesDetailMap {

    int InsertSalesDetail(SalesDetail salesDetail);

    List<SalesDetail> selectSalesDetail(Integer salesId);//查询销售明细
}
