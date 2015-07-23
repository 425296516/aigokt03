package com.aigo.kt03.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.SDKModule;
import com.aigo.kt03.business.obj.net.NetGetLearnCodeObject;
import com.aigo.kt03.business.obj.ui.LearnCodeObject;
import com.aigo.kt03.business.util.Constant;

import java.util.Timer;
import java.util.TimerTask;

public class NextAddActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = NextAddActivity.class.getSimpleName();
    private Button mStartLearn;
    private Timer timer;
    private String deviceType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_add);

        deviceType= getIntent().getStringExtra("deviceType");

        mStartLearn = (Button)findViewById(R.id.btn_start_learn);
        mStartLearn.setOnClickListener(this);

        registerBoradcastReceiver();

    }

    private String code = null;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("finish_last_activity")){
                finish();
            }
        }

    };

    public void registerBoradcastReceiver(){
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("finish_last_activity");
        //注册广播
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_start_learn:
                KT03Module.getInstance().setLearnModel(new KT03Module.OnPostListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG,result);
                        code = result;
                        Toast.makeText(NextAddActivity.this, code+"获取到学习码", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NextAddActivity.this,CheckStatusActivity.class);
                        intent.putExtra("code",code);
                        intent.putExtra("deviceType",deviceType);

                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(String err) {
                        Toast.makeText(NextAddActivity.this, err, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, err);
                    }
                });

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_next_add, menu);
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
