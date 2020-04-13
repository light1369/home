package com.example.demo.services.impl;

import com.example.demo.map.SupplierMap;
import com.example.demo.services.SupplierServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Duan
 * @date 2020/4/6 - 17:32
 */
@Service
public class SupplierServiceDaoImpl implements SupplierServiceDao {
    @Autowired
    SupplierMap supplierMap;

    @Override
    public int selectSupplier(Integer supplierId) {
        return supplierMap.selectSupplier(supplierId);
    }
}
