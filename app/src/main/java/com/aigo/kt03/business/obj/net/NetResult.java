package com.aigo.kt03.business.obj.net;

/**
 * Created by zhangcirui on 15/7/15.
 */
public class NetResult {

    private int ret;
    private String info;


    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ret='" + ret + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
