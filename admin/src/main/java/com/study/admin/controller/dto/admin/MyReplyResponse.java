package com.study.admin.controller.dto.admin;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/11 16:36
 */
@Data
public class MyReplyResponse {
    private Integer topicId;
    private String title;
    private String content;
    private Integer commentCount;
    private Integer isLook;
}
