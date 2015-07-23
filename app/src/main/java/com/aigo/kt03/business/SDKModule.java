package com.aigo.kt03.business;

import android.os.Looper;
import android.util.Log;

import com.aigo.kt03.business.obj.net.NetGetLearnCodeObject;
import com.aigo.kt03.business.obj.net.NetResultObject;
import com.aigo.kt03.business.obj.ui.LearnCodeObject;
import com.aigo.kt03.business.task.GetLearnCodeTask;
import com.aigo.kt03.business.task.SendIRCodeTask;
import com.aigo.kt03.business.task.SetTimeTask;
import com.aigo.kt03.business.task.StartLearnTask;
import com.aigo.kt03.business.task.StopLearnTask;
import com.aigo.kt03.business.task.TimerAirIndexTask;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by zhangcirui on 15/7/14.
 */
public class SDKModule {

 /*   private static final String TAG = SDKModule.class.getSimpleName();
    private static SDKModule module;

    public interface OnPostListener<T> {
        public void onSuccess(T result);

        public void onFailure(String err);
    }


    public static SDKModule getInstance() {
        if (module == null) {
            module = new SDKModule();
        }
        return module;
    }*/


   /* public void setTime(long time, final OnPostListener listener) {

        SetTimeTask task = new SetTimeTask(time) {

            @Override
            protected void onSuccess(NetResultObject result) throws Exception {
                listener.onSuccess(result);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                listener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();

    }

    private Timer timer;

    public void getAirIndex(final OnPostListener listener) {
        timer = new Timer();
        TimerAirIndexTask timerTask = new TimerAirIndexTask(listener);

        timer.schedule(timerTask, 0, 10000);
    }


    public void getLearnCode(final OnPostListener listener) {

        GetLearnCodeTask task = new GetLearnCodeTask() {
            @Override
            protected void onSuccess(NetGetLearnCodeObject o) throws Exception {

                LearnCodeObject learnCodeObject = KT03Adapter.getInstance().getLearnCodeObject(o);
                listener.onSuccess(learnCodeObject);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                listener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();
    }


    public void setLearnModel(final OnPostListener listener) {
       // mTime = 0;
        StartLearnTask task = new StartLearnTask() {

            @Override
            protected void onSuccess(NetResultObject result) throws Exception {

                timer = new Timer();
                NewTimerTask timerTask = new NewTimerTask(new OnPostListener<LearnCodeObject>() {
                    @Override
                    public void onSuccess(final LearnCodeObject result) {

                        SDKModule.getInstance().stopLearn(new SDKModule.OnPostListener<Integer>() {
                            @Override
                            public void onSuccess(Integer ret) {
                                listener.onSuccess(result.getCode());
                            }

                            @Override
                            public void onFailure(String err) {
                                listener.onFailure(err);
                            }
                        });
                    }

                    @Override
                    public void onFailure(String err) {
                        Log.d(TAG,err);
                        Looper.prepare();
                        listener.onFailure(err);
                        Looper.loop();
                    }
                });
                //程序运行后立刻执行任务，每隔100ms执行一次
                timer.schedule(timerTask, 0, 5000);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                listener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();
    }




    public class NewTimerTask extends TimerTask {
        private int mTime = 0;
        private OnPostListener mListener;

        public NewTimerTask(OnPostListener listener) {
            mListener = listener;
        }

        @Override
        public void run() {
            if (mTime < 12) {
                mTime++;
                Log.d(TAG, "time=" + mTime + "");
                SDKModule.getInstance().getLearnCode(new SDKModule.OnPostListener<LearnCodeObject>() {
                    @Override
                    public void onSuccess(final LearnCodeObject result) {

                        if (result.getResult().getRet() == 1) {

                        } else if (result.getResult().getRet() == 0) {
                            timer.cancel();
                            mListener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onFailure(String err) {
                        mListener.onFailure(err);
                    }
                });
            } else {
                if (timer != null) {
                    mListener.onFailure("访问超时");
                    timer.cancel();
                }
            }
        }

    }

    public void stopLearn(final OnPostListener listener) {


        StopLearnTask task = new StopLearnTask() {

            @Override
            protected void onSuccess(NetResultObject o) throws Exception {

                listener.onSuccess(o.getResult().getRet());
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                listener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();
    }

    public void sendIRCode(String code, final OnPostListener listener) {

        SendIRCodeTask task = new SendIRCodeTask(code) {

            @Override
            protected void onSuccess(NetResultObject o) throws Exception {

                listener.onSuccess(o.getResult().getRet());
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                listener.onFailure(e.toString());
                super.onException(e);
            }
        };
        task.execute();
    }*/

}
