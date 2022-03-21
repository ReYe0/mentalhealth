package com.study.admin.controller.dto;

import lombok.Data;

/**
 * @Description: 接受前端请求的参数
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 13:43
 */
@Data
public class UserRequest {
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String avatarUrl;
    private String token;
}
