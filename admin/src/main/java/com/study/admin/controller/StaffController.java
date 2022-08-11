package com.study.admin.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.study.admin.common.CommonResult;
import com.study.admin.controller.dto.StaffRequest;
import com.study.admin.dao.StaffDao;
import com.study.admin.entities.ArticleClass;
import com.study.admin.entities.ConsultCity;
import com.study.admin.entities.Staff;
import com.study.admin.service.StaffService;
import com.study.admin.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 23:57
 */
@RestController
@Slf4j
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private StaffService staffService;
    @Resource
    private StaffDao staffDao;

    //获取主页信息
    @GetMapping(value = "/homeDetail")
    public CommonResult homeDetail(){
        Integer userNum = staffDao.getUserNum();
        Integer consultNum = staffDao.getConsultNum();
        Integer psychologyNum = staffDao.getPsychologyNum();
        Integer staffNum = staffDao.getStaffNum();
        HashMap<Object, Object> res = new HashMap<>();
        res.put("userNum",userNum);
        res.put("consultNum",consultNum);
        res.put("psychologyNum",psychologyNum);
        res.put("staffNum",staffNum);
        return CommonResult.success(res);
    }
    //登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult Login(@RequestBody Staff staff, StaffRequest staffRequest){
        StaffRequest response = staffService.login(staff,staffRequest);
        Integer staffId = response.getId();
        Staff roleStaff = staffDao.getStaffById(staffId);
        response.setRole(roleStaff.getRole());
        response.setStaffName(roleStaff.getStaffName());
        return CommonResult.success(response);
    }
    //用户信息
    @GetMapping(value = "/get/{id}")
    public CommonResult getStaffById(@PathVariable("id") Integer id){
        Staff staff = staffService.getStaffById(id);
        List<ArticleClass> consultClassAll = staffDao.getConsultClassAll();
        List<ConsultCity> consultCityALl = staffDao.getConsultCityALl();
        Map<String,Object> res = new HashMap<>();
        res.put("consultClassAll",consultClassAll);
        res.put("consultCityALl",consultCityALl);
        res.put("staff",staff);
        log.info("***查询结果："+staff);
//        if(staff != null){
            return CommonResult.success(res);
//        }else {
//            return CommonResult.error();
//        }
    }
    //保存用户信息
    @PostMapping(value = "/login/save")
    public CommonResult saveStaff(@RequestBody Staff staff){
        Integer status = staffService.updateStaffById(staff, staff.getId());
        return status > 0 ? CommonResult.success(staffService.getStaffById(staff.getId())) : CommonResult.error();
    }

    //查询所有员工
    @GetMapping(value = "/all")
    public CommonResult findAll(){
        List<Staff> staff = staffService.findAll();
        if(staff.size() > 0){
           return CommonResult.success(staff);

        }else{
            return CommonResult.error();
        }
    }

//    新增和保存员工资料
    @PostMapping(value = "/save")
    public CommonResult save(@RequestBody Staff staff){ // 把JSON数据 改成 Staff对象
        Integer status = 0;
        if(staff.getId() ==null){
            status = staffService.addStaff(staff);

        }else {
            status = staffService.updateStaff(staff);
        }
        if(status > 0){
            return CommonResult.success();
        }else{
           return CommonResult.error();
        }
    }
//    删除员工
    @DeleteMapping(value = "/del/{id}")
    public CommonResult deleteStaff(@PathVariable Integer id){
        Integer status = staffService.deleteStaff(id);
        if(status > 0){
            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //    删除多个员工
    @PostMapping(value = "/del/batch")
    public CommonResult deleteBatchStaff(@RequestBody List<Integer> ids){
        Integer status = staffService.deleteBatchStaff(ids);
        if(status > 0){
            return  CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
//    分页查询
    @GetMapping(value = "/page")
    public CommonResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String staffName){
        pageNum = (pageNum - 1) * pageSize;
        List<Staff> staff = staffService.selectPage(pageNum, pageSize,staffName);
//        name = '%' + name + '%';
        Integer total = staffService.selectTotal(staffName);
        Map<String,Object> res = new HashMap<>();
        res.put("staffList",staff);
        res.put("total",total);
        List<ArticleClass> consultClassAll = staffDao.getConsultClassAll();
        List<ConsultCity> consultCityALl = staffDao.getConsultCityALl();
        res.put("consultClassAll",consultClassAll);
        res.put("consultCityALl",consultCityALl);
//        TokenUtil.getCurrentAdminUser();
            return CommonResult.success(res);
    }
//    导出接口
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
//        TokenUtil.getCurrentAdminUser();
        List<Staff> list = staffService.list();
//        通过工具类穿件Writer 写到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath+"/用户信息.xlsx");
//        内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("员工姓名","员工姓名");
//        writer.addHeaderAlias("account","员工账号");
//        writer.addHeaderAlias("password","员工密码");
//        一次性写出list的对象到Excel，使用默认样式，强制输出标题
        ExcelWriter write = writer.write(list,true);
        log.info("**"+write.toString());

//        设置浏览器响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
//    导入
    @PostMapping("/import")
    public CommonResult imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Staff> list = reader.readAll(Staff.class);
//        List<StaffDTO> list = reader.read(0, 1, StaffDTO.class);
        Integer num = staffService.saveBatchStaff(list);
//        log.info("***:"+list);
        if(num > 0){
                return CommonResult.success(num);
            }else{
                return CommonResult.error();
        }
    }
}
