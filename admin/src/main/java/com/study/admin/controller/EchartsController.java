package com.study.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.study.admin.common.CommonResult;
import com.study.admin.entities.Staff;
import com.study.admin.service.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/10 22:50
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private StaffService staffService;

    @GetMapping(value = "/members")
    public CommonResult member(){
        List<Staff> list = staffService.findAll();
        int q1 = 0;//第一节度
        int q2 = 0;//第二节度
        int q3 = 0;//第三节度
        int q4 = 0;//第四节度
        for (Staff staff : list){
            Date createTime = staff.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter){
                case Q1:q1 += 1;break;
                case Q2:q2 += 1;break;
                case Q3:q3 += 1;break;
                case Q4:q4 += 1;break;
                default:break;
            }
        }
        return CommonResult.success(CollUtil.newArrayList(q1,q2,q3,q4));
    }
}
