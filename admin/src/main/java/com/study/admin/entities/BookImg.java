package com.study.admin.entities;

import lombok.Data;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/25 21:49
 */
@Data
public class BookImg {
    private Integer id;
    private Integer bookId;
    private Integer fileId;
    private String url;
}
