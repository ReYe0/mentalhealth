package com.study.front.dao;

import com.study.front.controller.dto.ArticleResponse;
import com.study.front.entities.Article;
import com.study.front.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/15 23:05
 */
@Mapper
public interface ArticleDao {
    //取消关注
    public Integer quitConcern(@Param("userId") Integer userId,@Param("staffId") Integer staffId);
    //是否关注
    public Integer isConcern(@Param("userId") Integer userId,@Param("staffId") Integer staffId);
    //关注改变
    public Integer concernChange(@Param("userId") Integer userId,@Param("staffId") Integer staffId);
    //通过用户查找文章
    public List<ArticleResponse> findByUserId(@Param("userId") Integer userId);
    //获取用户拥有的文章数量
    public Integer articleNum(@Param("userId") Integer userId);
    //查找推荐文章
    public List<Article> findArticleByClass(@Param("articleId") Integer articleId,@Param("classId") Integer classId);
    //更新阅读量
    public Integer updateArticleById(@Param("id") Integer id,@Param("lookNum") Integer lookNum);
    //查找某一篇文章
    public ArticleResponse findById(@Param("id") Integer id);
    //查找所有文章通过分类
    public List<ArticleResponse> findAllByClassId(@Param("classId") Integer classId);
    //查找所有文章
    public List<ArticleResponse> findAllArticle();
    //上传文章
    public Integer insertArticle(Article article);
}
