package com.aigo.kt03.business.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aigo.kt03.business.util.Constant;

/**
 * Created by zhangcirui on 15/7/18.
 */
public class DbDeviceInfoHelper extends SQLiteOpenHelper {

    public static final String TABLE_DEVICE_INFO = "device_info";

    public DbDeviceInfoHelper(Context context) {
        this(context, Constant.DB_DEVICE_INFO, null, 1);
    }

    public DbDeviceInfoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbDeviceInfoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_DEVICE_INFO + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "deviceType String," +
                "deviceName String,"+
                "deviceInfo String," +
                "status String,"+
                "openCode String," +
                "closeCode String"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

    }
}
