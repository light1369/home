package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Goods;
import com.example.demo.result.Result;
import com.example.demo.services.GoodsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duan
 * @date 2020/4/2 - 15:11
 */

@RestController
@RequestMapping(value = "/goods", produces = "application/json;charset=UTF-8")//user根目录
public class GoodsControl {
    @Autowired// 自动注入
    private GoodsServiceDao goodsServiceDao;//属于依赖


    @PostMapping("/insert")
    public Result insert(@RequestBody Goods goods) {

        if (goods.getName() == null || goods.getName().trim().isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        if (goodsServiceDao.selectName(goods) >= 1) {
            goods.setStatus(-99);
            if (goodsServiceDao.selectName(goods) >= 1) {//查询删除状态的同名用户
                goods.setStatus(1);
                goodsServiceDao.updateStatus(goods);//有则修改状态激活
                return Result.createSuccess().put("message", "状态激活");
            }
            return Result.createExistedError();//没有则返回已存在

        }//校验是否存在同名
        goodsServiceDao.insert(goods);
        return Result.createSuccess();
    }


    @RequestMapping("/list")
    public List<Goods> list() {
        System.out.println(goodsServiceDao.selectAll());
        return goodsServiceDao.selectAll();
    }

//    @RequestMapping("/listId")
//    public List<User> listId(HttpServletRequest request) {
//        int id= Integer.parseInt(request.getParameter("id"));
//
//        return userServiceDao.selectId(id);


    //restful风格
    @RequestMapping("/listId/{id}")
    @ResponseBody
    public Result listId(@PathVariable int id) {
        if (id <= 0) {
            return Result.createFormatError();
        }//数据规范
        ;
        return Result.createSuccess().put("listId", goodsServiceDao.selectId(id));
    }

    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map map) {//用json接实体类以外的参数
        if (map.isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        //if(true){return Result.createSuccess().put("ceshi","sfd");}
        int page = (int)map.get("page");
        int num = (int)map.get("num");
        map.put("page",(page - 1) * num);

        if (page == 0 || num == 0 ) {
            return Result.createFormatError();
        }//数据规范

        return Result.createSuccess().put("page", goodsServiceDao.selectPage(map));//(page - 1) * num, num
    }


    @RequestMapping("/updateId")
    public Result updateId(@RequestBody Goods goods) {
        if (goods.getName() == null || goods.getName().trim().isEmpty() || goods.getId() == 0) {
            return Result.createFormatError();
        }//数据规范

        int res=goodsServiceDao.updateId(goods);
        if ( res== 0) {
            return Result.createNotFoundError();
        }
        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");
    }


    @RequestMapping("/deleteId")
    public Result deleteId(@RequestBody JSONObject jsonObject) {


        if (!Result.verificationNumber(jsonObject.getString("id"))) {
            return Result.createFormatError();
        }//校验是否是数字类型


        Goods goods=new Goods();
        goods.setId(jsonObject.getInteger("id"));

        if (goods.getId() <= 0) {//是否大于0
            return Result.createFormatError();
        }
        goods.setStatus(-99);
        int res=goodsServiceDao.updateStatus(goods);
        if ( res== 0) {
            return Result.createNotFoundError();
        }

        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");


    }
}
