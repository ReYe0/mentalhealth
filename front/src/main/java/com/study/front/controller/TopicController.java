package com.study.front.controller;

import com.study.front.common.CommonResult;
import com.study.front.dao.TopicDao;
import com.study.front.dao.TopicReplyCommentDao;
import com.study.front.dao.TopicReplyDao;
import com.study.front.entities.*;
import org.springframework.transaction.annotation.Transactional;
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
 * @Date: 2022/4/6 12:54
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private TopicDao topicDao;
    @Resource
    private TopicReplyDao topicReplyDao;
    @Resource
    private TopicReplyCommentDao topicReplyCommentDao;

    //获取公告内容
    @GetMapping(value = "/getNotice")
    public CommonResult getNotice(){
        return CommonResult.success(topicDao.getNotice());
    }
    //查找心理咨询师回答的话题
    @GetMapping(value = "/getStaffReplyList")
    public CommonResult getStaffReplyList(Integer staffId){
        return CommonResult.success(topicDao.getStaffReplyList(staffId));
    }
    //查找某个用户收藏的全部话题
    @GetMapping(value = "/userTopicCollectList")
    public CommonResult userTopicCollectList(Integer userId){
        List<Topic> topicList = topicDao.userTopicCollectList(userId);
        List<ArticleClass> topicClass = topicDao.findAllTopicClass();

        for (int i = 0; i < topicList.size(); i++) {
            for (int j = 0; j < topicClass.size(); j++) {
                if(topicList.get(i).getId() == topicClass.get(j).getId()){
                    List<ArticleClass> flag = topicList.get(i).getArticleClass();
                    if(flag == null){
                        flag = new ArrayList<>();
                    }
                    flag.add(topicClass.get(j));
                    topicList.get(i).setArticleClass(flag);
                }
            }
        }
        return CommonResult.success(topicList);
    }

    //查找某个用户的全部话题
    @GetMapping(value = "/userTopicList")
    public CommonResult userTopicList(Integer userId){
        List<Topic> topicList = topicDao.userTopicList(userId);
        List<ArticleClass> topicClass = topicDao.findAllTopicClass();

        for (int i = 0; i < topicList.size(); i++) {
            for (int j = 0; j < topicClass.size(); j++) {
                if(topicList.get(i).getId() == topicClass.get(j).getId()){
                    List<ArticleClass> flag = topicList.get(i).getArticleClass();
                    if(flag == null){
                        flag = new ArrayList<>();
                    }
                    flag.add(topicClass.get(j));
                    topicList.get(i).setArticleClass(flag);
                }
            }
        }
        return CommonResult.success(topicList);
    }

    //用户回答列表
    @GetMapping(value = "/getUserReply")
    public CommonResult getUserReply(Integer userId){
        return CommonResult.success(topicDao.userReplyList(userId));
    }
    //加载lookNum+1
    @GetMapping(value = "/lookNum")
    @Transactional
    public CommonResult getLookNum(Integer topicId){
        Integer lookNum = topicDao.loadTopicLookNum(topicId);
        Integer integer = topicDao.updateLookNum(lookNum + 1,topicId);
        return CommonResult.success(lookNum+1);
    }
    //获取用户的提问数
    @GetMapping(value = "/totalTopicNum")
    public CommonResult totalTopicNum(Integer userId){
        return CommonResult.success(topicDao.totalTopicNum(userId));
    }
    //获取用户的回答数
    @GetMapping(value = "/totalReplyNum")
    public CommonResult totalReplyNum(Integer userId){
        return CommonResult.success(topicDao.totalReplyNum(userId));
    }
    //获取登陆用户的话题回复获赞总数
    @GetMapping(value = "/totalTopicPraiseNum")
    public CommonResult totalTopicPraiseNum(Integer userId){
        return CommonResult.success(topicDao.totalTopicReplyPraiseNum(userId));
    }
    //新增点赞
    @PostMapping(value = "/toPraise")
    public CommonResult toPraise(Integer topicReplyId,Integer userId){
        return CommonResult.success(topicDao.toPraise(topicReplyId,userId));
    }
    //删除点赞
    @DeleteMapping(value = "/delPraise")
    public CommonResult delPraise(Integer topicReplyId,Integer userId){
        return CommonResult.success(topicDao.delPraise(topicReplyId,userId));
    }
    //是否收藏这个问题
    @GetMapping(value = "/judgeIsCollect")
    public CommonResult judgeIsCollect(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.judgeIsCollect(topicId,userId));
    }
    //这个问题的收藏数量
    @GetMapping(value = "/collectNum")
    public CommonResult collectNum(Integer topicId){
        return CommonResult.success(topicDao.collectNum(topicId));
    }
    //删除收藏
    @DeleteMapping(value = "/delCollect")
    public CommonResult delCollect(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.delCollect(topicId,userId));
    }
    //新增收藏
    @PostMapping(value = "/toCollect")
    public CommonResult toCollect(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.toCollect(topicId,userId));
    }

    //是否抱过这个问题
    @GetMapping(value = "/judgeIsHug")
    public CommonResult judgeIsHug(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.judgeIsHug(topicId,userId));
    }

    //这个问题的抱抱数量
    @GetMapping(value = "/hugNum")
    public CommonResult hugNum(Integer topicId){
        return CommonResult.success(topicDao.hugNum(topicId));
    }
    //删除抱抱
    @DeleteMapping(value = "/delHug")
    public CommonResult delHug(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.delHug(topicId,userId));
    }

    //新增抱抱
    @PostMapping(value = "/toHug")
    public CommonResult toHug(Integer topicId,Integer userId){
        return CommonResult.success(topicDao.toHug(topicId,userId));
    }
    //搜索话题
    @GetMapping(value = "/findTopicByTitle")
    public CommonResult findTopicByTitle(String topicTitle){
        List<Topic> topicList = topicDao.findTopicByTitle(topicTitle);
        List<ArticleClass> topicClass = topicDao.findAllTopicClass();

        for (int i = 0; i < topicList.size(); i++) {
            for (int j = 0; j < topicClass.size(); j++) {
                if(topicList.get(i).getId() == topicClass.get(j).getId()){
                    List<ArticleClass> flag = topicList.get(i).getArticleClass();
                    if(flag == null){
                        flag = new ArrayList<>();
                    }
                    flag.add(topicClass.get(j));
                    topicList.get(i).setArticleClass(flag);
                }
            }
        }
        return CommonResult.success(topicList);
    }
    //查找全部话题
    @GetMapping(value = "/findAllByReplyCount")
    public CommonResult findAllByReplyCount(){
        List<Topic> topicList = topicDao.findAllByReplyCount();
        List<ArticleClass> topicClass = topicDao.findAllTopicClass();

        for (int i = 0; i < topicList.size(); i++) {
            for (int j = 0; j < topicClass.size(); j++) {
                if(topicList.get(i).getId() == topicClass.get(j).getId()){
                    List<ArticleClass> flag = topicList.get(i).getArticleClass();
                    if(flag == null){
                        flag = new ArrayList<>();
                    }
                    flag.add(topicClass.get(j));
                    topicList.get(i).setArticleClass(flag);
                }
            }
        }
        return CommonResult.success(topicList);
    }
    //查找全部话题 通过时间降序
    @GetMapping(value = "/findAll")
    public CommonResult findAll(){
        List<Topic> topicList = topicDao.findAll();
        List<ArticleClass> topicClass = topicDao.findAllTopicClass();

        for (int i = 0; i < topicList.size(); i++) {
            for (int j = 0; j < topicClass.size(); j++) {
                if(topicList.get(i).getId() == topicClass.get(j).getId()){
                    List<ArticleClass> flag = topicList.get(i).getArticleClass();
                    if(flag == null){
                        flag = new ArrayList<>();
                    }
                    flag.add(topicClass.get(j));
                    topicList.get(i).setArticleClass(flag);
                }
            }
        }
        return CommonResult.success(topicList);
    }
    //话题回复评论列表
    @GetMapping(value = "/replyCommentInfo/{topicId}")
    public CommonResult replyCommentList(@PathVariable("topicId") Integer topicId){
        List<TopicReplyComment> comments = topicReplyCommentDao.findAllByTopicReplyId(topicId);
        Map<Integer, TopicReplyComment> map = new HashMap<>();
        List<TopicReplyComment> res = new ArrayList<>();
        List<TopicReplyComment> child = new ArrayList<>();
        for (TopicReplyComment comment : comments){
            if(comment.getParentId() == null){
                res.add(comment);
            }
            map.put(comment.getId(),comment);
        }
        for (TopicReplyComment comment : comments){
            if(comment.getParentId() != null){
                TopicReplyComment parent = map.get(comment.getParentId());
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

    //评论话题回复
    @PostMapping(value = "/replyCommentSubmit")
    public CommonResult replyTopic(@RequestBody TopicReplyComment topicReplyComment){
        return CommonResult.success(topicReplyCommentDao.insertReplyComment(topicReplyComment));
    }

    //获取回复信息
    @GetMapping(value = "/replyInfo")
    public CommonResult replyTopicInfo(Integer topicId,Integer userId){
        return CommonResult.success(topicReplyDao.findAllReply(topicId,userId));
    }

    //回复话题
    @PostMapping(value = "/reply")
    public CommonResult replyTopic(@RequestBody TopicReply topicReply){
        return CommonResult.success(topicReplyDao.insertReply(topicReply));
    }
    //获取话题类别信息
    @GetMapping(value = "/infoClass/{topicId}")
    public CommonResult infoClass(@PathVariable("topicId") Integer topicId){
        return CommonResult.success(topicDao.findClassByTopicId(topicId));
    }

    //获取话题信息
    @GetMapping(value = "/info/{topicId}")
    public CommonResult getTopicInfo(@PathVariable("topicId") Integer topicId){
        return CommonResult.success(topicDao.findTopicById(topicId));
    }

    //话题新增
    @PostMapping(value = "/add")
    @Transactional
    public CommonResult topicAdd(@RequestBody Topic topic){
        Integer integer = topicDao.insertTopic(topic);
        Integer id = topic.getId();
        List<ArticleClass> articleClass = topic.getArticleClass();
        int size = articleClass.size();
        Integer integer1=null;
        for (int i = 0; i < size; i++) {

            integer1 = topicDao.insertClass(id, articleClass.get(i).getId());
        }
        if(integer1 > 0){
            return CommonResult.success(id);

        }else {
            return CommonResult.error();
        }
    }
}
