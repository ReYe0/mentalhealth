package com.study.admin.dao;

import com.study.admin.controller.dto.TopicReplyResponse;
import com.study.admin.entities.TopicReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/8 22:24
 */
@Mapper
public interface TopicReplyDao {
    //话题回复列表
    public List<TopicReplyResponse> findAllReply(@Param("topicId") Integer topicId,@Param("userId") Integer userId);
    //新增话题回复
    public Integer insertReply(TopicReply topicReply);
}
