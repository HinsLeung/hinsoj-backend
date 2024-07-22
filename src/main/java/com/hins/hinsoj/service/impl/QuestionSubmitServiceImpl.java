package com.hins.hinsoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hins.hinsoj.common.ErrorCode;
import com.hins.hinsoj.exception.BusinessException;
import com.hins.hinsoj.mapper.QuestionSubmitMapper;
import com.hins.hinsoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hins.hinsoj.model.entity.Question;
import com.hins.hinsoj.model.entity.QuestionSubmit;
import com.hins.hinsoj.model.entity.User;
import com.hins.hinsoj.model.enums.QuestionSubmitLanguageEnum;
import com.hins.hinsoj.model.enums.QuestionSubmitStatusEnum;
import com.hins.hinsoj.service.QuestionService;
import com.hins.hinsoj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86134
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-07-21 13:46:27
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if(languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        //设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");

        boolean save = this.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }

}




