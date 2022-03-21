package com.study.front.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/21 13:22
 */
@Data
public class ArticlePraise {
    private Integer id;
    private Integer articleId;
    private Integer userId;
//    private String isPraise;
    private Date createTime;
}
