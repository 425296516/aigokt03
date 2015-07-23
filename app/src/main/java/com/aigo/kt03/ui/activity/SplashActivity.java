package com.aigo.kt03.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aigo.kt03.R;
import com.aigo.kt03.ui.util.ToastUtil;
import com.aigo.usermodule.business.UserModule;
import com.aigo.usermodule.ui.LoginActivity;

public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (UserModule.getInstance().isLogin()) {

            //initData();
            startActivity(new Intent(SplashActivity.this,AirIndexActivity.class));

        } else {

            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);


            startActivityForResult(intent, LoginActivity.INTENT_REQUEST_CODE);

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        if (requestCode == LoginActivity.INTENT_REQUEST_CODE) {
            ToastUtil.showToast(SplashActivity.this, "登录成功");

            startActivity(new Intent(SplashActivity.this, AirIndexActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
