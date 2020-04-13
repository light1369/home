package com.example.demo.map;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Instock;
import com.example.demo.domain.OutStockDetail;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/5 - 14:33
 */
@Repository
public interface InstockMap {

    String initialization(); //返回单号

    int insertInstock(Instock instock);//添加入库信息

    //Integer selesctOrderNumberId(String orderNumber);//通过流水号查找相应id

    List<JSONObject> selectNewInstock(Integer instockNweId);


    int insertTotalMoney(Integer instockNweId,double totalPrice);//通过新id添加入库商品总金额



//传goodid,supplierid时间排序得到价格，数量
    List<Map<String,Object>> selecAmountPrice(Integer goodId, Integer SupplierId);

    int updateStatus(Integer instockDetailId);//退库后修改入库明细status



}
