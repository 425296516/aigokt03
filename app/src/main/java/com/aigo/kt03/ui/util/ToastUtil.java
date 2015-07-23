package com.aigo.kt03.ui.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhangcirui on 15/2/2.
 */
public class ToastUtil {

    private static Toast mToast;
    //优化过后的Toast
    public static void showToast(Context context,String text) {

        if(mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {

            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    //未作任何优化的Toast
    public static void rawToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
