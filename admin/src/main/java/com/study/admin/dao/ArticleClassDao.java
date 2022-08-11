package com.study.admin.dao;

import com.study.admin.entities.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/16 22:07
 */
@Mapper
public interface ArticleClassDao {
    //更新文章的类别
    public Integer updateClassById(@Param("articleId") Integer articleId,@Param("classId") Integer classId);
    //查找所有
    public List<Article> findAll();
}
