package com.study.admin.dao;

import com.study.admin.entities.Menu;
import com.study.admin.entities.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 22:17
 */
@Mapper
public interface MenuDao {
    //通过id查找
    public Menu getById(@Param("id") Integer id);
    //找出pid为null 的一级菜单
    public List<Menu> findMenuOne();
    //查找所有
    public List<Menu> findAll(@Param("name") String name);
    //新增
    public Integer addMenu(Menu menu);
    //更新
    public Integer updateMenu(Menu menu);
    //    分页查询
    public List<Menu> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name);
    //    分页查询总条数
    public Integer selectTotal(String name);
    //删除角色
    public Integer deleteById(Integer id);
    //    删除多个员工
    public Integer deleteBatchMenu(@Param("ids") List<Integer> ids);
}
