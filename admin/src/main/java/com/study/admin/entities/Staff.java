package com.study.admin.entities;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.annotation.PropIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 23:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Staff {
    private Integer id;
    @Alias("员工姓名") // 文件导入需要的别名
    private String staffName;
    @Alias("员工账号")
    private String account;
    @Alias("员工密码")
    private String password;
    @Alias("头像地址")
    private String avatarUrl;
    @Alias("性别")
    private Boolean sex;
    @Alias("电话")
    private String phone;
    @Alias("邮箱")
    private String email;
    @Alias("证书等级")
    private String certificate;
    @Alias("描述")
    private String description;
    @Alias("访问量")
    private String traffic;
    @Alias("角色")
    private String role;
    @Alias("入职时间")
    private Date createTime;
    @Alias("咨询价格")
    private String consultPrice;
    @Alias("类别id")
    private Integer consultClassId;
    @Alias("所在地id")
    private Integer consultCityId;
    @Alias("擅长方向")
    private String goodAtDirection;
    @Alias("咨询对象")
    private String consultObject;
}
