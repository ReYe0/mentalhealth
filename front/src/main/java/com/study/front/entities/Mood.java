package com.study.front.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/15 12:02
 */
@Data
public class Mood {
    private Integer id;
    private Integer userId;
    private String mood;
    private Date createTime;
}
