package com.study.front.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/6 12:45
 */
@Data
public class Topic {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Boolean isAnonymous;
    private Date createTime;
    private Integer isLook;
    private Integer replyCount;
    private String name;
    private String avatarUrl;

    //其他需要的属性
    private List<ArticleClass> articleClass;
}
