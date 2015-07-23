package com.aigo.kt03.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.db.obj.DbDeviceInfoObject;
import com.aigo.kt03.business.db.obj.DeviceInfoObject;
import com.aigo.usermodule.business.UserModule;
import com.aigo.usermodule.ui.util.ToastUtil;

import java.util.List;

public class LearnFinishActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = LearnFinishActivity.class.getSimpleName();
    private Button mAddFinish;
    private Button mContinueAdd;
    private EditText mDeviceInfo;
    private String mCode;
    private String mDeviceType;
    private boolean mStatus;
    private TextView mKeyNum;
    private List<DeviceInfoObject> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_finish);

        mCode = getIntent().getStringExtra("code");
        mDeviceType = getIntent().getStringExtra("deviceType");
        mStatus = getIntent().getBooleanExtra("status", false);

        mAddFinish = (Button) findViewById(R.id.btn_add_finish);
        mContinueAdd = (Button) findViewById(R.id.btn_continue_add);
        mDeviceInfo = (EditText) findViewById(R.id.et_device_info);
        mKeyNum = (TextView) findViewById(R.id.tv_keycode_num);

        mAddFinish.setOnClickListener(this);
        mContinueAdd.setOnClickListener(this);

        ToastUtil.rawToast(getApplicationContext(),mCode+""+mDeviceType+""+mStatus);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mDeviceType.equals("purifier") || mDeviceType.equals("humidifier")) {
            mContinueAdd.setVisibility(View.GONE);
        } else if (mDeviceType.equals("air_conditioner")) {
            DeviceInfoObject deviceInfo = KT03Module.getInstance().getDeviceInfoObject();
            if (deviceInfo == null) {
                mContinueAdd.setVisibility(View.VISIBLE);
                mAddFinish.setVisibility(View.GONE);
            } else {
                mContinueAdd.setVisibility(View.GONE);
                mKeyNum.setText("你定义了" + 2 + "个按键");
            }
        }

        mList = KT03Module.getInstance().getDeviceInfo();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add_finish:
                Intent intent = new Intent(LearnFinishActivity.this, NoHouseHoldActivity.class);
                intent.putExtra("code", mCode);

                DeviceInfoObject deviceInfo = KT03Module.getInstance().getDeviceInfoObject();
                if (deviceInfo != null && deviceInfo.getDeviceType().equals("air_conditioner")) {

                    if (mDeviceType.equals("air_conditioner")) {
                        boolean status = deviceInfo.isStatus();
                        if (status) {
                            deviceInfo.setCloseCode(mCode);
                            deviceInfo.setStatus(false);
                        } else {
                            deviceInfo.setStatus(true);
                            deviceInfo.setOpenCode(mCode);
                        }
                    }

                    int i = KT03Module.getInstance().addDeviceInfo(deviceInfo);
                    ToastUtil.rawToast(getApplicationContext(), "" + deviceInfo.toString());
                    Log.d(TAG,deviceInfo.toString());
                    KT03Module.getInstance().setDeviceInfoObject(null);
                } else {
                    if (mDeviceType.equals("purifier")) {
                        DeviceInfoObject dbDeviceInfoObject = new DeviceInfoObject();
                        dbDeviceInfoObject.setDeviceType(mDeviceType);

                        String deviceName = KT03Module.getInstance().getDeviceName(dbDeviceInfoObject);
                        dbDeviceInfoObject.setDeviceName(deviceName);
                        dbDeviceInfoObject.setDeviceInfo(mDeviceInfo.getText().toString().trim());
                        if (mStatus) {
                            dbDeviceInfoObject.setStatus(true);
                            dbDeviceInfoObject.setOpenCode(mCode);
                            dbDeviceInfoObject.setCloseCode(mCode);
                        } else {
                            dbDeviceInfoObject.setStatus(false);
                            dbDeviceInfoObject.setOpenCode(mCode);
                            dbDeviceInfoObject.setCloseCode(mCode);
                        }

                        int i = KT03Module.getInstance().addDeviceInfo(dbDeviceInfoObject);
                        ToastUtil.rawToast(getApplicationContext(), "" + dbDeviceInfoObject.toString());
                        Log.d(TAG,dbDeviceInfoObject.toString());
                    } else if (mDeviceType.equals("humidifier")) {
                        DeviceInfoObject dbDeviceInfoObject = new DeviceInfoObject();
                        dbDeviceInfoObject.setDeviceType(mDeviceType);

                        String deviceName = KT03Module.getInstance().getDeviceName(dbDeviceInfoObject);
                        dbDeviceInfoObject.setDeviceName(deviceName);
                        dbDeviceInfoObject.setDeviceInfo(mDeviceInfo.getText().toString().trim());
                        if (mStatus) {
                            dbDeviceInfoObject.setStatus(true);
                            dbDeviceInfoObject.setOpenCode(mCode);
                            dbDeviceInfoObject.setCloseCode(mCode);
                        } else {
                            dbDeviceInfoObject.setStatus(false);
                            dbDeviceInfoObject.setOpenCode(mCode);
                            dbDeviceInfoObject.setCloseCode(mCode);
                        }

                        int i = KT03Module.getInstance().addDeviceInfo(dbDeviceInfoObject);
                        ToastUtil.rawToast(getApplicationContext(), "" + dbDeviceInfoObject.toString());
                        Log.d(TAG,dbDeviceInfoObject.toString());
                    }
                }
                startActivity(intent);
                finish();

                break;

            case R.id.btn_continue_add:

                Intent intent2 = new Intent(LearnFinishActivity.this, NextAddActivity.class);
                intent2.putExtra("deviceType","air_conditioner");
                DeviceInfoObject deviceInfo2 = new DeviceInfoObject();

                deviceInfo2.setDeviceType(mDeviceType);
                String deviceName = KT03Module.getInstance().getDeviceName(deviceInfo2);
                deviceInfo2.setDeviceName(deviceName);

                deviceInfo2.setDeviceInfo(mDeviceInfo.getText().toString().trim());

                if (mDeviceType.equals("air_conditioner")) {
                    if (mStatus) {
                        deviceInfo2.setStatus(true);
                        deviceInfo2.setOpenCode(mCode);
                        deviceInfo2.setCloseCode("");
                    } else {
                        deviceInfo2.setStatus(false);
                        deviceInfo2.setOpenCode("");
                        deviceInfo2.setCloseCode(mCode);
                    }
                }
                KT03Module.getInstance().setDeviceInfoObject(deviceInfo2);
                startActivity(intent2);
                finish();

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_learn_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
