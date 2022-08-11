package com.study.front.dao;

import com.study.front.entities.ArticleClass;
import com.study.front.entities.ConsultCity;
import com.study.front.entities.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/18 17:08
 */
@Mapper
public interface StaffDao {
    //获取所有心理咨询师
    public List<Staff> getConsultUserHome();
    //获取心理咨询师信息
    public Staff getStaffById(@Param("staffId") Integer staffId);
    //更新访问量
    public Integer updateTrafficByStaffId(@Param("staffId") Integer staffId,@Param("traffic") Integer traffic);
    //新增私信
    public Integer addEmailNum(@Param("userId") Integer userId,@Param("staffId") Integer staffId,@Param("content") String content);
    //获取私信数量
    public Integer getEmailNum(@Param("staffId") Integer staffId);
    //获取心理咨询师详细信息
    public Staff getConsultInfo(@Param("userId") Integer userId);
    //获取咨询的城市
    public List<ConsultCity> getConsultCity();
    //获取咨询类别
    public List<ArticleClass> getConsultClass();
    //获取心理咨询师
    public List<Staff> getConsultUser(@Param("className") String className,@Param("city") String city,@Param("minPrice") Integer minPrice,@Param("maxPrice") Integer maxPrice,@Param("consultUserName") String consultUserName);

}
