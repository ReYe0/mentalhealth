package com.study.admin.dao;

import com.study.admin.entities.TopicReplyComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/8 22:27
 */
@Mapper
public interface TopicReplyCommentDao {
    public List<TopicReplyComment> findAllByTopicReplyId(@Param("topicReplyId") Integer topicReplyId);
    //插入评论
    public Integer insertReplyComment(TopicReplyComment topicReplyComment);
}
