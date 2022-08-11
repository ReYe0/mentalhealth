package com.study.front.entities;

import lombok.Data;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/28 9:49
 */
@Data
public class BookOrderSon {
    private String orderId;
    private String bookNum;
    private Integer bookId;
    private String bookName;
    private String discountPrice;

    private List<BookImg> children;
}
