package com.aigo.kt03.business.task;

import com.aigo.kt03.business.obj.net.NetResultObject;
import com.aigo.kt03.business.util.Constant;
import com.aigo.kt03.business.util.SafeAsyncTask;
import com.google.gson.Gson;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;

import java.util.concurrent.TimeUnit;


/**
 * Created by zhangcirui on 15/7/15.
 */
public class SetTimeTask extends SafeAsyncTask<NetResultObject> {

    private long mTime ;

    public SetTimeTask(long time){
        mTime = time;
    }

    @Override
    public NetResultObject call() throws Exception {

        StringBuffer url = new StringBuffer(Constant.URL_KT03 + "/ir/getLearnCode.json");
        url.append("?timestamp").append(mTime);

        Future<String> future = AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(url.toString()), null);
        String value = future.get(Constant.TIME_OUT, TimeUnit.MILLISECONDS);

        return new Gson().fromJson(value, NetResultObject.class);
    }

}