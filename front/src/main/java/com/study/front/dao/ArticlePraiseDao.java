package com.study.front.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/21 13:20
 */
@Mapper
public interface ArticlePraiseDao {
    //查找某个用户的点赞数
    public Integer findPraiseNumByUserId(@Param("userId") Integer userId);
    //用户取消点赞
    public Integer delPraise(@Param("userId") Integer userId,@Param("articleId") Integer articleId);
    //用户点赞
    public Integer toPraise(@Param("userId") Integer userId,@Param("articleId") Integer articleId);
    //当前用户是否点赞
    public Integer isPraiseByUserIdAndArticleId(@Param("userId") Integer userId,@Param("articleId") Integer articleId);
    //查找文章点赞数量
    public Integer findPraiseNum(@Param("articleId") Integer articleId);
}
