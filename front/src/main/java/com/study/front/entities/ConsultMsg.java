package com.study.front.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/21 22:27
 */
@Data
public class ConsultMsg {
    private Integer id;
    private Integer userId;
    private Integer staffId;
    private String msg;
    private String name;
    private String type;
    private Date createTime;
}
