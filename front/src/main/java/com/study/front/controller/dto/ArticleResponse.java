package com.study.front.controller.dto;

import com.study.front.entities.ArticleComment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/17 18:00
 */
@Data
public class ArticleResponse {
    private Integer id;//文章id
    private Integer userId;
    private Integer classId;
    private String title;
    private String content;
    private String url;
//    private String isLike;
    private String isLook;
    private Date createTime;
    private List<ArticleComment> comments;


}
