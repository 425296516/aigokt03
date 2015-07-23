package com.aigo.kt03.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aigo.kt03.R;

import java.util.List;

public class WifiListActivity extends ActionBarActivity {


    private WifiManager wifiManager;
    List<ScanResult> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);
        init();
    }

    private void init() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        list = wifiManager.getScanResults();
        ListView listView = (ListView) findViewById(R.id.listView);
        if (list == null) {
            Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
        }else {
            listView.setAdapter(new MyAdapter(this,list));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String wifiName = list.get(i).SSID;
                Intent intent = new Intent(WifiListActivity.this,ConnectWifiActivity.class);
                intent.putExtra("wifi_name",wifiName);
                setResult(2,intent);
                finish();
            }
        });

    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;
        List<ScanResult> list;
        public MyAdapter(Context context, List<ScanResult> list) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            view = inflater.inflate(R.layout.item_listview_wifi, null);
            ScanResult scanResult = list.get(position);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(scanResult.SSID);
            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);
            signalStrenth.setText(String.valueOf(Math.abs(scanResult.level)));
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //判断信号强度，显示对应的指示图标
            if (Math.abs(scanResult.level) > 100) {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_0);
            } else if (Math.abs(scanResult.level) > 80) {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_1);
            } else if (Math.abs(scanResult.level) > 70) {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_1);
            } else if (Math.abs(scanResult.level) > 60) {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_2);
            } else if (Math.abs(scanResult.level) > 50) {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_3);
            } else {
                imageView.setImageResource(R.drawable.stat_sys_wifi_signal_4);
            }
            return view;
        }
    }
}

