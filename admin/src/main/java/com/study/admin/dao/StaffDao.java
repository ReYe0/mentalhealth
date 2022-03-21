package com.study.admin.dao;

import com.study.admin.entities.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 23:49
 */
@Mapper
public interface StaffDao {
    //登陆
    public Staff login(Staff staff);
    //通过id获取信息
    public Staff getStaffById(@Param("id") Integer id);
    //保存管理员信息
    public Integer updateStaffById(@Param("staff") Staff staff, @Param("id") Integer id);
    //查询所有员工
    public List<Staff> findAll();
    //插入
    public Integer addStaff(Staff staff);
    //更新
    public Integer updateStaff(Staff staff);
//    删除员工
    public Integer deleteStaff(@Param("id") Integer id);
    //    删除多个员工
    public Integer deleteBatchStaff(@Param("ids") List<Integer> ids);
    //    保存多个员工
    public Integer saveBatchStaff(List<Staff> staff);
//    分页查询
    public List<Staff> selectPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("staffName") String staffName);
//    分页查询总条数
    public Integer selectTotal(String staffName);
    //查询出所有数据
    public List<Staff> list();
}
