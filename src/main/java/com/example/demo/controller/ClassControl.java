package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Class;
import com.example.demo.domain.User;
import com.example.demo.result.Result;
import com.example.demo.services.ClassServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author Duan
 * @date 2020/3/29 - 16:30
 */

@RestController//控制层
@RequestMapping(value = "/class", produces = "application/json;charset=UTF-8")
public class ClassControl {
    @Autowired
    private ClassServiceDao classServiceDao;

    //restful风格
    @RequestMapping("/insert")
    public Result insert(@RequestBody Class classI) {

        if (classI.getName() == null || classI.getName().trim().isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        if (classServiceDao.selectName(classI) >= 1) {
            classI.setStatus(-99);
            if (classServiceDao.selectName(classI) >= 1) {//查询删除状态的同名用户
                classI.setStatus(1);
                classServiceDao.updateStatus(classI);//有则修改状态激活
                return Result.createSuccess().put("message", "状态激活");
            }
            return Result.createExistedError();//没有则返回已存在
        }//校验是否存在同名

        classServiceDao.insert(classI);
        return Result.createSuccess().put("id", classI.getId());
    }


    @RequestMapping("/listId/{id}")
    public List<Class> listId(@PathVariable int id) {

        return classServiceDao.selectId(id);
    }

    @RequestMapping("/updateId")
    public Result update(@RequestBody Class classI) {
        if (classI.getName() == null || classI.getName().trim().isEmpty()) {
            return Result.createFormatError();
        }//数据规范
        if (classServiceDao.selectName(classI) >= 1) {
            return Result.createExistedError();
        }//校验是否存在同名
        classServiceDao.updateId(classI);
        return Result.createSuccess();
    }

    @RequestMapping("/deleteId")
    public Result deleteId(@RequestBody JSONObject jsonObject) {
        if (!Result.verificationNumber(jsonObject.getString("id"))) {
            return Result.createFormatError();
        }//校验是否是数字类型
        Class classI = new Class();
        classI.setId(jsonObject.getInteger("id"));

        if (classI.getId() <= 0) {//是否大于0
            return Result.createFormatError();
        }
        classI.setStatus(-99);
        int res = classServiceDao.updateStatus(classI);
        if (res == 0) {
            return Result.createNotFoundError();
        }

        return Result.createSuccess().put("deleteId", "执行修改：" + res + "条");
    }
}
