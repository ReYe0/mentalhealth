package com.study.front.controller;

import com.study.front.common.CommonResult;
import com.study.front.dao.BookDao;
import com.study.front.entities.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/26 13:52
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Resource
    private BookDao bookDao;

    //获取首页书籍
    @GetMapping(value = "/homeBook")
    public CommonResult getHomeBook(){
        List<Book> homeBook = bookDao.getHomeBook();
        List<BookImg> bookImgs = bookDao.findBookImgs();
        for (Book book :
                homeBook) {
            for (BookImg bookimg :
                    bookImgs) {
                if (book.getId() == bookimg.getBookId()) {
                    List<BookImg> children = book.getChildren();
                    if(children == null) children = new ArrayList<BookImg>();
                    children.add(bookimg);
                    book.setChildren(children);
                }
                }
        }
        return CommonResult.success(homeBook);
    }
    //获取订单历史
    @GetMapping(value = "/getOrderRecord/{userId}")
    public CommonResult getOrderRecord(@PathVariable Integer userId){
        List<BookOrder> bookOrderList = bookDao.getBookOrderByUserId(userId);
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
        return CommonResult.success(bookOrderList);
    }
    //新增用户地址
    @PostMapping(value = "/addUserCity")
    public CommonResult addUserCity(@RequestBody UserCity userCity){
        return CommonResult.success(bookDao.addUserCity(userCity));
    }
    //更新用户地址
    @PostMapping(value = "/updateUserCity")
    public CommonResult updateUserCity(@RequestBody UserCity userCity){
        System.out.println(213);
        return CommonResult.success(bookDao.updateUserCity(userCity));
    }
    //获取用户地址
    @GetMapping(value = "/getCity/{userId}")
    public CommonResult getCity(@PathVariable Integer userId){
        return CommonResult.success(bookDao.getUserCity(userId));
    }
    //新增订单
    @PostMapping(value = "/addOrder")
    public CommonResult addOrder( @RequestBody List<Integer> cartIds,Integer userId,Integer totalPrice,Integer bookNum,String receiver,String receiverPhone,String cityDetail,String remark){
        int random = (int)(Math.random()*9+1);
        String valueOf = String.valueOf(random);
        int hashCode = UUID.randomUUID().toString().hashCode();
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        String orderId = valueOf + String.format("%015d", hashCode);
//        Integer integer = Integer.valueOf(orderId);
        bookDao.addOrder(orderId,userId,bookNum,totalPrice,receiver,receiverPhone,cityDetail,remark);
        int length = cartIds.size()/3;
        for (int i = 0; i < length; i++) {
            bookDao.delCartById(cartIds.get(i));//删除购物车已经选中的商品
        }
        for (int i = length;i<2*length;i++){
            int goodNum = 2*length + i - length;
            bookDao.addOrderSon(orderId,cartIds.get(i),cartIds.get(goodNum));//插入子订单
        }

//        System.out.println(cartIds);
        return CommonResult.success();
    }
    //删除购物车里面的商品
    @DeleteMapping(value = "/delCart/{id}")
    public CommonResult delCart(@PathVariable Integer id){
        return CommonResult.success(bookDao.delCartById(id));
    }
    //更新购物车书籍数量
    @GetMapping(value = "/updateBookNum")
    public CommonResult updateBookNum(@RequestParam Integer bookId,@RequestParam Integer userId,@RequestParam Integer bookNum){
//        Integer cartBookNum = bookDao.getCartBookNum(String.valueOf(bookId), userId);
        Integer integer = bookDao.updateCartBookNum(String.valueOf(bookId), userId, bookNum);
        return CommonResult.success();
    }

    //获取购物车信息
    @GetMapping(value = "/getCartList")
    public CommonResult getCartList(@RequestParam Integer userId){
        List<BookCart> cartList = bookDao.getCartList(userId);
        List<BookImg> bookImgs = bookDao.findBookImgs();
        for (BookCart bookCart:cartList){
            for (BookImg bookImg:bookImgs){
                if(bookCart.getBookId() == bookImg.getBookId()){
                    List<BookImg> children = bookCart.getChildren();
                    if(children == null) children = new ArrayList<>();
                    children.add(bookImg);
                    bookCart.setChildren(children);
                }
            }
        }
        return CommonResult.success(cartList);
    }

    //加入购物车
    @GetMapping(value = "/addCart")
    public CommonResult addCart(@RequestParam String bookId,@RequestParam Integer userId,@RequestParam Integer bookNum){
        Integer judge = bookDao.judgeCart(bookId, userId);
        if(judge == 0){
            bookDao.addCart(bookId, userId, bookNum);
        }else {
            Integer cartBookNum = bookDao.getCartBookNum(bookId, userId);
            bookDao.updateCartBookNum(bookId,userId,cartBookNum+1);
        }
        return CommonResult.success();
    }
    //获取书籍详细信息
    @GetMapping(value = "/getDetails")
    public CommonResult getBookDetails(@RequestParam Integer productID){
        Book book = bookDao.getDetailsById(productID);
        List<BookImg> bookImgs = bookDao.findBookImgs();
        for(BookImg bookImg : bookImgs){
            if(bookImg.getBookId() == book.getId()){
                List<BookImg> children = book.getChildren();
                if(children == null) children = new ArrayList<BookImg>();
                children.add(bookImg);
                book.setChildren(children);
            }
        }
        return CommonResult.success(book);
    }
    //获取书籍分类
    @GetMapping(value = "/bookClass")
    public CommonResult getBookClass(){
        return CommonResult.success(bookDao.findBookClass());
    }
    //书籍分页
    @GetMapping(value = "/findBookAll")
    public CommonResult findBookAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String className){
        pageNum = (pageNum - 1) * pageSize;
        List<Book> bookList = bookDao.findBookAll(pageSize, pageNum, className);
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
        Integer total = bookDao.pageTotal(className);
        HashMap<Object, Object> res = new HashMap<>();
        res.put("bookList",bookList);
        res.put("total",total);
        return CommonResult.success(res);
    }
}
