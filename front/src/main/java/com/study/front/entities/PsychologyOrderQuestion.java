package com.study.front.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 18:00
 */
@Data
public class PsychologyOrderQuestion {
    private Integer id;
    private Integer classId;
    private Integer staffId;
    private String title;
    private Boolean isSale;
    private String isFree;
    private String price;
    private String description;
    private String AScoreTotal;
    private String BScoreTotal;
    private String CScoreTotal;
    private String DScoreTotal;
    private String EScoreTotal;
    private String AResult;
    private String ABResult;
    private String BCResult;
    private String CDResult;
    private String DEResult;
    private Date createTime;
    private Integer fileId;
    private List<PsychologyOrderQuestionSon> sonQuestion;


    private String staffName;
    private String url;
    private String alreadyCheck;
    private String alreadyComment;
    private String checkResult;
    private String name;
}
