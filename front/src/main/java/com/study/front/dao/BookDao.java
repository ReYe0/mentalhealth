package com.study.front.dao;

import com.study.front.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/26 13:52
 */
@Mapper
public interface BookDao {
    //获取首页书籍
    public List<Book> getHomeBook();
    //获取所有子订单
    public List<BookOrderSon> getBookOrderSonAll();
    //获取用户的父订单
    public List<BookOrder> getBookOrderByUserId(@Param("userId") Integer userId);
    //新增用户地址
    public Integer addUserCity(UserCity userCity);
    //更新用户地址
    public Integer updateUserCity(UserCity userCity);
    //获取用户地址
    public UserCity getUserCity(@Param("userId") Integer userId);
    //插入子订单
    public Integer addOrderSon(@Param("orderId") String orderId,@Param("bookId") Integer bookId,@Param("bookNumSon") Integer bookNumSon);
    //新增父订单
    public Integer addOrder(@Param("id") String id,@Param("userId") Integer userId,@Param("bookNum") Integer bookNum,@Param("totalPrice") Integer totalPrice,@Param("receiver") String receiver,@Param("receiverPhone") String receiverPhone,@Param("cityDetail") String cityDetail,@Param("remark") String remark);
    //更新购物车的书籍数量
//    public Integer updateBookNum
    //删除购物车商品
    public Integer delCartById(@Param("id") Integer id);
    //获取购物车信息
    public List<BookCart> getCartList(@Param("userId") Integer userId);
    //更新书籍数量
    public Integer updateCartBookNum(@Param("bookId") String bookId,@Param("userId") Integer userId,@Param("bookNum") Integer bookNum);
    //获取购物车中的书籍数量
    public Integer getCartBookNum(@Param("bookId") String bookId,@Param("userId") Integer userId);
    //判断购物车中是否存在这个书
    public Integer judgeCart(@Param("bookId") String bookId,@Param("userId") Integer userId);
    //加入购物车
    public Integer addCart(@Param("bookId") String bookId,@Param("userId") Integer userId,@Param("bookNum") Integer bookNum);
    //获取书籍详细信息
    public Book getDetailsById(@Param("productID") Integer productID);
    //获取书籍分类
    public List<ArticleClass> findBookClass();
    //查找书籍封面
    public List<BookImg> findBookImgs();
    //分页数量
    public Integer pageTotal(@Param("className") String className);
    //查找所有书籍
    public List<Book> findBookAll(@Param("pageSize") Integer pageSize,@Param("pageNum") Integer pageNum,@Param("className") String  className);
}
