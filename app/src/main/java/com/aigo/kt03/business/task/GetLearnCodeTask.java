package com.aigo.kt03.business.task;

import com.aigo.kt03.business.obj.net.NetGetLearnCodeObject;
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
public class GetLearnCodeTask extends SafeAsyncTask<NetGetLearnCodeObject> {
    @Override
    public NetGetLearnCodeObject call() throws Exception {

        StringBuffer url = new StringBuffer(Constant.URL_KT03+"/ir/getLearnCode.json");
        Future<String> future = AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(url.toString()), null);
        String value = future.get(Constant.TIME_OUT, TimeUnit.MILLISECONDS);

        return new Gson().fromJson(value, NetGetLearnCodeObject.class);
    }

}

