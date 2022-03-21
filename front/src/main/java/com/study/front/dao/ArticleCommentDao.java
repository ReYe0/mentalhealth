package com.study.front.dao;

import com.study.front.entities.Article;
import com.study.front.entities.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/19 14:01
 */
@Mapper
public interface ArticleCommentDao {
    //获取文章评论数量
    public Integer findCount(@Param("articleId") Integer articleId);
    //查找文章评论
    public List<ArticleComment> getComments(@Param("articleId") Integer articleId);
    //新增文章评论
    public Integer insertComment(ArticleComment articleComment);

}
