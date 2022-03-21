package com.study.admin.service.impl;

import com.study.admin.dao.RoleDao;
import com.study.admin.entities.Role;
import com.study.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 21:34
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public Integer findByFlag(String flag) {
        return roleDao.findByFlag(flag);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Integer addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public List<Role> selectPage(Integer pageNum, Integer pageSize, String name) {
        return roleDao.selectPage(pageNum, pageSize, name);
    }

    @Override
    public Integer selectTotal(String name) {
        return roleDao.selectTotal(name);
    }

    @Override
    public Integer deleteById(Integer id) {
        return roleDao.deleteById(id);
    }

    @Override
    public Integer deleteBatchRole(List<Integer> ids) {
        return roleDao.deleteBatchRole(ids);
    }
}
