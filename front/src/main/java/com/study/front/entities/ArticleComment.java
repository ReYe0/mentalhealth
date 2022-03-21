package com.study.front.entities;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/19 12:06
 */
@Data
@ToString
public class ArticleComment {
    private Integer commentId;
    private Integer articleId;
    private String commentContent;
    private Integer userId;
    private Integer parentId;
    private String commentLike;
    private String name;
    private String avatarUrl;
    private Date createTime;
    private List<ArticleComment> child;

}
