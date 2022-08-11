package com.study.admin.controller.dto.admin;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/12 15:47
 */
@Data
public class PsychologyListResponse {
    private Integer id;
    private String title;
    private Boolean isSale;
    private String isFree;
    private Integer price;
    private String description;
    private String staffName;
}
