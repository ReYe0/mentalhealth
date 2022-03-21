package com.study.admin.service.impl;

import com.study.admin.dao.RoleMenuDao;
import com.study.admin.entities.Menu;
import com.study.admin.service.MenuService;
import com.study.admin.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 15:35
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuDao roleMenuDao;
    @Resource
    private MenuService menuService;

    @Transactional//事务注解，同时成功，同时失败
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        //先删除当前角色id绑定的所有关系
        roleMenuDao.deleteByRoleId(roleId);
        //再把前端传过来的菜单id数组绑定到当前这个角色id上去
        for (Integer menuId : menuIds){
            Menu menu = menuService.getById(menuId);
//            if(menu.getPid() != null){//二级菜单 并且穿过来的menuID数组里面没有她的父级id 那么我们就得补上这个父级id
//                roleMenuDao.insert(roleId,menu.getPid());
//            }
            roleMenuDao.insert(roleId, menuId);
        }
    }

    @Override
    public Integer deleteByRoleId(Integer roleId) {
        return roleMenuDao.deleteByRoleId(roleId);
    }

    @Override
    public Integer insert(Integer roleId, Integer menuId) {
        return roleMenuDao.insert(roleId, menuId);
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuDao.getRoleMenu(roleId);
    }
}
