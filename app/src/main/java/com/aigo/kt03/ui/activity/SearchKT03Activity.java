package com.aigo.kt03.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Adapter;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.obj.ui.ResultObject;
import com.aigo.kt03.business.task.SearchKT03Task;
import com.aigo.usermodule.ui.util.ToastUtil;

public class SearchKT03Activity extends ActionBarActivity {

    private Button mSearchKT03;
    private TextView mTvSearch;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_kt03);

        mSearchKT03 =  (Button)findViewById(R.id.btn_search_kt03);
        mTvSearch = (TextView)findViewById(R.id.tv_search_kt03);
        mNext = (Button)findViewById(R.id.btn_next);

        mSearchKT03.setVisibility(View.VISIBLE);
        mTvSearch.setVisibility(View.GONE);
        mNext.setVisibility(View.GONE);

        mSearchKT03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                KT03Module.getInstance().searchKT03(new KT03Module.OnPostListener<ResultObject>() {
                    @Override
                    public void onSuccess(ResultObject result) {
                        ToastUtil.showToast(getApplicationContext(),"检测到kt03设备");

                        mSearchKT03.setVisibility(View.GONE);
                        mTvSearch.setVisibility(View.VISIBLE);
                        mNext.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(String err) {

                    }
                });

            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchKT03Activity.this,ConnectWifiActivity.class));
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_kt03, menu);
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
