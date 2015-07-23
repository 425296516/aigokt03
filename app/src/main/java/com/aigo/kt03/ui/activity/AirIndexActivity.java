package com.aigo.kt03.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Adapter;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.obj.ui.AirIndex;
import com.aigo.kt03.business.obj.ui.AirQuality;
import com.aigo.kt03.business.task.SearchKT03Task;
import com.aigo.usermodule.business.UserModule;
import com.aigo.usermodule.ui.LoginActivity;
import com.aigo.usermodule.ui.UpdatePasswordActivity;
import com.aigo.usermodule.ui.UpdateUserInfoActivity;

import java.text.SimpleDateFormat;

public class AirIndexActivity extends ActionBarActivity {

    private static final String TAG = AirIndexActivity.class.getSimpleName();

    private TextView mIaq;
    private TextView mIaqPollute;
    private TextView mTem;
    private TextView mHumidity;
    private TextView mNoise;
    private TextView mCo2;
    private TextView mVoc;
    private TextView mPm25;
    private TextView mFormadehyde;
    private TextView mUpdateTime;
    private Button mHouseHoldControl;
    private Button mIndoorAir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_index);
        initView();
        if (UserModule.getInstance().isLogin()) {
            initData();
        } else {
            Intent intent = new Intent(AirIndexActivity.this, LoginActivity.class);
            startActivityForResult(intent, LoginActivity.INTENT_REQUEST_CODE);
        }

        mHouseHoldControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AirIndexActivity.this,NoHouseHoldActivity.class));
            }
        });
        mIndoorAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AirIndexActivity.this,SearchKT03Activity.class));
            }
        });
    }

    private void initData() {

        KT03Module.getInstance().setDataChangedListener(new KT03Module.OnPostListener<AirIndex>() {
            @Override
            public void onSuccess(AirIndex airIndex) {
                AirQuality airQuality = KT03Adapter.getInstance().getAirIndexToAirQuality(airIndex);
                mIaq.setText(""+airIndex.getIaq());
                mIaqPollute.setText(""+airIndex.getIaqQuality());
                mTem.setText("温度："+airIndex.getTemperature()+" : "+airQuality.getTemperature());
                mHumidity.setText("湿度："+airIndex.getHumidity()+" : "+airQuality.getHumidity());
                mNoise.setText("噪音："+airIndex.getNoise()+" : "+airQuality.getNoise());
                mCo2.setText("二氧化碳："+airIndex.getCo2()+" : "+airQuality.getCo2());
                mVoc.setText("Voc:"+airIndex.getVoc()+" : "+airQuality.getVoc());
                mPm25.setText("pm2.5:"+airIndex.getPm25()+" : "+airQuality.getPm25());
                mFormadehyde.setText("甲醛："+airIndex.getFormadehyde()+" : "+airQuality.getFormadehyde());

                SimpleDateFormat dataFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mUpdateTime.setText("时间："+dataFormat.format(airIndex.getTime()));

                Log.d(TAG, airIndex.toString());
            }

            @Override
            public void onFailure(String err) {

            }
        });

       // Constant.URL_KT03 = "http://" + mIPAddress.getText().toString().trim() + ":" + mPort.getText().toString().trim();
        KT03Module.getInstance().getKT03AirInfo(new KT03Module.OnPostListener<AirIndex>() {
            @Override
            public void onSuccess(AirIndex airIndex) {
                //AirIndex airIndex = KT03Module.getInstance().getKT03AirInfo();
                AirQuality airQuality = KT03Adapter.getInstance().getAirIndexToAirQuality(airIndex);
                mIaq.setText(""+airIndex.getIaq());
                mIaqPollute.setText(""+airIndex.getIaqQuality());
                mTem.setText("温度："+airIndex.getTemperature()+" : "+airQuality.getTemperature());
                mHumidity.setText("湿度："+airIndex.getHumidity()+" : "+airQuality.getHumidity());
                mNoise.setText("噪音："+airIndex.getNoise()+" : "+airQuality.getNoise());
                mCo2.setText("二氧化碳："+airIndex.getCo2()+" : "+airQuality.getCo2());
                mVoc.setText("Voc:"+airIndex.getVoc()+" : "+airQuality.getVoc());
                mPm25.setText("pm2.5:"+airIndex.getPm25()+" : "+airQuality.getPm25());
                mFormadehyde.setText("甲醛："+airIndex.getFormadehyde()+" : "+airQuality.getFormadehyde());

                SimpleDateFormat dataFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mUpdateTime.setText("时间："+dataFormat.format(airIndex.getTime()));

                Log.d(TAG, airIndex.toString());
            }

            @Override
            public void onFailure(String err) {
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
                Log.d(TAG, err);
            }
        });


    }

    private void initView() {

        mIaq = (TextView)findViewById(R.id.tv_iaq_num);
        mIaqPollute = (TextView)findViewById(R.id.tv_iaq_pollute_num);
        mTem = (TextView)findViewById(R.id.tv_temperature_num);
        mHumidity = (TextView)findViewById(R.id.tv_humidity_num);
        mNoise = (TextView)findViewById(R.id.tv_noise_num);
        mCo2 = (TextView)findViewById(R.id.tv_co2_num);
        mVoc = (TextView)findViewById(R.id.tv_voc_num);
        mPm25 = (TextView)findViewById(R.id.tv_pm25_num);
        mFormadehyde = (TextView)findViewById(R.id.tv_formadehyde_num);
        mUpdateTime = (TextView)findViewById(R.id.tv_update_time);
        mHouseHoldControl = (Button)findViewById(R.id.btn_household_control);
        mIndoorAir = (Button)findViewById(R.id.btn_indoor_air);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LoginActivity.INTENT_REQUEST_CODE) {

            initData();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_air_index, menu);
        MenuItem menuItem = menu.findItem(R.id.user_actionbar_menu);
        if (!isMine()) {
            menuItem.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        if( popupWindow !=null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }else {
            super.onBackPressed();
        }
    }


    public boolean isMine() {
        if (!UserModule.getInstance().isLogin()
                || !UserModule.getInstance().getUser().getUsername().equals(UserModule.getInstance().getUser().getUsername())) {
            return false;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.user_actionbar_menu) {

            showMenu();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private PopupWindow popupWindow;
    private void showMenu() {
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;

        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.menu_user_home, null);
        View menu = view.findViewById(R.id.lv_2_weather_menu);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menu.getLayoutParams();
        params.topMargin = statusBarHeight + getSupportActionBar().getHeight();
        menu.setLayoutParams(params);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        View changePwd = view.findViewById(R.id.user_home_menu_change_pwd);
        View divideLine = view.findViewById(R.id.divide_line);
        View changeInfo = view.findViewById(R.id.user_home_menu_change_info);
        View logout = view.findViewById(R.id.user_home_menu_logout);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        if(!UserModule.getInstance().getLoginMode().equals(com.aigo.usermodule.business.util.Constant.NATIVE_LOGIN_MODE)){
            changePwd.setVisibility(View.GONE);
            divideLine.setVisibility(View.GONE);
        }else {
            changePwd.setVisibility(View.VISIBLE);
            divideLine.setVisibility(View.VISIBLE);
        }

        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AirIndexActivity.this, UpdatePasswordActivity.class));
                popupWindow.dismiss();
            }
        });
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AirIndexActivity.this, UpdateUserInfoActivity.class));
                popupWindow.dismiss();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                showExitDialog();
            }
        });
    }

    private void showExitDialog() {
        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.show();
        final Window window = exitDialog.getWindow();
        window.setContentView(R.layout.dlg_user_exit);
        Button ok = (Button) window.findViewById(R.id.btn_user_home_makesure);
        Button cancel = (Button) window.findViewById(R.id.btn_user_home_cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
                UserModule.getInstance().logout();

                onBackPressed();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });

    }


}
