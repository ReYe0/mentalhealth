package com.study.front.controller;

import com.study.front.common.CommonResult;
import com.study.front.controller.dto.ArticleResponse;
import com.study.front.dao.*;
import com.study.front.entities.Article;
import com.study.front.entities.ArticleComment;
import com.study.front.entities.ArticlePraise;
import com.study.front.entities.Files;
import com.study.front.service.ArticleService;
import com.study.front.service.FilesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/16 17:42
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleDao articleDao;
    @Resource
    private ArticleClassDao articleClassDao;
    @Resource
    private FilesService filesService;
    @Resource
    private UserDao userDao;
    @Resource
    private ArticleCommentDao articleCommentDao;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticlePraiseDao articlePraiseDao;

//    管理员

    //***********************************************以下是用户端
    @GetMapping(value = "/findMyArticle")
    public CommonResult findMyArticle(Integer staffId){
        return CommonResult.success(articleDao.findByUserId(staffId));
    }
    //是否关注
    @GetMapping(value = "/quitConcern")
    public CommonResult quitConcern(Integer userId,Integer staffId){
        return CommonResult.success(articleDao.quitConcern(userId,staffId));
    }
    //是否关注
    @GetMapping(value = "/isConcern")
    public CommonResult isConcern(Integer userId,Integer staffId){
        return CommonResult.success(articleDao.isConcern(userId,staffId));
    }
    //关注
    @GetMapping(value = "/concern")
    public CommonResult concern(Integer userId,Integer staffId){
        return CommonResult.success(articleDao.concernChange(userId,staffId));
    }
    //根据用户id获取文章
    @GetMapping(value = "/userArticle/{userId}")
    public CommonResult getArticleByUserId(@PathVariable("userId") Integer userId){
        List<ArticleResponse> res = articleDao.findByUserId(userId);
        return CommonResult.success(res);
    }
    //获取用户点赞数
    @GetMapping(value = "/praiseNumTotal/{userId}")
    public CommonResult getPraise(@PathVariable("userId") Integer userId){
        Integer integer = articlePraiseDao.findPraiseNumByUserId(userId);
        return CommonResult.success(integer);
    }
    //查找用户文章数目
    @GetMapping(value = "/articleNum")
    public CommonResult articleNum(Integer userId){
        Integer integer = articleDao.articleNum(userId);
        return CommonResult.success(integer);
    }
    //获取同类型文章
    @GetMapping(value = "/theClass")
    public CommonResult findArticleByClass(Integer articleId,Integer classId){
        List<Article> articleByClass = articleDao.findArticleByClass(articleId, classId);
        return  CommonResult.success(articleByClass);
    }
    //取消文章
    @GetMapping(value = "/delPraise")
    public CommonResult delPraise(Integer userId,Integer articleId){
        Integer praise = articlePraiseDao.delPraise(userId, articleId);
        return CommonResult.success(praise);
    }
    //点赞文章
    @GetMapping(value = "/toPraise")
    public CommonResult toPraise(Integer userId,Integer articleId){
        Integer praise = articlePraiseDao.toPraise(userId, articleId);
        return CommonResult.success(praise);
    }
    //是否点赞文章
    @GetMapping(value = "/isPraise")
    public CommonResult isPraise(Integer userId,Integer articleId){
        Integer praise = articlePraiseDao.isPraiseByUserIdAndArticleId(userId, articleId);
        return CommonResult.success(praise);
    }
    //获取文章点赞数
    @GetMapping(value = "/praise/{articleId}")
    public CommonResult getPraiseNum(@PathVariable("articleId") Integer articleId){
        Integer praiseNum = articlePraiseDao.findPraiseNum(articleId);
        return CommonResult.success(praiseNum);
    }


    //查找文章的用户信息
    @GetMapping(value = "/authorInfo/{id}")
    public CommonResult authorInfo(@PathVariable("id") Integer id){

        return CommonResult.success(userDao.findUserByArticleId(id));
    }
    //评论数量
    @GetMapping(value = "/commentNum")
    public CommonResult articleCommentNum(Integer articleId){
        Integer integer = articleCommentDao.findCount(articleId);
        return CommonResult.success(integer);
    }
    //新增评论
    @PostMapping(value = "/firstComment")
    public CommonResult articleFirstComment(@RequestBody ArticleComment articleComment){
        Integer integer = articleCommentDao.insertComment(articleComment);
        return CommonResult.success(integer);
    }
    //评论详情
    @GetMapping(value = "/comments/{id}")
    public CommonResult articleComments(@PathVariable("id") Integer id){
        List<ArticleComment> comments = articleCommentDao.getComments(id);
//        List<ArticleComment> articleComments = articleService.buildTree(comments, comments.get(0).getCommentId());
        Map<Integer,ArticleComment> map = new HashMap<>();
        List<ArticleComment> res = new ArrayList<>();
        List<ArticleComment> child = new ArrayList<>();
        for (ArticleComment comment : comments){
            if(comment.getParentId() == null){
                res.add(comment);
            }
            map.put(comment.getCommentId(),comment);
        }
        for (ArticleComment comment : comments){
            if(comment.getParentId() != null){
                ArticleComment parent = map.get(comment.getParentId());
                if(parent.getChild() == null){
                    child = new ArrayList<>();
                }else{
                    child = parent.getChild();
                }
                    child.add(comment);
                parent.setChild(child);
            }
        }
        return CommonResult.success(res);
    }

//    文章详情
    @GetMapping(value = "info/{id}")
    public CommonResult articleInfo(@PathVariable("id") Integer id){
        ArticleResponse article = articleDao.findById(id);
        Integer  lookNum = Integer.valueOf(article.getIsLook()) + 1;
        articleDao.updateArticleById(id, lookNum);
        return CommonResult.success(articleDao.findById(id));
    }
    //按类别查找文章
    @GetMapping(value = "/findAll/{classId}")
    public  CommonResult articleFindAll(@PathVariable("classId") Integer classId){
        List<ArticleResponse> article = null;
        if(classId == 0){
            article = articleDao.findAllArticle();
        }else{
            article = articleDao.findAllByClassId(classId);
        }
        return CommonResult.success(article);
    }
//    文章上传
    @PostMapping(value = "/up")
    public CommonResult articleUp(@RequestBody Article article){
        return CommonResult.success(articleDao.insertArticle(article));
    }

//    封面图片上传
    @PostMapping(value = "/upFile")
    public CommonResult picUp(@RequestParam MultipartFile file) throws IOException {
        Files files = filesService.upload(file);
        return CommonResult.success(filesService.upload(file).getId());
    }

//    获取文章分类
    @GetMapping("/class")
    public CommonResult articleClass(){
        return CommonResult.success(articleClassDao.findAll());
    }
}
