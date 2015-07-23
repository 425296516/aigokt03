package com.aigo.kt03.business.obj.ui;

import com.aigo.kt03.business.obj.net.NetResult;

/**
 * Created by zhangcirui on 15/7/22.
 */
public class KT03DeviceInfo {

    private Result result;
    private String ip;
    private String sn;
    private String version;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "NetKT03DeviceInfo{" +
                "result=" + result +
                ", ip='" + ip + '\'' +
                ", sn='" + sn + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

