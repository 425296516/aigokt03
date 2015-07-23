package com.aigo.kt03.business.obj.net;

/**
 * Created by zhangcirui on 15/7/15.
 */
public class NetGetLearnCodeObject {

    private NetResult result;

    private String code;


    public NetResult getResult() {
        return result;
    }

    public void setResult(NetResult result) {
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
