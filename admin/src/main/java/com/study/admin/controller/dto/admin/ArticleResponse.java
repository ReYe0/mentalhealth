package com.study.admin.controller.dto.admin;

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
    private String name;
    private Integer userId;
    private Integer classId;
    private String title;
    private String content;
    private String url;
//    private String isLike;
    private String isShare;
    private Date createTime;
//    private List<ArticleComment> comments;


}
