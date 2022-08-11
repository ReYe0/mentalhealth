package com.study.admin.entities;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/22 23:21
 */
@Data
public class Consult {
    private Integer id;
    private Integer userId;
    private String dateValue;
    private String timeValue;
    private String orderType;
    private String isEnable;
    private String sex;
    private String phone;
    private String name;
    private String record;
    private String label;
    private String value;
}
