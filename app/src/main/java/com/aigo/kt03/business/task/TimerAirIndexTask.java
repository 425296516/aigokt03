package com.aigo.kt03.business.task;

import com.aigo.kt03.business.KT03Adapter;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.SDKModule;
import com.aigo.kt03.business.obj.net.NetAirIndexObject;
import com.aigo.kt03.business.obj.ui.AirIndex;

import java.util.TimerTask;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class TimerAirIndexTask extends TimerTask {


    private KT03Module.OnPostListener mListener;

    public TimerAirIndexTask(KT03Module.OnPostListener listener){
        mListener = listener;
    }

    @Override
    public void run() {

        GetAirIndexTask task = new GetAirIndexTask() {

            @Override
            protected void onSuccess(NetAirIndexObject o) throws Exception {

                AirIndex airIndex = KT03Adapter.getInstance().getAirIndex(o);
                mListener.onSuccess(airIndex);

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
