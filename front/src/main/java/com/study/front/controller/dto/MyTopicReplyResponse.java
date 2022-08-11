package com.study.front.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/11 22:33
 */
@Data
public class MyTopicReplyResponse {
    private Integer topicId;
    private String title;
    private String content;
    private Integer isLook;
    private Date createTime;
}
