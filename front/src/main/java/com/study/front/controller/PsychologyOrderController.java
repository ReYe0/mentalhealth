package com.study.front.controller;

import com.study.front.common.CommonResult;
import com.study.front.dao.FilesDao;
import com.study.front.dao.PsychologyClassDao;
import com.study.front.dao.PsychologyOrderDao;
import com.study.front.dao.UserDao;
import com.study.front.entities.Mood;
import com.study.front.entities.PsychologyOrderQuestion;
import com.study.front.entities.PsychologyOrderQuestionSon;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 16:40
 */
@RestController
@RequestMapping(value = "/psychology")
public class PsychologyOrderController {
    @Resource
    private PsychologyClassDao psychologyClassDao;
    @Resource
    private PsychologyOrderDao psychologyOrderDao;
    @Resource
    private FilesDao filesDao;
    @Resource
    private UserDao userDao;

    //获取关注的咨询师列表
    @GetMapping(value = "/focusStaff/{userId}")
    public CommonResult getFocusStaff(@PathVariable Integer userId){
        return CommonResult.success(userDao.getFocusStaff(userId));
    }
    //新增心情
    @PostMapping(value = "/addMood")
    public CommonResult addMood(Integer userId, Integer radio, String today){
        return CommonResult.success(psychologyOrderDao.addMood(userId, today, radio));
    }
    //获取用户的心情记录
    @GetMapping(value = "/getMyMood")
    public CommonResult getMyMood(Integer userId){
        List<Mood> myMood = psychologyOrderDao.getMyMood(userId);
        return CommonResult.success(myMood);
    }
    //用户的测试记录
    @GetMapping(value = "/userCheckHistory")
    public CommonResult getCheckedList(Integer userId){
        return CommonResult.success(psychologyOrderDao.getCheckedList(userId));
    }
    //加载测试评论
    @GetMapping(value = "/getComments")
    public CommonResult getComments(Integer questionId){
        return CommonResult.success(psychologyOrderDao.getComments(questionId));
    }
    //评论测试
    @PostMapping(value = "/comment")
    public CommonResult comment(Integer questionId,Integer userId,String content){
        Integer integer = psychologyOrderDao.insertComment(questionId, userId, content);
        if(integer > 0){
            return CommonResult.success();
        }else {
            return CommonResult.error();
        }
    }
    //增加心理测试历史记录
    @PostMapping(value = "/addHistory")
    public CommonResult addHistory(Integer questionId,Integer userId,String checkResult){
        return CommonResult.success(psychologyOrderDao.addHistory(questionId, userId, checkResult));
    }
    //删除心理测试
    @DeleteMapping(value = "/delPsychology/{id}")
    public CommonResult delPsychology(@PathVariable Integer id){
        PsychologyOrderQuestion question = psychologyOrderDao.findById(id);
        Integer integer2 = filesDao.deleteById(question.getFileId());
        Integer integer = psychologyOrderDao.delQuestion(id);
        Integer integer1 = psychologyOrderDao.delQuestionSon(id);
        if (integer >0 && integer1 >0 && integer2 >0){

            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //心理测试更新
    @PostMapping(value = "/updatePsychology")
    @Transactional
    public CommonResult updatePsychology(@RequestBody PsychologyOrderQuestion psychologyOrderQuestion){
        Integer orderQuestion = psychologyOrderDao.updateOrderQuestion(psychologyOrderQuestion);
        Integer integer = psychologyOrderDao.updateOrderQuestionSon(psychologyOrderQuestion.getSonQuestion());
        if(orderQuestion >0 && integer >0){
            return CommonResult.success();
        }else {
            return CommonResult.error();
        }
    }
    //心理测试列表
    @GetMapping(value = "/getPsychologyList")
    public CommonResult getPsychologyList(@RequestParam(defaultValue = "1") Integer classId,@RequestParam(defaultValue = "") String title){
        List<PsychologyOrderQuestion> psychologyList = psychologyOrderDao.getPsychologyList(classId, title);
        List<PsychologyOrderQuestionSon> psychologySonList = psychologyOrderDao.getPsychologySonList();
        for (int i = 0; i < psychologyList.size(); i++) {
            for (int j = 0; j < psychologySonList.size(); j++) {
                List<PsychologyOrderQuestionSon> sonQuestion = psychologyList.get(i).getSonQuestion();
                if(sonQuestion == null){
                    sonQuestion = new ArrayList<PsychologyOrderQuestionSon>();
                }
                if(psychologySonList.get(j).getParentId() == psychologyList.get(i).getId()){
                    sonQuestion.add(psychologySonList.get(j));
                    psychologyList.get(i).setSonQuestion(sonQuestion);
                }
            }
        }
        Integer total = psychologyOrderDao.getPsychologyTotalNum(title);
        Map<String,Object> res = new HashMap<>();
        res.put("psychologyList",psychologyList);
        res.put("total",total);
        return CommonResult.success(res);
    }
    //新增心理测试
    @PostMapping(value = "/addOrder")
    @Transactional
    public CommonResult addOrder(@RequestBody PsychologyOrderQuestion psychologyOrderQuestion){
        Integer orderQuestion = psychologyOrderDao.addOrderQuestion(psychologyOrderQuestion);
        for (int i = 0; i < psychologyOrderQuestion.getSonQuestion().size(); i++) {
            psychologyOrderQuestion.getSonQuestion().get(i).setParentId(psychologyOrderQuestion.getId());
        }
        Integer integer = psychologyOrderDao.addOrderQuestionSon(psychologyOrderQuestion.getSonQuestion());
        if(orderQuestion >0 && integer >0){
            return CommonResult.success();
        }else {
            return CommonResult.error();
        }
    }
    @GetMapping(value = "/class")
    public CommonResult findAllClass(){
        return CommonResult.success(psychologyClassDao.findAll());
    }
}
