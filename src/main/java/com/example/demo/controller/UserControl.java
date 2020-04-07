package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.result.Result;
import com.example.demo.services.UserServiceDao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Duan
 * @date 2020/3/23 - 13:43
 */
@RestController//控制层
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
//@RequestMapping(value = "/aa", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//value = "/aa"-->根目录
//method = RequestMethod.POST-->只接收post
//produces = "application/json;charset=UTF-8"-->引用json格式

public class UserControl {


    @Autowired// 自动注入
    private UserServiceDao userServiceDao;//属于依赖


    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {

        if (user.getName() == null || user.getName().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        if (userServiceDao.selectName(user) >= 1) {
            user.setStatus(-99);
            if (userServiceDao.selectName(user) >= 1) {//查询删除状态的同名用户
                user.setStatus(1);
                userServiceDao.updateStatus(user);//有则修改状态激活
                return Result.createSuccess().put("message", "状态激活");
            }
            return Result.createExistedError();//没有则返回已存在

        }//校验是否存在同名
        userServiceDao.insert(user);
        return Result.createSuccess();
    }


    @RequestMapping("/list")
    public List<User> list() {
        System.out.println(userServiceDao.selectAll());
        return userServiceDao.selectAll();
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
        return Result.createSuccess().put("listId", userServiceDao.selectId(id));
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
        return Result.createSuccess().put("page", userServiceDao.selectPage((page - 1) * num, num));
    }


    @PostMapping("/updateId")
    public Result updateId(@RequestBody User user) {
        if (user.getName() == null || user.getName().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getId() == 0) {
            return Result.createFormatError();
        }//数据规范

        int res=userServiceDao.updateId(user);
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


        User user = new User();
        user.setId(jsonObject.getInteger("id"));

        if (user.getId() <= 0) {//是否大于0
            return Result.createFormatError();
        }
        user.setStatus(-99);
        int res=userServiceDao.updateStatus(user);
        if ( res== 0) {
            return Result.createNotFoundError();
        }

        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");


    }
}
