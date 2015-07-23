package com.aigo.kt03.business.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aigo.kt03.business.db.obj.DbDeviceInfoObject;
import com.google.gson.Gson;
import com.goyourfly.ln.Ln;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class DbDeviceInfo {

    private DbDeviceInfoHelper mDbDeviceInfoHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public DbDeviceInfo(Context context) {
        mDbDeviceInfoHelper = new DbDeviceInfoHelper(context);
    }


    public int insert(DbDeviceInfoObject deviceInfo) {

        mSqLiteDatabase = mDbDeviceInfoHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceType", deviceInfo.getDeviceType());
        contentValues.put("deviceName", deviceInfo.getDeviceName());
        contentValues.put("deviceInfo", deviceInfo.getDeviceInfo());
        contentValues.put("status", deviceInfo.getStatus());
        contentValues.put("openCode", deviceInfo.getOpenCode());
        contentValues.put("closeCode", deviceInfo.getCloseCode());

        int result = (int) mSqLiteDatabase.insert(DbDeviceInfoHelper.TABLE_DEVICE_INFO, null, contentValues);
        mSqLiteDatabase.close();

        return result;
    }


    public boolean deleteById(String id) {
        mSqLiteDatabase = mDbDeviceInfoHelper.getWritableDatabase();
        String sql = "delete from " + mDbDeviceInfoHelper.TABLE_DEVICE_INFO +
                " where _id = " + Integer.parseInt(id);
        mSqLiteDatabase.execSQL(sql);
        mSqLiteDatabase.close();
        return true;
    }

    public void update(String id, String deviceName) {
        try {
            String sql = "update " + mDbDeviceInfoHelper.TABLE_DEVICE_INFO + " set deviceName = '" +deviceName + "' where _id = " + Integer.parseInt(id);
            mSqLiteDatabase = mDbDeviceInfoHelper.getReadableDatabase();
            mSqLiteDatabase.execSQL(sql);
            mSqLiteDatabase.close();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void setStatus(String id, String status) {
        try {
            String sql = "update " + mDbDeviceInfoHelper.TABLE_DEVICE_INFO + " set status = '" +status + "' where _id = " + Integer.parseInt(id);
            mSqLiteDatabase = mDbDeviceInfoHelper.getReadableDatabase();
            mSqLiteDatabase.execSQL(sql);
            mSqLiteDatabase.close();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public DbDeviceInfoObject queryById(String deviceId) {

        mSqLiteDatabase = mDbDeviceInfoHelper.getReadableDatabase();
        String sql = "select * from " + mDbDeviceInfoHelper.TABLE_DEVICE_INFO + " where _id = " + Integer.parseInt(deviceId);
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        DbDeviceInfoObject dbDeviceInfoObject = new DbDeviceInfoObject();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String deviceType = cursor.getString(cursor.getColumnIndex("deviceType"));
            String deviceName = cursor.getString(cursor.getColumnIndex("deviceName"));
            String deviceInfo = cursor.getString(cursor.getColumnIndex("deviceInfo"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            String openCode = cursor.getString(cursor.getColumnIndex("openCode"));
            String closeCode = cursor.getString(cursor.getColumnIndex("closeCode"));

            dbDeviceInfoObject.setId(String.valueOf(id));
            dbDeviceInfoObject.setDeviceType(deviceType);
            dbDeviceInfoObject.setDeviceName(deviceName);
            dbDeviceInfoObject.setDeviceInfo(deviceInfo);
            dbDeviceInfoObject.setStatus(status);
            dbDeviceInfoObject.setOpenCode(openCode);
            dbDeviceInfoObject.setCloseCode(closeCode);


        }
        cursor.close();
        mSqLiteDatabase.close();
        return dbDeviceInfoObject;
    }

    public List<DbDeviceInfoObject> queryAll() {

        mSqLiteDatabase = mDbDeviceInfoHelper.getReadableDatabase();
        List<DbDeviceInfoObject> list = new ArrayList<DbDeviceInfoObject>();
        String sql = "select * from " + mDbDeviceInfoHelper.TABLE_DEVICE_INFO + " order by _id asc";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String deviceType = cursor.getString(cursor.getColumnIndex("deviceType"));
            String deviceName = cursor.getString(cursor.getColumnIndex("deviceName"));
            String deviceInfo = cursor.getString(cursor.getColumnIndex("deviceInfo"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            String openCode = cursor.getString(cursor.getColumnIndex("openCode"));
            String closeCode = cursor.getString(cursor.getColumnIndex("closeCode"));

            DbDeviceInfoObject dbDeviceInfoObject = new DbDeviceInfoObject();
            dbDeviceInfoObject.setId(String.valueOf(id));
            dbDeviceInfoObject.setDeviceType(deviceType);
            dbDeviceInfoObject.setDeviceName(deviceName);
            dbDeviceInfoObject.setDeviceInfo(deviceInfo);
            dbDeviceInfoObject.setStatus(status);
            dbDeviceInfoObject.setOpenCode(openCode);
            dbDeviceInfoObject.setCloseCode(closeCode);

            list.add(dbDeviceInfoObject);
        }
        cursor.close();
        mSqLiteDatabase.close();
        return list;
    }


}
