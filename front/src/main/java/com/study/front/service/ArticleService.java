package com.study.front.service;

import com.study.front.entities.ArticleComment;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/20 17:01
 */
public interface ArticleService {
    public List<ArticleComment> buildTree(List<ArticleComment> commentList,int pid);
}
