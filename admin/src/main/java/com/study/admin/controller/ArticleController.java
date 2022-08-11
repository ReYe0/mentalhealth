package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.controller.dto.admin.ArticleResponse;
import com.study.admin.dao.ArticleClassDao;
import com.study.admin.dao.ArticleDao;
import com.study.admin.dao.FilesDao;
import com.study.admin.dao.StaffDao;
import com.study.admin.entities.Article;
import com.study.admin.entities.Staff;
import com.study.admin.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/21 17:01
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleDao articleDao;
    @Resource
    private FilesDao filesDao;
    @Resource
    private ArticleClassDao articleClassDao;
    @Resource
    private StaffDao staffDao;


    //管理员自己的文章
    @GetMapping(value = "/findMyArticle")
    public CommonResult findMyArticle(Integer staffId){
        return CommonResult.success(articleDao.findMyArticle(staffId));
    }

    //删除一级评论
    @DeleteMapping(value = "/delFirstComments")
    @Transactional
    public CommonResult delFirstComments(Integer commentId){
        articleDao.delSecondComments(commentId);
        articleDao.delFirstComments(commentId);
        return CommonResult.success();
    }

    //删除二级评论
    @DeleteMapping(value = "/delSecondComments")
    public CommonResult delSecondComments(Integer commentId){
        return CommonResult.success(articleDao.delSecondComments(commentId));
    }
    //获取有评论的文章getComments
    @GetMapping(value = "/getComments")
    public CommonResult getComments(Integer commentId){
        return CommonResult.success(articleDao.getComments(commentId));
    }

    //获取有评论的文章
    @GetMapping(value = "/getAllArticle")
    public CommonResult getAllArticle(){
        return CommonResult.success(articleDao.getAllArticle());
    }
    //文章一级评论
    @GetMapping(value = "/getFirstComments")
    public CommonResult getFirstComments(Integer articleId){
        return CommonResult.success(articleDao.getFirstComments(articleId));
    }
    //更新文章内容
    @PostMapping(value = "/update")
    public CommonResult updateArticle(@RequestBody Article article){
        return CommonResult.success(articleDao.updateArticle(article));
    }

    //    文章上传
    @PostMapping(value = "/up")
    public CommonResult articleUp(@RequestBody Article article){
        return CommonResult.success(articleDao.insertArticle(article));
    }
    //更新文章类别
    @GetMapping(value = "/updateClass")
    public CommonResult updateClass(Integer articleId,Integer classId){
        return CommonResult.success(articleClassDao.updateClassById(articleId,classId));
    }
    //获取文章分类
    @GetMapping(value = "/class")
    public CommonResult getArticleClass(){
        return CommonResult.success(articleClassDao.findAll());
    }
    //删除文章
    @DeleteMapping(value = "/del/{articleId}")
    @Transactional
    public CommonResult delArticle(@PathVariable("articleId") Integer articleId){
        Article article = articleDao.findById(articleId);
        Integer integer1 = filesDao.updateIsDeleteById(true, article.getFileId());
        Integer integer = articleDao.delArticleById(articleId);
        return CommonResult.success(integer+integer1);
    }
    //审核文章
    @GetMapping(value = "/updateShare")
    public CommonResult updateShare(@RequestParam String share,@RequestParam Integer id){
        Integer integer = articleDao.updateShare(share, id);
        return CommonResult.success(integer);
    }
    //    分页查询
    @GetMapping(value = "/all")
    public CommonResult findArticlePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String title,Integer staffId){
        pageNum = (pageNum - 1) * pageSize;
        Staff nowStaff = staffDao.getStaffById(staffId);
        String role = nowStaff.getRole();
        List<ArticleResponse> articleList = null;
        Integer total = null;
        if (role.equals("ROLE_ADMIN")){
            articleList = articleDao.findArticleList(pageNum, pageSize, title);
            total = articleDao.selectTotal(title);
        }else{
            articleList = articleDao.findArticleListByRole(pageNum, pageSize, title,role);
            total = articleDao.selectTotalByRole(title,role);
        }
//        name = '%' + name + '%';
        Map<String,Object> res = new HashMap<>();
        res.put("articleList",articleList);
        res.put("total",total);
        if(total > 0){
            return CommonResult.success(res);
        }else{
            return CommonResult.error();
        }
    }

//    @GetMapping(value = "/test")
//    public CommonResult articleAll(){
////        List<ArticleResponse> articleList = articleDao.findArticleList();
//        return CommonResult.success(111111);
//    }
}
