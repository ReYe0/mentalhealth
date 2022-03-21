package com.study.admin.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/15 23:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private Integer userId;
    private Integer classId;
    private String title;
    private String content;
    private Integer fileId;
    private String isShare;
//    private String articleLike;
    private String isLook;
    private Date createTime;
}
