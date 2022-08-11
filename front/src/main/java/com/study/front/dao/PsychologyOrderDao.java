package com.study.front.dao;

import com.study.front.controller.dto.PsychologyCommentResponse;
import com.study.front.entities.Mood;
import com.study.front.entities.PsychologyOrderQuestion;
import com.study.front.entities.PsychologyOrderQuestionSon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 19:28
 */
@Mapper
public interface PsychologyOrderDao {


    //新增用户心情
    public Integer addMood(@Param("userId") Integer userId, @Param("today")String  today,@Param("radio") Integer radio);
    //获取用户的心情记录
    public List<Mood> getMyMood(@Param("userId") Integer userId);
    //用户的测试记录
    public List<PsychologyOrderQuestion> getCheckedList(@Param("userId") Integer userId);
    //加载评论
    public List<PsychologyCommentResponse> getComments(@Param("questionId") Integer questionId);
    //评论
    public Integer insertComment(@Param("questionId") Integer questionId,@Param("userId") Integer userId,@Param("content") String content);
    //新增测试历史记录
    public Integer addHistory(@Param("questionId") Integer questionId,@Param("userId") Integer userId,@Param("checkResult") String checkResult);
    //通过id查找问题
    public PsychologyOrderQuestion findById(@Param("id") Integer id);
    //删除子问题
    public Integer delQuestionSon(@Param("parentId") Integer parentId);
    //删除父问题
    public Integer delQuestion(@Param("id") Integer id);
    //更新父问题时，同时更新子问题
    public Integer updateOrderQuestionSon(@Param("psychologyOrderQuestionSon") List<PsychologyOrderQuestionSon> psychologyOrderQuestionSon);
    //更新父问题
    public Integer updateOrderQuestion(PsychologyOrderQuestion psychologyOrderQuestion);
    //心理测试列表数量
    public Integer getPsychologyTotalNum(@Param("title") String title);
    //心理测试子列表
    public List<PsychologyOrderQuestionSon> getPsychologySonList();
    //心理测试列表
    public List<PsychologyOrderQuestion> getPsychologyList(@Param("classId") Integer classId,@Param("title") String title);
    //新增父问题时，同时新增子问题
    public Integer addOrderQuestionSon(List<PsychologyOrderQuestionSon> psychologyOrderQuestionSon);
    //新增父问题
    public Integer addOrderQuestion(PsychologyOrderQuestion psychologyOrderQuestion);
}
