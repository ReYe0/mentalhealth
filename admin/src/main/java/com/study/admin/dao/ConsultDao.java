package com.study.admin.dao;

import com.study.admin.entities.Consult;
import com.study.admin.entities.ConsultMsg;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/22 23:15
 */
@Mapper
public interface ConsultDao {
    //删除预约记录
    public Integer delConsultOrder(@Param("orderId") Integer orderId);
    //更新订单状态
    public Integer updateConsultOrderById(@Param("orderId") Integer orderId,@Param("isEnable") String isEnable);
    //查找用户和心理咨询师的消息记录
    public List<ConsultMsg> getMsgHistory(@Param("userId") Integer userId, @Param("staffId") Integer staffId);
    //获取预约人信息
    public List<Consult> getConsultUser(@Param("staffId") Integer staffId);
    //保存记录信息
    public Integer updateRecord(@Param("record")String record,@Param("id") Integer id);
    //获取用户预约记录总数
    public Integer getConsultOrderListTotal(@Param("name") String name,@Param("date") String date);
    //获取用户预约记录
    public List<Consult> getConsultOrderList(@Param("staffId") Integer staffId,@Param("pageSize") Integer pageSize,@Param("pageNum") Integer pageNum,@Param("name") String name,@Param("date") String date);
}
