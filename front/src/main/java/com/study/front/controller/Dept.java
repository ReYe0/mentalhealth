package com.study.front.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/20 21:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    Integer id;
    Integer parentId;
    String content;
    List<Dept> child;
}
