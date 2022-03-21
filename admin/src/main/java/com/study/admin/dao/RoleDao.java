package com.study.admin.dao;

import com.study.admin.entities.Role;
import com.study.admin.entities.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 21:28
 */
@Mapper
public interface RoleDao {
    //根据flag查找
    public Integer findByFlag(@Param("flag") String flag);
    //查找全部
    public List<Role> findAll();
    //新增
    public Integer addRole(Role role);
    //更新
    public Integer updateRole(Role role);
    //    分页查询
    public List<Role> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name);
    //    分页查询总条数
    public Integer selectTotal(String name);
    //删除角色
    public Integer deleteById(Integer id);
    //    删除多个员工
    public Integer deleteBatchRole(@Param("ids") List<Integer> ids);
}
