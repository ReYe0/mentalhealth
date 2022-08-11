package com.study.front.controller;

import com.study.front.common.CommonResult;
import com.study.front.dao.StaffDao;
import com.study.front.dao.UserDao;
import com.study.front.entities.ArticleClass;
import com.study.front.entities.ConsultCity;
import com.study.front.entities.Staff;
import com.study.front.service.impl.WebSocketService;
import org.apache.commons.collections4.Get;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/18 17:04
 */
@RestController
@RequestMapping(value = "/consult")
public class ConsultController {
    @Resource
    private StaffDao staffDao;
    @Resource
    private UserDao userDao;

    //用户建立连接
    @PostMapping(value = "/userBuild")
    public CommonResult userBuild(){
//        WebSocketService.userDao
        return CommonResult.success();
    }
    //获取首页心理咨询师
    @GetMapping(value = "/getConsultUserHome")
    public CommonResult getConsultUserHome(){
        return CommonResult.success(staffDao.getConsultUserHome());
    }
    //加载消息记录
    @GetMapping(value = "/getMsgHistory")
    public CommonResult getMsgHistory(Integer userId,Integer staffId){

        return CommonResult.success(userDao.getMsgHistory(userId, staffId));
    }
    //查看个人的心理咨询师预约记录
    @GetMapping(value = "/getMyOrderHistory")
    public CommonResult getMyOrderHistory(Integer userId){
        return CommonResult.success(userDao.getMyOrderHistory(userId));
    }
    //预约心理咨询师
    @PostMapping(value = "/orderConsult")
    public CommonResult orderConsultUser(Integer userId,Integer staffId,String dateValue,String timeValue,String orderType){
        Integer integer = userDao.orderConsultUser(userId, staffId, dateValue, timeValue,orderType);
        Staff staff = staffDao.getStaffById(staffId);
        staffDao.updateTrafficByStaffId(staffId, Integer.valueOf(staff.getTraffic())+1);
        if (integer > 0){
            return CommonResult.success();
        }else {
            return CommonResult.error();
        }
    }

    //给心理咨询师私信
    @PostMapping(value = "/addEmail")
    public CommonResult addEmail(Integer userId,Integer staffId,String content){
        Integer integer = staffDao.addEmailNum(userId, staffId, content);
        if(integer > 0){
            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }



    //心理咨询师详情
    @GetMapping(value = "/getConsultInfo")
    public CommonResult getConsultInfo(Integer userId){
        Staff consultInfo = staffDao.getConsultInfo(userId);
        Integer emailNum = staffDao.getEmailNum(userId);
        Map<String,Object> res = new HashMap<>();
        res.put("consultInfo",consultInfo);
        res.put("emailNum",emailNum);
        return CommonResult.success(res);
    }
    //心理咨询师浏览页面
    @GetMapping(value = "/getConsultUser")
    public CommonResult getConsultUser(@RequestParam String className,@RequestParam String city,@RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam String consultUserName){
        List<ArticleClass> consultClass = staffDao.getConsultClass();
        List<ConsultCity> consultCity = staffDao.getConsultCity();
        List<Staff> consultUser = staffDao.getConsultUser(className, city,minPrice,maxPrice,consultUserName);
        Map<String,Object> res = new HashMap<>();
        res.put("consultClass",consultClass);
        res.put("consultCity",consultCity);
        res.put("consultUser",consultUser);
        return CommonResult.success(res);
    }
}
