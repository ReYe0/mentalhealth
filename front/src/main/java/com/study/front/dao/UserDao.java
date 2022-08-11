package com.study.front.dao;

import com.study.front.entities.ConsultMsg;
import com.study.front.entities.ConsultOrder;
import com.study.front.entities.Staff;
import com.study.front.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:34
 */
@Mapper
public interface UserDao {
    //获取用户关注的心理咨询师列表
    public List<Staff> getFocusStaff(@Param("userId") Integer userId);
    //查找用户和心理咨询师的消息记录
    public List<ConsultMsg> getMsgHistory(@Param("userId") Integer userId,@Param("staffId") Integer staffId);
    //插入消息记录
    public Integer insertMsg(@Param("userId") Integer userId,@Param("staffId") Integer staffId,@Param("msg") String msg,@Param("name") String name,@Param("type") String type);
    //用户的预约记录
    public List<ConsultOrder> getMyOrderHistory(@Param("userId") Integer userId);
    //用户预约心理咨询师
    public Integer orderConsultUser(@Param("userId") Integer userId,@Param("staffId") Integer staffId,@Param("dateValue") String dateValue,@Param("timeValue") String timeValue,@Param("orderType") String orderType);
    //通过id 查找 心理咨询师
    public Staff findByStaffId(@Param("staffId") Integer staffId);
    //获取文章用户信息
    public Staff findUserByArticleId(@Param("articleId") Integer articleId);
    // 用户登陆
    public User login(User user);

    public int create(User user);
    //更新用户频道
    public Integer updateUserById(@Param("id") Integer id,@Param("value") String value);
    public User getUserById(@Param("id") Integer id);
    //保存管理员信息
    public Integer updateAdminById(@Param("user") User user, @Param("id") Integer id);
}
