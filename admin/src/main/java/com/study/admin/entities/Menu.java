package com.study.admin.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 22:15
 */
@Data
public class Menu {
    private Integer id;
    private String name;
    private String path;
    private String icon;
    private String description;
    private Date createTime;
    private Integer pid;
    private List<Menu> children;
    private String pagePath;
}
