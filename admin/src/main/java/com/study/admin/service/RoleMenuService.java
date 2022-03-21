package com.study.admin.service;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 15:35
 */
public interface RoleMenuService {
    public void setRoleMenu(Integer roleId, List<Integer> menuIds);
    //删除
    public Integer deleteByRoleId(Integer roleId);
    //新增
    public Integer insert(Integer roleId,Integer menuId);
    //    查询菜单
    public List<Integer> getRoleMenu(Integer roleId);
}
