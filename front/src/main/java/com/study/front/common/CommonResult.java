package com.study.front.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回前端通用实体串
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

//    public CommonResult(Integer code,String message){
//        this(code,message,null);
//    }
    public static CommonResult success(){
        return new CommonResult(Constants.CODE_200, "", null);
    }
    public static CommonResult success(Object data){
        return new CommonResult(Constants.CODE_200,"" , data);
    }
    public static CommonResult error(String code,String msg){
        return new CommonResult(code, msg, null);
    }
    public static CommonResult error(){
        return new CommonResult(Constants.CODE_500, "系统错误", null);
    }
}
