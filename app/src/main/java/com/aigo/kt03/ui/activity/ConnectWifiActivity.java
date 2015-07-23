package com.aigo.kt03.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.obj.ui.KT03DeviceInfo;
import com.aigo.kt03.business.obj.ui.ResultObject;
import com.aigo.usermodule.ui.util.ToastUtil;

public class ConnectWifiActivity extends ActionBarActivity {

    private static final String TAG = ConnectWifiActivity.class.getSimpleName();
    private EditText mETWiFiName;
    private EditText mETWiFiPassword;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wifi);

        mETWiFiName = (EditText)findViewById(R.id.et_wifi_name_list);
        mETWiFiPassword = (EditText)findViewById(R.id.et_wifi_password_list);
        mNext = (Button)findViewById(R.id.btn_wifi_next);

        mETWiFiName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ConnectWifiActivity.this, WifiListActivity.class), 1);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                KT03Module.getInstance().setWifiApInfo(mETWiFiName.getText().toString().trim(),mETWiFiPassword.getText().toString().trim(),new KT03Module.OnPostListener<ResultObject>() {
                    @Override
                    public void onSuccess(ResultObject result) {

                        KT03Module.getInstance().getConnectStatus(new KT03Module.OnPostListener<KT03DeviceInfo>() {
                            @Override
                            public void onSuccess(KT03DeviceInfo result) {
                                //ToastUtil.rawToast(getApplicationContext(),result.toString());
                                Log.d(TAG,result.toString());
                            }

                            @Override
                            public void onFailure(String err) {
                                ToastUtil.showToast(getApplicationContext(),err);
                                Log.d(TAG,err);
                            }
                        });

                        ToastUtil.rawToast(getApplicationContext(), "连接成功");
                        Intent intent = new Intent(ConnectWifiActivity.this, ConnectSuccessActivity.class);
                        intent.putExtra("connect_status",true);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(String err) {
                        ToastUtil.rawToast(getApplicationContext(), "连接失败");
                        Intent intent = new Intent(ConnectWifiActivity.this, ConnectSuccessActivity.class);
                        intent.putExtra("connect_status",false);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==2){

             String wifiName = data.getStringExtra("wifi_name");

            mETWiFiName.setText(wifiName);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_connect_wifi, menu);
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
