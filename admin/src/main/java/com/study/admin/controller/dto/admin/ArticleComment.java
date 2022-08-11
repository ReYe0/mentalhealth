package com.study.admin.controller.dto.admin;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/31 20:21
 */
@Data
public class ArticleComment {
    private Integer commentId;
    private Integer articleId;
    private String title;
    private String commentContent;
    private Integer userId;
    private Integer parentId;
    private String commentLike;
    private String avatarUrl;
    private String name;
    private Date createTime;
}
