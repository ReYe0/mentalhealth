package com.study.front.dao;

import com.study.front.controller.dto.TopicReplyResponse;
import com.study.front.entities.TopicReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/6 20:59
 */
@Mapper
public interface TopicReplyDao {
    //话题回复列表
    public List<TopicReplyResponse> findAllReply(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //新增话题回复
    public Integer insertReply(TopicReply topicReply);
}
