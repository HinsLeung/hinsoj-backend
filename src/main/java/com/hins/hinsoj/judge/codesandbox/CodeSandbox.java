package com.hins.hinsoj.judge.codesandbox;

import com.hins.hinsoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.hins.hinsoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
