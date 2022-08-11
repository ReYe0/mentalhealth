package com.study.admin.dao;

import com.study.admin.controller.dto.admin.MyReplyResponse;
import com.study.admin.entities.ArticleClass;
import com.study.admin.entities.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/8 22:04
 */
@Mapper
public interface TopicDao {
    //更新公告
    public Integer updateNotice(@Param("content") String content);
    //获取公告内容
    public String getNotice();
    //分页列表总数
    public Integer collectListTotal(@Param("staffId") Integer staffId,@Param("title") String title);
    //心理咨询师获取自己回复问题的列表
    public List<MyReplyResponse> getCollectList(@Param("staffId") Integer staffId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("title") String title);

    //分页列表总数
    public Integer replyListTotal(@Param("staffId") Integer staffId,@Param("title") String title);
    //心理咨询师获取自己回复问题的列表
    public List<MyReplyResponse> getReplyList(@Param("staffId") Integer staffId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("title") String title);
    //浏览量加1
    public Integer updateLookNum(@Param("lookNum") Integer lookNum,@Param("topicId") Integer topicId);
    //获取浏览量
    public Integer loadTopicLookNum(@Param("topicId") Integer topicId);
    //获取登陆用户的提问数
    public Integer totalTopicNum(@Param("userId") Integer userId);
    //登陆用户的回答总数
    public Integer totalReplyNum(@Param("userId") Integer userId);
    //获取登陆用户的话题回复获赞总数
    public Integer totalTopicReplyPraiseNum(@Param("userId") Integer userId);
    //新增点赞
    public Integer toPraise(@Param("topicReplyId") Integer topicReplyId,@Param("userId") Integer userId);
    //删除点赞
    public Integer delPraise(@Param("topicReplyId") Integer topicReplyId,@Param("userId") Integer userId);
    //判断是否拥抱过
    public Integer judgeIsCollect(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //问题的抱抱数量
    public Integer collectNum(@Param("topicId") Integer topicId);
    //删除抱抱
    public Integer delCollect(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //新增收藏
    public Integer toCollect(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //判断是否拥抱过
    public Integer judgeIsHug(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //问题的抱抱数量
    public Integer hugNum(@Param("topicId") Integer topicId);
    //删除抱抱
    public Integer delHug(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //新增抱抱
    public Integer toHug(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //查找全部问题的全部标签
    public List<ArticleClass> findAllTopicClass();
    //搜索问题
    public List<Topic> findTopicByTitle(@Param("topicTitle") String topicTitle);
    //获取全部问题 回答数降序
    public List<Topic> findAllByReplyCount();
    //获取全部问题 时间降序
    public List<Topic> findAll();
    //获取话题标签
    public List<ArticleClass> findClassByTopicId(@Param("topicId") Integer topicId);
    //获取话题内容
    public Topic findTopicById(@Param("topicId") Integer topicId);
}
