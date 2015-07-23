package com.aigo.kt03.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aigo.kt03.R;

public class ConnectSuccessActivity extends ActionBarActivity {

    private TextView mConnectStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_success);

        boolean val = getIntent().getBooleanExtra("connect_status",false);

        mConnectStatus = (TextView)findViewById(R.id.tv_connect_is_no);

        if(val){
            mConnectStatus.setText("连接成功");
        }else{
            mConnectStatus.setText("连接失败");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_connect_success, menu);
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
