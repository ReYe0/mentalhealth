package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.controller.dto.admin.PsychologyListResponse;
import com.study.admin.dao.FilesDao;
import com.study.admin.dao.PsychologyClassDao;
import com.study.admin.dao.PsychologyOrderDao;
import com.study.admin.entities.PsychologyOrderQuestion;
import com.study.admin.entities.PsychologyOrderQuestionSon;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.smartcardio.CommandAPDU;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //获取有人测试过的测试题目
    @GetMapping(value = "getAlreadyChecked")
    public CommonResult getAlreadyChecked(){
        return CommonResult.success(psychologyOrderDao.getAlreadyChecked());
    }
    //获取测试结果
    @GetMapping(value = "/getCheckResult")
    public CommonResult getCheckResult(@RequestParam Integer questionId,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        List<PsychologyOrderQuestion> checkResult = psychologyOrderDao.getCheckResult(questionId,pageNum,pageSize);
        Integer total = psychologyOrderDao.getCheckResultTotal(questionId);
        Map<String,Object> res = new HashMap<>();
        res.put("checkResultList",checkResult);
        res.put("total",total);
        return CommonResult.success(res);
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
    public CommonResult getPsychologyList(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String title){
        pageNum = (pageNum - 1) * pageSize;
        List<PsychologyOrderQuestion> psychologyList = psychologyOrderDao.getPsychologyList(pageNum, pageSize, title);
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
