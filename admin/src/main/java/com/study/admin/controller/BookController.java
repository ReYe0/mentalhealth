package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.dao.BookDao;
import com.study.admin.entities.Book;
import com.study.admin.entities.BookImg;
import com.study.admin.entities.BookOrder;
import com.study.admin.entities.BookOrderSon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/25 18:48
 */
@RestController
@Slf4j
@RequestMapping("/book")
public class BookController
{
    @Resource
    private BookDao bookDao;

    //获取用户订单
    @GetMapping(value = "/getOrderRecord")
    public CommonResult getOrderRecord(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String id){
        pageNum = (pageNum - 1) * pageSize;
        List<BookOrder> bookOrderList = bookDao.getBookOrder(pageNum, pageSize,id);
        List<BookImg> bookImgs = bookDao.findBookImgs();
        List<BookOrderSon> bookOrderSonList = bookDao.getBookOrderSonAll();
        for (BookOrderSon bookorderson :
                bookOrderSonList) {
            for (BookImg bookImg :
                    bookImgs) {
                if(bookorderson.getBookId() == bookImg.getBookId()){
                    List<BookImg> children = bookorderson.getChildren();
                    if(children == null) children = new ArrayList<BookImg>();
                    children.add(bookImg);
                    bookorderson.setChildren(children);
                }
            }
        }
        for (BookOrder bookOrder :
                bookOrderList) {
            for (BookOrderSon bookOrderSon :
                    bookOrderSonList) {
                if(bookOrderSon.getOrderId().equals(bookOrder.getId()+"")){
                    List<BookOrderSon> children = bookOrder.getChildren();
                    if(children == null) children = new ArrayList<BookOrderSon>();
                    children.add(bookOrderSon);
                    bookOrder.setChildren(children);
                }
            }
        }
        Integer total = bookDao.orderPageTotal(id);
        Map<String,Object> res = new HashMap<>();
        res.put("bookOrderList",bookOrderList);
        res.put("total",total);
        return CommonResult.success(res);
    }
    //获取书籍分类
    @GetMapping(value = "/bookClass")
    public CommonResult getBookClass(){
        return CommonResult.success(bookDao.getBookClass());
    }
    //更新书籍信息
    @PostMapping(value = "/updateBook")
    public CommonResult updateBook(@RequestBody Book book){
        return CommonResult.success(bookDao.updateBook(book));
    }
    //删除书籍
    @DeleteMapping(value = "/delBook/{id}")
    public CommonResult delBook(@PathVariable Integer id){
        return CommonResult.success(bookDao.delBookBeId(id));
    }
    //删除封面
    @DeleteMapping(value = "/delImg/{id}")
    public CommonResult delBookImg(@PathVariable Integer id){
        return CommonResult.success(bookDao.delBookImgById(id));
    }

    //获取书籍
    @GetMapping(value = "/getBook")
    public CommonResult getBook(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String bookName){
        pageNum = (pageNum - 1) * pageSize;
        List<Book> bookList = bookDao.findBookAll(pageNum, pageSize,bookName);
        List<BookImg> bookImgs = bookDao.findBookImgs();
        if(bookList.size() != 0){
            for (Book book: bookList) {
                for (BookImg bookImg: bookImgs){
                    if(book.getId() == bookImg.getBookId()){
                        List<BookImg> children = book.getChildren();
                        if(children == null) children = new ArrayList<BookImg>();
                        children.add(bookImg);
                        book.setChildren(children);
                    }
                }
            }
        }
        Integer total = bookDao.pageTotal(bookName);
        Map<String,Object> res = new HashMap<>();
        res.put("bookList",bookList);
        res.put("total",total);
//        if(total >= 0){
            return CommonResult.success(res);
//        }else{
//            return CommonResult.error();
//        }
    }
    //插入书籍封面
    @GetMapping(value = "/addBookImg")
    public CommonResult addBookImg(@RequestParam Integer bookId,@RequestParam Integer fileId){
        return CommonResult.success(bookDao.addBookImg(bookId, fileId));
    }

    //新增书籍
    @PostMapping(value = "/addBook")
    public CommonResult addBook(@RequestBody Book book){
        Integer integer = bookDao.addBook(book);
        if(integer > 0){
            return CommonResult.success(book.getId());
        }else {
            return CommonResult.error();
        }
    }
}
