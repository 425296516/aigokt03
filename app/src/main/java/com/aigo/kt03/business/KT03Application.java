package com.aigo.kt03.business;

import android.app.Application;

import com.aigo.usermodule.business.UserModule;


/**
 * Created by zhangcirui on 15/7/18.
 */
public class KT03Application extends Application {


    @Override
    public void onCreate() {

        UserModule.getInstance().init(this);
        UserModule.initKeys("", "", "829540725", "705229ff42e060f857f1d1ad413a8308", "https://api.weibo.com/oauth2/default.html");
        KT03Module.getInstance().init(getApplicationContext());

    }

}
