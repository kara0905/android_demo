package com.zyl.ndk;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private final JWebSocketClientBinder mBinder = new JWebSocketClientBinder();

    //用于Activity和service通讯
    public class JWebSocketClientBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        //  openDb();
        Log.e("service", "EventBus register");

    }
    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void onDataSynEvent(EventBean event) {
        Log.e("service", "收到了");

    }
}
