package com.study.front.entities;

import lombok.Data;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/27 14:51
 */
@Data
public class BookOrder {
    private String id;
    private String bookNum;
    private String totalPrice;
    private String createTime;
    private String userId;
    private String receiver;
    private String receiverPhone;
    private String cityDetail;
    private String remark;

    private List<BookOrderSon> children;

}
