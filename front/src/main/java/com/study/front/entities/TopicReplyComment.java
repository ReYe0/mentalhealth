package com.study.front.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/7 14:13
 */
@Data
public class TopicReplyComment {
    private Integer id;
    private Integer topicReplyId;
    private String content;
    private Integer parentId;
    private Integer userId;
    private Boolean replyType;
    private Boolean topicReplyIsWho;
    private Date  createTime;

    private String name;
    private String avatarUrl;
    private String staffName;
    private String adminUrl;

    private List<TopicReplyComment> child;
}
