package com.study.front.dao;

import com.study.front.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:34
 */
@Mapper
public interface UserDao {
    //获取文章用户信息
    public User findUserByArticleId(@Param("articleId") Integer articleId);
    // 用户登陆
    public User login(User user);

    public int create(User user);

    public User getUserById(@Param("id") Integer id);
    //保存管理员信息
    public Integer updateAdminById(@Param("user") User user, @Param("id") Integer id);
}
