package com.study.admin.dao;

import com.study.admin.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:34
 */
@Mapper
public interface UserDao {
    //分页总数
    public Integer selectTotal(@Param("name") String name);
    //用户分页
    public List<User> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name);
    // 管理员登陆
    public User login(User user);

    public int create(User user);

    public User getUserById(@Param("id") Integer id);
    //保存管理员信息
    public Integer updateAdminById(@Param("user") User user, @Param("id") Integer id);
}
