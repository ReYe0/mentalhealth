package com.study.admin.controller.dto;

import cn.hutool.core.annotation.Alias;
import com.study.admin.entities.Menu;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 22:44
 */
@Data
public class StaffRequest {
    private Integer id;
    private String staffName;
    private String account;
    private String password;
    private String avatarUrl;
    private Boolean sex;
    private String phone;
    private String email;
    private String certificate;
    private String description;
    private String traffic;
    private String role;
    private Date createTime;
    private String token;
    private List<Menu> menus;
}
