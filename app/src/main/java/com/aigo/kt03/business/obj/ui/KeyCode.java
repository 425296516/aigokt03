package com.aigo.kt03.business.obj.ui;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class KeyCode {

    private String id;
    private String opendCode;
    private String closeCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpendCode() {
        return opendCode;
    }

    public void setOpendCode(String opendCode) {
        this.opendCode = opendCode;
    }

    public String getCloseCode() {
        return closeCode;
    }

    public void setCloseCode(String closeCode) {
        this.closeCode = closeCode;
    }

    @Override
    public String toString() {
        return "KeyCode{" +
                "id='" + id + '\'' +
                ", opendCode='" + opendCode + '\'' +
                ", closeCode='" + closeCode + '\'' +
                '}';
    }
}
