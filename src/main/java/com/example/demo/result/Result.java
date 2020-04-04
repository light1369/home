package com.example.demo.result;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xfc
 * @version 1.0
 * @date 2019/12/25 16:28
 */
public class Result extends JSONObject {
    private static final Logger log = LogManager.getLogger(Result.class.getName());
    //

    //
    public static Result EXISTED_ERROR =createExistedError();
    public static Result PARAMETER_ERROR = createParameterError();
    public static Result PARAMETER_GOODS_ID_ERROR =creareParameterGoodsId();
    public static Result FORMAT_ERROR =createFormatError();
    public static Result SQLException =createSQLException();
    public static Result NOTFOUND_ERROR = createNotFoundError();
    public static Result NOTFOUND_KEY_ERROR =createNotFoundKeyError();
    public static Result NOTFOUND_GOODS_ERROR =createNotFoundGoodsError();
    public static Result NOTFOUND_SUPPLIER_ERROR =createNotFoundSuppllierError();
    public static Result INSERT_ERROR =createInsertError();
    public static Result ADD_ERROR =createAddError();
    public static Result UNKNOWN_ERROR =createUnknowError();
    public static Result DELETE_STATUS_ERROR=createDeleteStatusError();
    public static Result UPDATE_ERROR=createUpdateError();

    //
    public static Result createSuccess() {return new Result(0, "success");}
    public static Result createExistedError(){ return new Result(1, "已经存在！");}
    public static Result createParameterError() { return new Result(2, "参数错误"); }
    public static Result createFormatError(){return new Result(3, "数据格式错误！");}
    public static Result createSQLException(){return new Result(4, "sql语句异常！");}
    public static Result createNotFoundError() { return new Result(5, "没有数据");}
    public static Result createNotFoundKeyError(){return new Result(51, "接口错误！");}
    public static Result createNotFoundGoodsError(){return new Result(52, "商品信息为空！");}
    public static Result creareParameterGoodsId(){return new Result(21, "商品id参数错误！");}
    public static Result createNotFoundSuppllierError(){return new Result(53, "无效供应商！");}
    public static Result createInsertError(){return  new Result(6, "插入失败！");}
    public static Result createAddError(){return new Result(7, "添加失败！");}
    public static Result createUnknowError(){return new Result(999, "未知错误！");}
    public static Result createDeleteStatusError(){return new Result(8,"删除失败");}
    public static Result createUpdateError(){return new Result(9,"修改失败！");}
    //
    public Result(Integer code, String message) {
        put("code", code);
        put("message", message);
    }

    //
    public int getCode() {
        return getInteger("code");
    }

    public String getMessage() {
        return getString("message");
    }

    public boolean isSuccess() {
        return 0 == getInteger("code");
    }

    public Result put(String name,Object value){
        super.put(name,value);
        return this;
    }


    public static boolean verificationNumber(String parameter){
        //使用正则表达式校验大于0的数
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher isNum = pattern.matcher(parameter);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
