package com.aigo.kt03.business;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.aigo.kt03.business.db.DbDeviceInfo;
import com.aigo.kt03.business.db.obj.DbDeviceInfoObject;
import com.aigo.kt03.business.db.obj.DeviceInfoObject;
import com.aigo.kt03.business.obj.net.NetGetLearnCodeObject;
import com.aigo.kt03.business.obj.net.NetResultObject;
import com.aigo.kt03.business.obj.ui.AirIndex;
import com.aigo.kt03.business.obj.ui.KT03DeviceInfo;
import com.aigo.kt03.business.obj.ui.LearnCodeObject;
import com.aigo.kt03.business.obj.ui.ResultObject;
import com.aigo.kt03.business.task.GetLearnCodeTask;
import com.aigo.kt03.business.task.SearchKT03Task;
import com.aigo.kt03.business.task.SendIRCodeTask;
import com.aigo.kt03.business.task.SetTimeTask;
import com.aigo.kt03.business.task.SetWifiApInfoTask;
import com.aigo.kt03.business.task.StartLearnTask;
import com.aigo.kt03.business.task.StopLearnTask;
import com.aigo.kt03.business.task.TimerAirIndexTask;
import com.aigo.kt03.business.task.TimerGetConnectStatusTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangcirui on 15/7/17.
 */
public class KT03Module {

    private static final String TAG = KT03Module.class.getSimpleName();
    private static KT03Module module;
    private OnPostListener mListener;
    private Context mContext;
    private Timer timer;
    private AirIndex mAirIndex = null;
    private DeviceInfoObject mDeviceInfoObject;

    public interface OnPostListener<T> {
        public void onSuccess(T result);

        public void onFailure(String err);
    }

    public void init(Context context) {
        mContext = context;
    }

    public static KT03Module getInstance() {
        if (module == null) {
            module = new KT03Module();
        }
        return module;
    }

    public void searchKT03(final OnPostListener<ResultObject> listener) {

        SearchKT03Task task = new SearchKT03Task() {

            @Override
            protected void onSuccess(NetResultObject netResultObject) throws Exception {

                ResultObject resultObject = KT03Adapter.getInstance().getResultObject(netResultObject);
                listener.onSuccess(resultObject);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                listener.onFailure(e.toString());
            }
        };
        task.execute();
    }


    public void setWifiApInfo(String wifiName, String password, final OnPostListener<ResultObject> listener) {

        SetWifiApInfoTask task = new SetWifiApInfoTask(wifiName, password) {

            @Override
            protected void onSuccess(NetResultObject netResultObject) throws Exception {

                ResultObject resultObject = KT03Adapter.getInstance().getResultObject(netResultObject);
                listener.onSuccess(resultObject);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                listener.onFailure(e.toString());
            }
        };

        task.execute();

    }

    public void getConnectStatus(final OnPostListener<KT03DeviceInfo> listener) {

        timer = new Timer();
        TimerGetConnectStatusTask timerTask = new TimerGetConnectStatusTask(listener);
        timer.schedule(timerTask, 0, 3000);

    }

    public void getKT03AirInfo(final OnPostListener listener) {

        if (mAirIndex != null) {
            listener.onSuccess(mAirIndex);

            KT03Module.getInstance().getAirIndex(new KT03Module.OnPostListener<AirIndex>() {
                @Override
                public void onSuccess(AirIndex airIndex) {
                    mAirIndex = airIndex;
                    if (!mAirIndex.toString().equals(airIndex.toString())) {
                        Log.d(TAG, "11111");
                        mListener.onSuccess(airIndex);
                    } else {
                        Log.d(TAG, "222222");
                        listener.onSuccess(airIndex);
                    }
                }

                @Override
                public void onFailure(String err) {

                }
            });

        } else {

            KT03Module.getInstance().getAirIndex(new KT03Module.OnPostListener<AirIndex>() {
                @Override
                public void onSuccess(AirIndex airIndex) {
                    mAirIndex = airIndex;
                    if (!mAirIndex.toString().equals(airIndex.toString())) {
                        Log.d(TAG, "33333");
                        mListener.onSuccess(airIndex);
                    } else {
                        Log.d(TAG, "44444");
                        listener.onSuccess(airIndex);
                    }
                }

                @Override
                public void onFailure(String err) {
                        listener.onFailure(err);
                }
            });
        }
    }


    public void setDataChangedListener(OnPostListener listener) {
        mListener = listener;
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

                        KT03Module.getInstance().stopLearn(new KT03Module.OnPostListener<Integer>() {
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
                        Log.d(TAG, err);
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

    public int addDeviceInfo(DeviceInfoObject deviceInfoObject) {

        DbDeviceInfoObject dbDeviceInfoObject = KT03Adapter.getInstance().getDbDeviceInfoObject(deviceInfoObject);

        DbDeviceInfo dbDeviceInfo = new DbDeviceInfo(mContext);
        int pos = dbDeviceInfo.insert(dbDeviceInfoObject);
        Log.d(TAG, "addDeviceInfo" + dbDeviceInfoObject);
        return pos;

    }

    public List<DeviceInfoObject> getDeviceInfo() {

        List<DbDeviceInfoObject> list = new DbDeviceInfo(mContext).queryAll();
        List<DeviceInfoObject> deviceList = new ArrayList<DeviceInfoObject>();
        for (DbDeviceInfoObject dbDeviceInfoObject : list) {
            DeviceInfoObject deviceInfoObject = KT03Adapter.getInstance().getDeviceInfoObject(dbDeviceInfoObject);
            deviceList.add(deviceInfoObject);
        }

        return deviceList;

    }

    public void setDeviceInfoObject(DeviceInfoObject deviceInfoObject) {

        mDeviceInfoObject = deviceInfoObject;

    }

    public DeviceInfoObject getDeviceInfoObject() {

        return mDeviceInfoObject;
    }



    public void updateDeviceInfo(String id, String deviceName) {
        //DbDeviceInfoObject dbDeviceInfoObject = AirIndexAdapter.getInstance().getDbDeviceInfoObject(deviceInfoObject);
        new DbDeviceInfo(mContext).update(id, deviceName);
    }

    public void setStatus(String id, boolean bol) {
        //DbDeviceInfoObject dbDeviceInfoObject = AirIndexAdapter.getInstance().getDbDeviceInfoObject(deviceInfoObject);
        String status = null;
        if (bol) {
            status = "1";
        } else {
            status = "0";
        }
        new DbDeviceInfo(mContext).setStatus(id, status);

    }


    public boolean deleteDeviceInfo(String id) {

        boolean bol = new DbDeviceInfo(mContext).deleteById(id);

        return bol;
    }

    public String getDeviceName(DeviceInfoObject deviceInfo) {

        DbDeviceInfoObject dbDeviceInfoObject = KT03Adapter.getInstance().getDbDeviceInfoObject(deviceInfo);

        int deviceNum;
        List<Integer> listInteger;

        List<DeviceInfoObject> list = getDeviceInfo();

        if (dbDeviceInfoObject.getDeviceType().equals("air_conditioner")) {
            listInteger = new ArrayList<Integer>();

            if (list.size() == 0) {
                return "我的空调";
            } else {
                for (DeviceInfoObject deviceInfoObject : list) {
                    String name = deviceInfoObject.getDeviceName();
                    if (name.contains("我的空调")) {
                        String[] num = name.split("我的空调");
                        if (num.length == 0) {
                            deviceNum = 0;
                            listInteger.add(deviceNum);
                        } else {
                            deviceNum = Integer.parseInt(num[1]);
                            listInteger.add(deviceNum);
                        }
                    }
                }
                if (listInteger.size() == 0) {
                    return "我的空调";
                } else {
                    int max = Collections.max(listInteger);
                    return "我的空调" + (max + 1);
                }
            }

        } else if (dbDeviceInfoObject.getDeviceType().equals("purifier")) {
            listInteger = new ArrayList<Integer>();

            if (list.size() == 0) {
                return "我的净化器";
            } else {
                for (DeviceInfoObject deviceInfoObject : list) {
                    String name = deviceInfoObject.getDeviceName();
                    if (name.contains("我的净化器")) {
                        String[] num = name.split("我的净化器");
                        if (num.length == 0) {
                            deviceNum = 0;
                            listInteger.add(deviceNum);
                        } else {
                            deviceNum = Integer.parseInt(num[1]);
                            listInteger.add(deviceNum);
                        }
                    }
                }
                if (listInteger.size() == 0) {
                    return "我的净化器";
                } else {
                    int max = Collections.max(listInteger);
                    return "我的净化器" + (max + 1);
                }
            }

        } else if (dbDeviceInfoObject.getDeviceType().equals("humidifier")) {
            listInteger = new ArrayList<Integer>();

            if (list.size() == 0) {
                return "我的加湿器";
            } else {
                for (DeviceInfoObject deviceInfoObject : list) {
                    String name = deviceInfoObject.getDeviceName();
                    if (name.contains("我的加湿器")) {
                        String[] num = name.split("我的加湿器");
                        if (num.length == 0) {
                            deviceNum = 0;
                            listInteger.add(deviceNum);
                        } else {
                            deviceNum = Integer.parseInt(num[1]);
                            listInteger.add(deviceNum);
                        }
                    }
                }
                if (listInteger.size() == 0) {
                    return "我的加湿器";
                } else {
                    int max = Collections.max(listInteger);
                    return "我的加湿器" + (max + 1);
                }
            }

        } else {
            return null;
        }
    }



    public DeviceInfoObject getDeviceInfoId(String id) {
        DbDeviceInfoObject dbDeviceInfoObject = new DbDeviceInfo(mContext).queryById(id);
        DeviceInfoObject deviceInfoObject = KT03Adapter.getInstance().getDeviceInfoObject(dbDeviceInfoObject);
        return deviceInfoObject;

    }

    public void setTime(long time, final OnPostListener listener) {

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
                KT03Module.getInstance().getLearnCode(new KT03Module.OnPostListener<LearnCodeObject>() {
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
    }

}
