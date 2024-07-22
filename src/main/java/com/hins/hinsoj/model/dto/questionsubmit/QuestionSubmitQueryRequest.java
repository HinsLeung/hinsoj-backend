package com.hins.hinsoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/lihins">程序员鱼皮</a>
 * @from <a href="https://hins.icu">编程导航知识星球</a>
 */
@Data
public class QuestionSubmitQueryRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;


    private static final long serialVersionUID = 1L;
}