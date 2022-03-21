package com.study.admin.service;

import com.study.admin.entities.Menu;
import com.study.admin.entities.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 22:19
 */
public interface MenuService {
    //通过id查找
    public Menu getById(@Param("id") Integer id);
    //找出菜单数据
    List<Menu> findMenus(String name);
    //找出pid为null 的一级菜单
    public List<Menu> findMenuOne();
    //查找所有
    public List<Menu> findAll(String name);
    //新增
    public Integer addMenu(Menu menu);
    //更新
    public Integer updateMenu(Menu menu);
    //    分页查询
    public List<Menu> selectPage(Integer pageNum, Integer pageSize, String name);
    //    分页查询总条数
    public Integer selectTotal(String name);
    //删除角色
    public Integer deleteById(Integer id);
    //    删除多个员工
    public Integer deleteBatchMenu(List<Integer> ids);

}
