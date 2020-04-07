package com.example.demo.map;

import org.springframework.stereotype.Repository;

/**
 * @author Duan
 * @date 2020/4/6 - 17:30
 */
@Repository
public interface SupplierMap {
    int selectSupplierId(Integer supplierId);  //通过id查询生产商
}
