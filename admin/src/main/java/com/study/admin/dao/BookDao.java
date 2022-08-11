package com.study.admin.dao;

import com.study.admin.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/25 18:36
 */
@Mapper
public interface BookDao {
    //父订单数量
    public Integer orderPageTotal(@Param("id") String id);
    //获取所有子订单
    public List<BookOrderSon> getBookOrderSonAll();
    //获取用户的父订单
    public List<BookOrder> getBookOrder(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("id") String id);
//获取书籍分类
    public List<ArticleClass> getBookClass();
    //更新书籍信息
    public Integer updateBook(Book book);
    //删除书籍
    public Integer delBookBeId(@Param("id") Integer id);
    //删除封面
    public Integer delBookImgById(@Param("id")Integer id);
    //查找书籍封面
    public List<BookImg> findBookImgs();
    //分页总数
    public Integer pageTotal(@Param("bookName") String bookName);
    //查找书籍
    public List<Book> findBookAll(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("bookName") String bookName);
    //插入书籍封面
    public Integer addBookImg(@Param("bookId") Integer bookId,@Param("fileId") Integer fileId);
    //新增书籍
    public Integer addBook(Book book);
}
