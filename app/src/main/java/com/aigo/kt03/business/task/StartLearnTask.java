package com.aigo.kt03.business.task;

import com.aigo.kt03.business.obj.net.NetResultObject;
import com.aigo.kt03.business.util.Constant;
import com.aigo.kt03.business.util.SafeAsyncTask;
import com.google.gson.Gson;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * Created by zhangcirui on 15/7/15.
 */
public class StartLearnTask extends SafeAsyncTask<NetResultObject> {

    @Override
    public NetResultObject call() throws Exception {

        StringBuffer url = new StringBuffer(Constant.URL_KT03+"/send/startLearn.json");
        Future<String> future = AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(url.toString()), null);
        String value = future.get(Constant.TIME_OUT, TimeUnit.MILLISECONDS);

        return new Gson().fromJson(value,NetResultObject.class);
    }
}