package com.example.asmapplication;

import android.view.View;

import java.util.Calendar;

/**
 * @auther: qizewei
 * @date: 2019-08-28
 * @e-mail: qizewei@koolearn.com
 * @description：
 */
public class Utils {

    public static boolean isDoubleClick(View v) {
        Object tag = v.getTag(v.getId());
        long beforeTimemiles = tag != null ? (long) tag : 0;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        v.setTag(v.getId(), timeInMillis);
        return timeInMillis - beforeTimemiles < 1000;
    }
}
