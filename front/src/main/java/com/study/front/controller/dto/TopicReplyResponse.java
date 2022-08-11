package com.study.front.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/6 21:27
 */
@Data
public class TopicReplyResponse {
    private Integer id;
    private Integer userId;
    private Integer topicUserId;

    private String name;
    private String avatarUrl;
    private String content;
    private Date createTime;
    private Boolean replyType;
    private String staffName;
    private String adminUrl;
    private Integer praiseCount;
    private Integer isPraise;
    private Integer commentCount;
}
