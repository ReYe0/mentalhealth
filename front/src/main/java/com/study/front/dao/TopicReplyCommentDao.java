package com.study.front.dao;

import com.study.front.entities.TopicReplyComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/7 14:15
 */
@Mapper
public interface TopicReplyCommentDao {
    //查找评论
    public List<TopicReplyComment> findAllByTopicReplyId(@Param("topicReplyId") Integer topicReplyId);
    //插入评论
    public Integer insertReplyComment(TopicReplyComment topicReplyComment);
}
