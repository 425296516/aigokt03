package com.aigo.kt03.business.db.obj;

import com.aigo.kt03.business.obj.ui.KeyCode;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class DbDeviceInfoObject {

    private String id;
    private String deviceType;
    private String deviceName;
    private String deviceInfo;
    private String status;
    private String openCode;
    private String closeCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getCloseCode() {
        return closeCode;
    }

    public void setCloseCode(String closeCode) {
        this.closeCode = closeCode;
    }

    @Override
    public String toString() {
        return "DbDeviceInfoObject{" +
                "id='" + id + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", status='" + status + '\'' +
                ", openCode='" + openCode + '\'' +
                ", closeCode='" + closeCode + '\'' +
                '}';
    }
}
