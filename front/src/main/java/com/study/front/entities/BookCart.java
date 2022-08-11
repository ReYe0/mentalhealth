package com.study.front.entities;

import lombok.Data;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/26 16:52
 */
@Data
public class BookCart {
    private Integer id;
    private Integer bookId;
    private String bookNum;
    private String bookName;
    private String discountPrice;
    private String price;

    private List<BookImg> children;
}
