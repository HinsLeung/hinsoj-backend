package com.hins.hinsoj.judge.codesandbox.impl;

import com.hins.hinsoj.judge.codesandbox.CodeSandbox;
import com.hins.hinsoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.hins.hinsoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.hins.hinsoj.judge.codesandbox.model.JudgeInfo;
import com.hins.hinsoj.model.enums.JudgeInfoMessageEnum;
import com.hins.hinsoj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 示例代码沙箱
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);
        System.out.println("示例代码沙箱");
        return executeCodeResponse;
    }
}
