package com.study.front.dao;

import com.study.front.entities.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/16 22:07
 */
@Mapper
public interface ArticleClassDao {
    //查找所有
    public List<Article> findAll();
}
