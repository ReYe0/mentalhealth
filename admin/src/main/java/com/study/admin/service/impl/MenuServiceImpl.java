package com.study.admin.service.impl;

import com.study.admin.dao.MenuDao;
import com.study.admin.entities.Menu;
import com.study.admin.entities.Role;
import com.study.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 22:20
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public Menu getById(Integer id) {
        return menuDao.getById(id);
    }

    @Override
    public List<Menu> findMenus(String name) {
        List<Menu> list = menuDao.findAll(name);
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
//        List<Menu> parentNode = menuService.findMenuOne();
        //找出一级菜单的子菜单
        for(Menu menu : parentNodes){
            //筛选所有数据中pid=父级id的数据 就是二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }

    @Override
    public List<Menu> findMenuOne() {
        return menuDao.findMenuOne();
    }

    @Override
    public List<Menu> findAll(String name) {
        return menuDao.findAll(name);
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuDao.addMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public List<Menu> selectPage(Integer pageNum, Integer pageSize, String name) {
        return menuDao.selectPage(pageNum, pageSize, name);
    }

    @Override
    public Integer selectTotal(String name) {
        return menuDao.selectTotal(name);
    }

    @Override
    public Integer deleteById(Integer id) {
        return menuDao.deleteById(id);
    }

    @Override
    public Integer deleteBatchMenu(List<Integer> ids) {
        return menuDao.deleteBatchMenu(ids);
    }
}
