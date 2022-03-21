package com.study.front.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:28
 */
@Data //注解在类上, 为类提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@AllArgsConstructor // 注解作用在类上，使用后为类生成一个全参构造函数（含有已申明的所有属性参数）
@NoArgsConstructor  //注解作用在类上，使用后为类生成一个无参构造函数
public class User {
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String avatarUrl;
    private String info;
}
