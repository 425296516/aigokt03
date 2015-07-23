package com.aigo.kt03.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.aigo.kt03.R;
import com.aigo.kt03.business.KT03Module;
import com.aigo.kt03.business.SDKModule;
import com.aigo.kt03.business.db.obj.DeviceInfoObject;

import java.util.List;

public class NoHouseHoldActivity extends ActionBarActivity {

    private static final String TAG = NoHouseHoldActivity.class.getSimpleName();

    private ListView mListView;
    private String mCode;
    private List<DeviceInfoObject> mList;
    private MyAdapter adapter;
    private Button mAddDevice;
    private PopupWindow popupWindow;
    private String mCustomCost = "";
    String code = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_house_hold);

        mCode = getIntent().getStringExtra("code");

        mList = KT03Module.getInstance().getDeviceInfo();
        mAddDevice = (Button) findViewById(R.id.btn_add_device);
        mListView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter();

        mListView.setAdapter(adapter);

        mAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoHouseHoldActivity.this, AddHouseHoldActivity.class));
                finish();
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }

                final AlertDialog exitDialog = new AlertDialog.Builder(NoHouseHoldActivity.this).create();
                exitDialog.show();
                final Window window = exitDialog.getWindow();
                window.setContentView(R.layout.dlg_checkout_status_open);
                Button ok = (Button) window.findViewById(R.id.btn_user_home_makesure);
                Button cancel = (Button) window.findViewById(R.id.btn_user_home_cancel);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        code = mList.get(i).getOpenCode();

                        KT03Module.getInstance().setStatus(mList.get(i).getId(), true);
                        mList = KT03Module.getInstance().getDeviceInfo();
                        adapter.notifyDataSetChanged();

                        KT03Module.getInstance().sendIRCode(code, new KT03Module.OnPostListener<Integer>() {
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

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();

                        code = mList.get(i).getCloseCode();

                        KT03Module.getInstance().setStatus(mList.get(i).getId(), false);
                        mList = KT03Module.getInstance().getDeviceInfo();
                        adapter.notifyDataSetChanged();

                        KT03Module.getInstance().sendIRCode(code, new KT03Module.OnPostListener<Integer>() {
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
                    }
                });


                /*if (mList.get(i).isStatus()) {
                    code = mList.get(i).getCloseCode();
                    //mList.get(i).setStatus(false);
                    DeviceInfoObject dbDeviceInfoObject = new DeviceInfoObject();
                    dbDeviceInfoObject.setId(mList.get(i).getId());
                    dbDeviceInfoObject.setDeviceName(mList.get(i).getDeviceName());
                    dbDeviceInfoObject.setStatus(false);
                    KT03Module.getInstance().setStatus(dbDeviceInfoObject);
                    mList = KT03Module.getInstance().getDeviceInfo();
                    adapter.notifyDataSetChanged();

                    SDKModule.getInstance().sendIRCode(code, new SDKModule.OnPostListener<Integer>() {
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

                } else if(!mList.get(i).isStatus()) {
                    code = mList.get(i).getOpenCode();

                    DeviceInfoObject dbDeviceInfoObject = new DeviceInfoObject();
                    dbDeviceInfoObject.setId(mList.get(i).getId());
                    dbDeviceInfoObject.setDeviceName(mList.get(i).getDeviceName());
                    dbDeviceInfoObject.setStatus(true);
                    KT03Module.getInstance().setStatus(dbDeviceInfoObject);
                    mList = KT03Module.getInstance().getDeviceInfo();
                    adapter.notifyDataSetChanged();

                    SDKModule.getInstance().sendIRCode(code, new SDKModule.OnPostListener<Integer>() {
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
                }*/
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                View prView = getLayoutInflater().inflate(R.layout.menu_user_home, null);//自定义的布局文件
                popupWindow = new PopupWindow(prView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.drw_2_weather_popup_window_bg));
                popupWindow.showAsDropDown(view, 200, 0);

                TextView addDevice = (TextView) prView.findViewById(R.id.tv_update_password);
                TextView deleteDevice = (TextView) prView.findViewById(R.id.tv_update_user_info);
                TextView updateDevice = (TextView) prView.findViewById(R.id.tv_unlogin);

                LinearLayout llAddDevice = (LinearLayout) prView.findViewById(R.id.user_home_menu_change_pwd);
                LinearLayout llDeleteDevice = (LinearLayout) prView.findViewById(R.id.user_home_menu_change_info);
                LinearLayout llUpdateDevice = (LinearLayout) prView.findViewById(R.id.user_home_menu_logout);

                addDevice.setText("添加电器");
                deleteDevice.setText("删除电器");
                updateDevice.setText("编辑名称");

                llAddDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(NoHouseHoldActivity.this, AddHouseHoldActivity.class));
                        finish();
                        popupWindow.dismiss();
                    }
                });

                llDeleteDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();

                        KT03Module.getInstance().deleteDeviceInfo(mList.get(i).getId());
                        mList = KT03Module.getInstance().getDeviceInfo();
                        adapter.notifyDataSetChanged();
                    }
                });

                llUpdateDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        setCustomCost(true, i);
                    }
                });

                return true;
            }
        });
    }

    /**
     * 弹出一个AlertDaialog输入自定义的费用
     *
     * @param cancelable
     */
    public void setCustomCost(boolean cancelable, final int i) {

        // 隐藏输入法
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 显示或者隐藏输入法
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setCancelable(cancelable);
        exitDialog.show();
        Window window = exitDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.sub_popwindow_select_consult_cost);
        Button okBtn = (Button) window.findViewById(R.id.btn_save_consult_cost);
        Button cancelBtn = (Button) window.findViewById(R.id.btn_save_consult_cancel);

        final EditText customCost = (EditText) window.findViewById(R.id.et_custom_cost);

        customCost.setText(mList.get(i).getDeviceName() + "");

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (customCost.getText().toString().trim() != null) {
                    Log.d(TAG, "customCost=" + customCost.getText().toString().trim());
                    mCustomCost = customCost.getText().toString().trim();

                    //DeviceInfoObject dbDeviceInfoObject = new DeviceInfoObject();
                    //dbDeviceInfoObject.setId(mList.get(i).getId());
                    //dbDeviceInfoObject.setDeviceName(mCustomCost);
                    KT03Module.getInstance().updateDeviceInfo(mList.get(i).getId(), mCustomCost);
                    mList = KT03Module.getInstance().getDeviceInfo();
                    adapter.notifyDataSetChanged();
                }
                exitDialog.dismiss();
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            View view = getLayoutInflater().inflate(R.layout.item_listview_household, null, false);

            TextView deviceName = (TextView) view.findViewById(R.id.tv_device_name);
            ImageView deviceStatus = (ImageView) view.findViewById(R.id.iv_device_status);

            deviceName.setText(mList.get(position).getDeviceName());
            boolean status = mList.get(position).isStatus();

            if (status) {
                deviceStatus.setImageResource(R.drawable.ic_launcher);
            } else {
                deviceStatus.setImageResource(R.drawable.stat_sys_wifi_signal_0);
            }
            return view;
        }
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
