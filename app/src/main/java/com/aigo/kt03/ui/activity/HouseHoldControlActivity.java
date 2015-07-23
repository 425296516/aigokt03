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
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.db.obj.DbDeviceInfoObject;
import com.aigo.kt03.business.db.obj.DeviceInfoObject;
import com.aigo.usermodule.business.UserModule;
import com.aigo.usermodule.ui.util.ToastUtil;

import java.util.List;

public class HouseHoldControlActivity extends ActionBarActivity {

    private static final String TAG = HouseHoldControlActivity.class.getSimpleName();

    private Button mAddHousehold;
    private Button mGetHousehold;

    private Button mQueryId;
    private EditText mETQueryId;

    private Button mDeleteId;
    private EditText mETDeleteId;

    private Button mUpdateDeviceId;
    private EditText mETUpdateDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_control);

        mAddHousehold = (Button)findViewById(R.id.btn_add_household);
        mGetHousehold = (Button)findViewById(R.id.btn_get_household);

        mQueryId = (Button)findViewById(R.id.btn_query_id);
        mETQueryId = (EditText)findViewById(R.id.et_query_id);

        mDeleteId = (Button)findViewById(R.id.btn_delete_device_id);
        mETDeleteId = (EditText)findViewById(R.id.et_delete_device_id);

        mUpdateDeviceId = (Button)findViewById(R.id.btn_update_device_id);
        mETUpdateDeviceId = (EditText)findViewById(R.id.et_update_device_id);


        mQueryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DeviceInfoObject dbDeviceInfoObject = KT03Module.getInstance().getDeviceInfoId("1");
                Log.d(TAG,""+dbDeviceInfoObject.toString());
                ToastUtil.rawToast(HouseHoldControlActivity.this,dbDeviceInfoObject.toString());
            }
        });

        mDeleteId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean d = KT03Module.getInstance().deleteDeviceInfo("4");
                Log.d(TAG,"deleteDeviceId"+d);
                ToastUtil.rawToast(HouseHoldControlActivity.this,"deleteDeviceId"+d);

            }
        });

        mUpdateDeviceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                KT03Module.getInstance().updateDeviceInfo("1","name");

            }
        });



        mAddHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseHoldControlActivity.this,AddHouseHoldActivity.class));
            }
        });

        mGetHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DeviceInfoObject> list =  KT03Module.getInstance().getDeviceInfo();

                for (DeviceInfoObject dbDeviceInfoObject : list){
                    Log.d(TAG,dbDeviceInfoObject.toString());
                    ToastUtil.rawToast(HouseHoldControlActivity.this,dbDeviceInfoObject.toString());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_house_hold_control, menu);
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
