package com.study.front.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/6 20:58
 */
@Data
public class TopicReply {
    private Integer id;
    private Integer topicId;
    private Integer userId;
    private String content;
    private Date createTime;
    private Boolean replyType;
}
