package com.study.admin.dao;

import com.study.admin.controller.dto.admin.PsychologyListResponse;
import com.study.admin.entities.PsychologyOrderQuestion;
import com.study.admin.entities.PsychologyOrderQuestionSon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 19:28
 */
@Mapper
public interface PsychologyOrderDao {
    //获取有人测试过的测试
    public List<PsychologyOrderQuestion> getAlreadyChecked();
    //获取测试结果总数
    public Integer getCheckResultTotal(@Param("questionId") Integer questionId);
    //获取用户测试结果
    public List<PsychologyOrderQuestion> getCheckResult(@Param("questionId") Integer questionId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

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
    public List<PsychologyOrderQuestion> getPsychologyList(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("title") String title);
    //新增父问题时，同时新增子问题
    public Integer addOrderQuestionSon(List<PsychologyOrderQuestionSon> psychologyOrderQuestionSon);
    //新增父问题
    public Integer addOrderQuestion(PsychologyOrderQuestion psychologyOrderQuestion);
}
