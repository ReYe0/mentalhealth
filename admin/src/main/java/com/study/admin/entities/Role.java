package com.study.admin.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 21:20
 */
@Data
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String flag;//唯一标识
    private Date createTime;
}
