package com.aigo.kt03.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;

public class AddHouseHoldActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mAirConditioner;
    private Button mPurifier;
    private Button mHumidifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house_hold);

        mAirConditioner = (Button)findViewById(R.id.btn_air_conditioner);
        mPurifier = (Button)findViewById(R.id.btn_purifier);
        mHumidifier = (Button)findViewById(R.id.btn_humidifier);

        mAirConditioner.setOnClickListener(this);
        mPurifier.setOnClickListener(this);
        mHumidifier.setOnClickListener(this);

        KT03Module.getInstance().setDeviceInfoObject(null);
    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(AddHouseHoldActivity.this,NextAddActivity.class);

        switch (view.getId()){

            case R.id.btn_air_conditioner:

                intent.putExtra("deviceType","air_conditioner");

                startActivity(intent);
                finish();
                break;

            case R.id.btn_purifier:

                intent.putExtra("deviceType","purifier");

                startActivity(intent);
                finish();
                break;

            case R.id.btn_humidifier:

                intent.putExtra("deviceType","humidifier");

                startActivity(intent);
                finish();
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_house_hold, menu);
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
