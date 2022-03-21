package com.study.admin.dao;

import com.study.admin.entities.Dict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 14:29
 */
@Mapper
public interface DictDao {
//根据类型查找
    public List<Dict> findByType(String type);
}
