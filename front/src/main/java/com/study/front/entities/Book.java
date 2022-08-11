package com.study.front.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/25 18:34
 */
@Data
public class Book {
    private Integer id;
    private String bookName;
    private String price;
    private String description;
    private String discountPrice;
    private String bookNum;
    private String isSale;
    private Date createTime;

    private List<BookImg> children;
    private String className;
    private String url;
}
