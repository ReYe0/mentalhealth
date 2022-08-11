package com.study.admin.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/23 18:41
 */
@Data
public class PsychologyOrderQuestionSon {
    private Integer id;
    private Integer parentId;
    private String sonTitle;
    private String AOption;
    private String BOption;
    private String COption;
    private String DOption;
    private String EOption;
    private String AScore;
    private String BScore;
    private String CScore;
    private String DScore;
    private String EScore;
    private Date crateTime;
}
