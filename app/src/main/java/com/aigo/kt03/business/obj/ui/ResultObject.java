package com.aigo.kt03.business.obj.ui;

import com.aigo.kt03.business.obj.net.NetResult;

/**
 * Created by zhangcirui on 15/7/17.
 */
public class ResultObject {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "result=" + result +
                '}';
    }

}
