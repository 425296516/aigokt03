package com.aigo.kt03.business.db.obj;

import com.aigo.kt03.business.obj.ui.KeyCode;
import com.aigo.usermodule.business.UserModule;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class DeviceInfoObject {

    private String id;
    private String deviceType;
    private String deviceName;
    private String deviceInfo;
    private boolean status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        return "DeviceInfo{" +
                "id='" + id + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", status=" + status +
                ", openCode='" + openCode + '\'' +
                ", closeCode='" + closeCode + '\'' +
                '}';
    }
}
