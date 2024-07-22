package com.hins.hinsoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hins.hinsoj.annotation.AuthCheck;
import com.hins.hinsoj.common.BaseResponse;
import com.hins.hinsoj.common.ErrorCode;
import com.hins.hinsoj.common.ResultUtils;
import com.hins.hinsoj.constant.UserConstant;
import com.hins.hinsoj.exception.BusinessException;
import com.hins.hinsoj.model.dto.question.QuestionQueryRequest;
import com.hins.hinsoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hins.hinsoj.model.entity.Question;
import com.hins.hinsoj.model.entity.User;
import com.hins.hinsoj.service.QuestionSubmitService;
import com.hins.hinsoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author HinsLeung
 * @from 
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录ID
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看非答案、提交代码等非公开信息）
     *
     * @param questionQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Question>> listQuestionSubmitByPage(@RequestBody QuestionQueryRequest questionQueryRequest) {
        long current = questionQueryRequest.getCurrent();
        long size = questionQueryRequest.getPageSize();
        Page<Question> questionPage = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionQueryRequest));
        return ResultUtils.success(questionPage);
    }
}
