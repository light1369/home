package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Unit;
import com.example.demo.domain.User;
import com.example.demo.result.Result;
import com.example.demo.services.UnitServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author Duan
 * @date 2020/4/2 - 10:48
 */

@RestController//控制层
@ResponseBody
@RequestMapping(value = "/unit", produces = "application/json;charset=UTF-8")//user根目录

public class UnitControl {
    @Autowired// 自动注入
    private UnitServiceDao unitServiceDao;//属于依赖


    @PostMapping("/insert")
    public Result insert(@RequestBody Unit unit) {

        if (unit.getName() == null || unit.getName().trim().isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        if (unitServiceDao.selectName(unit) >= 1) {
            unit.setStatus(-99);
            if (unitServiceDao.selectName(unit) >= 1) {//查询删除状态的同名用户
                unit.setStatus(1);
                unitServiceDao.updateStatus(unit);//有则修改状态激活
                return Result.createSuccess().put("message", "状态激活");
            }
            return Result.createExistedError();//没有则返回已存在

        }//校验是否存在同名
        unitServiceDao.insert(unit);
        return Result.createSuccess();
    }


    @RequestMapping("/list")
    public List<User> list() {
        System.out.println(unitServiceDao.selectAll());
        return unitServiceDao.selectAll();
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
        return Result.createSuccess().put("listId", unitServiceDao.selectId(id));
    }

    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody JSONObject jsonObject) {//用json接实体类以外的参数
        if (jsonObject.isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        int page = (int) jsonObject.get("page");
        int num = (int) jsonObject.get("num");
        if (page == 0 || num == 0) {
            return Result.createFormatError();
        }//数据规范
        return Result.createSuccess().put("page", unitServiceDao.selectPage((page - 1) * num, num));
    }


    @PostMapping("/updateId")
    public Result updateId(@RequestBody Unit unit) {
        if (unit.getName() == null || unit.getName().trim().isEmpty() || unit.getId() == 0) {
            return Result.createFormatError();
        }//数据规范

        int res=unitServiceDao.updateId(unit);
        if ( res== 0) {
            return Result.createNotFoundError();
        }
        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");
    }


    @PostMapping("/deleteId")
    public Result deleteId(@RequestBody JSONObject jsonObject) {


        if (!Result.verificationNumber(jsonObject.getString("id"))) {
            return Result.createFormatError();
        }//校验是否是数字类型


        Unit unit=new Unit();
        unit.setId(jsonObject.getInteger("id"));

        if (unit.getId() <= 0) {//是否大于0
            return Result.createFormatError();
        }
        unit.setStatus(-99);
        int res=unitServiceDao.updateStatus(unit);
        if ( res== 0) {
            return Result.createNotFoundError();
        }

        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");


    }
}
