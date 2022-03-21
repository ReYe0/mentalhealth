package com.study.admin.service;

import com.study.admin.controller.dto.UserRequest;
import com.study.admin.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:49
 */
//@Service
public interface UserService {

    // 管理员登陆
    public UserRequest login(User user, UserRequest userRequest);

    public int create(User user);

    public User getUserById(@Param("id") Integer id);
    //保存管理员信息
    public Integer updateAdminById(User user, @Param("id") Integer id);
}
