package com.dukeg.callrejector;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.KeyEvent;

import com.dukeg.callrejector.LogUtils.logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/11/14.
 * This is a broadcast receiver for call
 */

public class callListener extends BroadcastReceiver{
    String[] noCallNumber = {"110", "119", "112", "12351", "120", "12395", "4006009958"};
    List<String> noCallNumberList = Arrays.asList(noCallNumber);

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), Intent.ACTION_NEW_OUTGOING_CALL)) {
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            logger.d("call OUT:" + phoneNumber);
            if (noCallNumberList.contains(phoneNumber)){
                SystemClock.sleep(2000);
                sendKeyCode(KeyEvent.KEYCODE_ENDCALL);
                logger.d("end call");
            }
        }
    }

    private void sendKeyCode(final int keyCode){
        new Thread () {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    logger.d("Exception when sendPointerSync");
                }
            }
        }.start();
    }
}
