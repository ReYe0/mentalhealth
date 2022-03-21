package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.controller.dto.admin.ArticleResponse;
import com.study.admin.dao.ArticleDao;
import com.study.admin.dao.FilesDao;
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
    //删除文章
    @DeleteMapping(value = "/del/{articleId}")
    @Transactional
    public CommonResult delArticle(@PathVariable("articleId") Integer articleId){
        Article article = articleDao.findById(articleId);
        Integer integer = articleDao.delArticleById(articleId);
        Integer integer1 = filesDao.updateIsDeleteById(true, article.getFileId());
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
    public CommonResult findArticlePage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String title){
        pageNum = (pageNum - 1) * pageSize;
        List<ArticleResponse> articleList = articleDao.findArticleList(pageNum, pageSize, title);
//        name = '%' + name + '%';
        Integer total = articleDao.selectTotal(title);
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
