package com.zyl.ndk;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;


import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvShow;
    private NdkJniUtils ndkJniUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = ((TextView) findViewById(R.id.tv_show));
        tvShow.setOnClickListener(this);
        ndkJniUtils = new NdkJniUtils();
        startService(new Intent(MainActivity.this, MyService.class));
        Handler handler = new Handler();

        handler.postDelayed(runnable, 3000);
        Disposable disposable = new RxPermissions(this).request(//动态获取权限获取设备DivceID需要权限申请
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                    }
                });


    }

    private Runnable runnable = () -> {
        Log.e("Runnable", "App.isBoot is " + App.isBoot);
        if (App.isBoot)
            EventBus.getDefault().post(new EventBean());

    };

    @Override
    public void onClick(View v) {
        String cLanguageString = ndkJniUtils.getCLanguageString();
        Log.e("MainActivity", cLanguageString);
        String uId = UUID.randomUUID().toString();
        Log.e("MainActivity", uId);
        Log.e("MainActivity", uId.replace("-", ""));
    }

}