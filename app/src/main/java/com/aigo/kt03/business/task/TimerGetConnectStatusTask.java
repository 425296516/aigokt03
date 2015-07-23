package com.aigo.kt03.business.task;

import com.aigo.kt03.business.KT03Adapter;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.SDKModule;
import com.aigo.kt03.business.obj.net.NetGetLearnCodeObject;
import com.aigo.kt03.business.obj.net.NetKT03DeviceInfo;
import com.aigo.kt03.business.obj.ui.KT03DeviceInfo;
import com.aigo.kt03.business.obj.ui.LearnCodeObject;

import java.util.TimerTask;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class TimerGetConnectStatusTask extends TimerTask {


    private KT03Module.OnPostListener mListener;

    public TimerGetConnectStatusTask(KT03Module.OnPostListener listener){
        mListener = listener;
    }

    @Override
    public void run() {

        GetConnectStatusTask task = new GetConnectStatusTask() {

            @Override
            protected void onSuccess(NetKT03DeviceInfo o) throws Exception {

                KT03DeviceInfo kt03DeviceInfo =  KT03Adapter.getInstance().getKT03DeviceInfo(o);
                mListener.onSuccess(kt03DeviceInfo);

            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                mListener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();

    }

}
