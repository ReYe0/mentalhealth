package com.study.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 15:34
 */
@Mapper
public interface RoleMenuDao {

    //删除
    public Integer deleteByRoleId(Integer roleId);
    //新增
    public Integer insert(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
//    查询菜单
    public List<Integer> getRoleMenu(@Param("roleId") Integer roleId);
//    public void setRoleMenu(Integer roleId, List<Integer> menuIds);
}
