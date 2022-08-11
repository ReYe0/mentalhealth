package com.study.front.entities;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/20 17:27
 */
@Data
public class ConsultOrder {
    private Integer id;//心理咨询师id
    private String staffName;
    private String avatarUrl;
    private String dateValue;
    private String timeValue;
    private String orderType;
    private String isEnable;
}
