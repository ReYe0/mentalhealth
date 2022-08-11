package com.study.front.dao;

import com.study.front.entities.PsychologyClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 16:37
 */
@Mapper
public interface PsychologyClassDao {

    //查找所有分类
    public List<PsychologyClass> findAll();
}
