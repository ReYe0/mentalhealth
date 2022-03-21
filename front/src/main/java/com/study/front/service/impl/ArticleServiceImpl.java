package com.study.front.service.impl;

import com.study.front.entities.ArticleComment;
import com.study.front.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/20 17:02
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public List<ArticleComment> buildTree(List<ArticleComment> commentList, int pid) {
        List<ArticleComment> treeList = new ArrayList<ArticleComment>();
        for (ArticleComment articleComment : commentList){
            if(articleComment.getParentId() == null){
//                treeList.add(articleComment);
            }else{

            }
            if(articleComment.getParentId() == pid){
                articleComment.setChild(buildTree(commentList,articleComment.getCommentId()));
                treeList.add(articleComment);
            }
        }
        return treeList;
    }
}
