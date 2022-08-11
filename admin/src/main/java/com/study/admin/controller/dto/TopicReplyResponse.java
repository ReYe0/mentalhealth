package com.study.admin.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/8 22:25
 */
@Data
public class TopicReplyResponse {
    private Integer id;
    private Integer userId;
    private Integer topicUserId;
    private Integer praiseCount;
    private Integer isPraise;
    private Integer commentCount;

    private String name;
    private String avatarUrl;
    private String content;
    private Date createTime;
    private Boolean replyType;
    private String staffName;
    private String adminUrl;
}
