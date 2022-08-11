package com.study.admin.dao;

import com.study.admin.controller.dto.admin.ArticleComment;
import com.study.admin.controller.dto.admin.ArticleResponse;
import com.study.admin.controller.dto.admin.MyArticleResponse;
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
    //查找某个管理员的文章
    public List<MyArticleResponse> findMyArticle(@Param("staffId") Integer staffId);
    //删除一级评论
    public Integer delFirstComments(@Param("commentId") Integer commentId);
    //删除二级评论
    public Integer delSecondComments(@Param("commentId") Integer commentId);
    //获取文章的子评论
    public List<ArticleComment> getComments(@Param("commentId") Integer commentId);
    //获取有评论的文章
    public List<ArticleComment> getAllArticle();
    //获取文章一级评论
    public List<ArticleComment> getFirstComments(@Param("articleId") Integer articleId);
    //更新文章内容
    public Integer updateArticle(Article article);
    //上传文章
    public Integer insertArticle(Article article);
    //通过id查找文章
    public Article findById(@Param("id") Integer id);
    //通过id删除文章
    public Integer delArticleById(@Param("id") Integer id);
    //更改文章审核
    public Integer updateShare(@Param("share") String share,@Param("id") Integer id);
    //查找文章列表通过角色
    public List<ArticleResponse> findArticleListByRole(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("title") String title,@Param("role") String role);
    //查找文章列表
    public List<ArticleResponse> findArticleList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("title") String title);

    //    分页查询总条数
    public Integer selectTotal(String title);
    //    分页查询总条数
    public Integer selectTotalByRole(@Param("title") String title,@Param("role") String role);
}
