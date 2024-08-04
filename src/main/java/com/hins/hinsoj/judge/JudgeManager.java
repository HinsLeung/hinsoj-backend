package com.hins.hinsoj.judge;

import com.hins.hinsoj.judge.strategy.DefaultJudgeStrategy;
import com.hins.hinsoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.hins.hinsoj.judge.strategy.JudgeContext;
import com.hins.hinsoj.judge.strategy.JudgeStrategy;
import com.hins.hinsoj.judge.codesandbox.model.JudgeInfo;
import com.hins.hinsoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(language.equals("java")){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
