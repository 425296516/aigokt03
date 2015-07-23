package com.aigo.kt03.business.obj.net;

/**
 * Created by zhangcirui on 15/7/15.
 */
public class NetAirIndexObject {

    private NetResultObject result;

    private NetAirIndex data;

    private long pubtime;

    public NetResultObject getResult() {
        return result;
    }

    public void setResult(NetResultObject result) {
        this.result = result;
    }

    public NetAirIndex getData() {
        return data;
    }

    public void setData(NetAirIndex data) {
        this.data = data;
    }

    public long getPubtime() {
        return pubtime;
    }

    public void setPubtime(long pubtime) {
        this.pubtime = pubtime;
    }

    @Override
    public String toString() {
        return "AirIndexObject{" +
                "result=" + result +
                ", data=" + data +
                ", pubtime=" + pubtime +
                '}';
    }
}
