package com.study.admin.service;

import com.study.admin.entities.Dict;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 14:56
 */
public interface DictService {
    //根据类型查找
    public List<Dict> findByType(String type);
}
