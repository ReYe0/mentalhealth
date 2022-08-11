package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.dao.ConsultDao;
import com.study.admin.entities.Consult;
import com.study.admin.entities.Staff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/22 23:14
 */
@RestController
@Slf4j
@RequestMapping("/consult")
public class ConsultController {

    @Resource
    private ConsultDao consultDao;


    //删除预约记录
    @DeleteMapping(value = "/del")
    public CommonResult delConsult(Integer orderId){
        return CommonResult.success(consultDao.delConsultOrder(orderId));
    }
    //结束咨询
    @GetMapping(value = "/endConsult")
    public CommonResult endConsult( Integer orderId){
        Integer integer = consultDao.updateConsultOrderById(orderId, "3");
        if(integer > 0 ){
            return CommonResult.success();
        }else {
            return CommonResult.error();
        }
    }
    //加载消息记录
    @GetMapping(value = "/getMsgHistory")
    public CommonResult getMsgHistory(Integer userId,Integer staffId){

        return CommonResult.success(consultDao.getMsgHistory(userId, staffId));
    }
    //获取预约人信息
    @GetMapping(value = "/getConsultUser")
    public CommonResult getConsultUser(@RequestParam Integer staffId){
        return CommonResult.success(consultDao.getConsultUser( staffId));
    }

    //保存咨询记录，
    @GetMapping(value = "/save")
    public CommonResult saveRecord(@RequestParam String record,@RequestParam Integer id){
        consultDao.updateRecord(record,id);
        return CommonResult.success();
    }
    //    分页查询 心理咨询记录
    @GetMapping(value = "/page")
    public CommonResult findPage(@RequestParam Integer staffId,@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String name,@RequestParam String radio){
        pageNum = (pageNum - 1) * pageSize;
        Date date = new Date();
        String dataValue;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(radio.equals("0")){
            dataValue = "";
        }else if(radio.equals("1")){//只看今天
            dataValue = simpleDateFormat.format(date);
        }else {//获取明天的日期
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(gregorianCalendar.DATE,1);
            dataValue = simpleDateFormat.format(gregorianCalendar.getTime());
        }
        List<Consult> consultOrderList = consultDao.getConsultOrderList(staffId, pageSize, pageNum, name,dataValue);
//        name = '%' + name + '%';
        Integer total = consultDao.getConsultOrderListTotal(name,dataValue);
        Map<String,Object> res = new HashMap<>();
        res.put("consultOrderList",consultOrderList);
        res.put("total",total);
            return CommonResult.success(res);
    }
}
