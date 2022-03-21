package com.study.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.study.admin.common.Constants;
import com.study.admin.controller.dto.StaffRequest;
import com.study.admin.dao.MenuDao;
import com.study.admin.dao.RoleDao;
import com.study.admin.dao.RoleMenuDao;
import com.study.admin.dao.StaffDao;
import com.study.admin.entities.Menu;
import com.study.admin.entities.Staff;
import com.study.admin.exception.ServiceException;
import com.study.admin.service.MenuService;
import com.study.admin.service.StaffService;
import com.study.admin.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 23:59
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Resource
    private StaffDao staffDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMenuDao roleMenuDao;
    @Resource
    private MenuService menuService;
    @Override
    public StaffRequest login(Staff staff, StaffRequest staffRequest) {
        Staff one;
        try{
            one = staffDao.login(staff);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one != null){
            BeanUtil.copyProperties(one,staffRequest,true);
//            设置token
            String token = TokenUtil.genToken(one.getId().toString(), one.getPassword());
            staffRequest.setToken(token);
            String role = one.getRole();//ROLE_ADMIN
            Integer roleId = roleDao.findByFlag(role);
            //设置用户的菜单列表
            staffRequest.setMenus(getRoleMenus(roleId));
            return staffRequest;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或者密码错误");
        }
    }
    /**
     * 获取当前角色的菜单列表
     * @author 二爷
     * @E-mail 1299461580@qq.com
     * @date 2022/3/14 14:24 
     * @param  roleId
     * @return java.util.List<com.study.admin.entities.Menu>
     */
    private List<Menu> getRoleMenus(Integer roleId){

        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuDao.getRoleMenu(roleId);
        //查出系统所有菜单
        List<Menu> menus = menuService.findMenus("");
        //new 一个 最后帅选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (!menuIds.contains(menu.getId())){
//                roleMenus.add(menu);
                List<Menu> children = menu.getChildren();
                // removeIf 移除children 里面不在 menuIds集合中的元素
                children.removeIf(child -> !menuIds.contains(child.getId()));
            }
        }
        Iterator<Menu> iterator = menus.iterator();
        while(iterator.hasNext()){
            Menu next = iterator.next();

            if(next.getChildren().isEmpty()){
                if(!menuIds.contains(next.getId())){
                    iterator.remove();
                }
            }

        }

        return menus;

    }

    @Override
    public Staff getStaffById(Integer id) {
        return staffDao.getStaffById(id);
    }

    @Override
    public Integer updateStaffById(Staff staff, Integer id) {
        return staffDao.updateStaffById(staff,id);
    }

    @Override
    public List<Staff> findAll() {
        return staffDao.findAll();
    }

    @Override
    public Integer addStaff(Staff staff) {
        return staffDao.addStaff(staff);
    }

    @Override
    public Integer updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }
    @Override
    public Integer deleteStaff(@Param("id") Integer id){
        return staffDao.deleteStaff(id);
    }

    @Override
    public Integer deleteBatchStaff(List<Integer> ids) {
        return staffDao.deleteBatchStaff(ids);
    }

    @Override
    public Integer saveBatchStaff(List<Staff> staff) {
        return staffDao.saveBatchStaff(staff);
    }

    @Override
    public List<Staff> selectPage(Integer pageNum, Integer pageSize,String staffName) {
        return staffDao.selectPage(pageNum,pageSize,staffName);
    }

    @Override
    public Integer selectTotal(String staffName) {
        return staffDao.selectTotal(staffName);
    }

    @Override
    public List<Staff> list() {
        return staffDao.list();
    }
}
