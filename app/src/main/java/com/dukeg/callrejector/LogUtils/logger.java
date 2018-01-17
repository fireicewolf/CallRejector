package com.dukeg.callrejector.LogUtils;

import android.util.Log;

import com.dukeg.callrejector.BuildConfig;

/**
 * Created by John on 2017/9/7.
 * This is a logger for debug version.
 */

public class logger {
    private static final boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "@string/app_name";

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }
}
