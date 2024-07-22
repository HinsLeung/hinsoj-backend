package com.hins.hinsoj.service;

import com.hins.hinsoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hins.hinsoj.model.entity.User;
import com.hins.hinsoj.model.dto.questionsubmit.QuestionSubmitAddRequest;

/**
* @author 86134
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-07-21 13:46:27
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
