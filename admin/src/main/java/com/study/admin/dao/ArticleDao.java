package com.study.admin.dao;

import com.study.admin.controller.dto.admin.ArticleResponse;
import com.study.admin.entities.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/21 19:11
 */
@Mapper
public interface ArticleDao {
    //通过id查找文章
    public Article findById(@Param("id") Integer id);
    //通过id删除文章
    public Integer delArticleById(@Param("id") Integer id);
    //更改文章审核
    public Integer updateShare(@Param("share") String share,@Param("id") Integer id);
    //查找文章列表
    public List<ArticleResponse> findArticleList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("title") String title);
    //    分页查询总条数
    public Integer selectTotal(String title);
}
