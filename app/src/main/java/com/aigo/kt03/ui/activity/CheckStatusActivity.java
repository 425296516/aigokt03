package com.aigo.kt03.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.SDKModule;
import com.aigo.kt03.business.db.obj.DeviceInfoObject;

public class CheckStatusActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = CheckStatusActivity.class.getSimpleName();
    private Button mOpenStatus;
    private Button mCloseStatus;
    private String mCode;
    private String mDeviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);

        mCode = getIntent().getStringExtra("code");
        mDeviceType = getIntent().getStringExtra("deviceType");

        mOpenStatus = (Button) findViewById(R.id.btn_open_status);
        mCloseStatus = (Button) findViewById(R.id.btn_close_status);

        mOpenStatus.setOnClickListener(this);
        mCloseStatus.setOnClickListener(this);

        //可以删除
        if(mDeviceType.equals("air_conditioner")){
           DeviceInfoObject deviceInfoObject = KT03Module.getInstance().getDeviceInfoObject();
            if(deviceInfoObject != null){
                mCloseStatus.setVisibility(View.VISIBLE);
                mOpenStatus.setVisibility(View.GONE);
            }else{
                mCloseStatus.setVisibility(View.GONE);
                mOpenStatus.setVisibility(View.VISIBLE);
            }
        }else{
            mCloseStatus.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_open_status:
                final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
                exitDialog.show();
                final Window window = exitDialog.getWindow();
                window.setContentView(R.layout.dlg_checkout_status_open);
                Button ok = (Button) window.findViewById(R.id.btn_user_home_makesure);
                Button cancel = (Button) window.findViewById(R.id.btn_user_home_cancel);

                KT03Module.getInstance().sendIRCode(mCode, new KT03Module.OnPostListener<Integer>() {
                    @Override
                    public void onSuccess(Integer result) {
                        Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, result + "");
                    }

                    @Override
                    public void onFailure(String err) {
                        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, err);
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Intent broadCastIntent = new Intent("finish_last_activity");
                        sendBroadcast(broadCastIntent);
                        Intent intent = new Intent(getApplicationContext(), LearnFinishActivity.class);
                        intent.putExtra("status", true);
                        intent.putExtra("code", mCode);
                        intent.putExtra("deviceType", mDeviceType);
                        startActivity(intent);
                        finish();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                });

                break;

            case R.id.btn_close_status:

                final AlertDialog exitDialog2 = new AlertDialog.Builder(this).create();
                exitDialog2.show();
                final Window window2 = exitDialog2.getWindow();
                window2.setContentView(R.layout.dlg_checkout_status_close);
                Button ok2 = (Button) window2.findViewById(R.id.btn_user_home_makesure);
                Button cancel2 = (Button) window2.findViewById(R.id.btn_user_home_cancel);

                KT03Module.getInstance().sendIRCode(mCode, new KT03Module.OnPostListener<Integer>() {
                    @Override
                    public void onSuccess(Integer result) {
                        Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, result + "");
                    }

                    @Override
                    public void onFailure(String err) {
                        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, err);
                    }
                });

                ok2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog2.dismiss();
                        Intent broadCastIntent = new Intent("finish_last_activity");
                        sendBroadcast(broadCastIntent);
                        Intent intent = new Intent(getApplicationContext(), LearnFinishActivity.class);
                        intent.putExtra("status", false);
                        intent.putExtra("code", mCode);
                        intent.putExtra("deviceType", mDeviceType);
                        startActivity(intent);
                        finish();
                    }
                });

                cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog2.dismiss();
                    }
                });

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_check_status, menu);
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
