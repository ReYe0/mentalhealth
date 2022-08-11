package com.study.front.entities;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/27 17:25
 */
@Data
public class UserCity {
    private Integer id;
    private Integer userId;
    private String receiver;
    private String receiverPhone;
    private String cityDetail;
    private String remark;
}
