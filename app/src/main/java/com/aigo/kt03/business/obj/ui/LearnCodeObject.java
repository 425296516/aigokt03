package com.aigo.kt03.business.obj.ui;

import com.aigo.kt03.business.obj.net.NetResult;

/**
 * Created by zhangcirui on 15/7/17.
 */
public class LearnCodeObject {

    private Result result;

    private String code;


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "GetLearnCodeObject{" +
                "result=" + result +
                ", code='" + code + '\'' +
                '}';
    }
}