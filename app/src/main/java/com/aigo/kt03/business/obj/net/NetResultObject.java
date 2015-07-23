package com.aigo.kt03.business.obj.net;

/**
 * Created by zhangcirui on 15/7/15.
 */
public class NetResultObject {
    
    private NetResult result;

    public NetResult getResult() {
        return result;
    }

    public void setResult(NetResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "result=" + result +
                '}';
    }
}
