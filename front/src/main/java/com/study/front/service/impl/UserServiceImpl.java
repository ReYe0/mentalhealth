package com.study.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.study.front.common.Constants;
import com.study.front.controller.dto.UserRequest;
import com.study.front.dao.UserDao;
import com.study.front.entities.User;
import com.study.front.exception.ServiceException;
import com.study.front.service.UserService;
import com.study.front.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:51
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserRequest login(User user, UserRequest userRequest) {
        User one;
        try{
             one = userDao.login(user);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one != null ){
            BeanUtil.copyProperties(one, userRequest,true);
//            设置token
            String token = TokenUtil.genToken(one.getId().toString(), one.getPassword());
            userRequest.setToken(token);
            return userRequest;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public Integer updateAdminById(User user, @Param("id") Integer id){
        return userDao.updateAdminById(user,id);
    }
}
