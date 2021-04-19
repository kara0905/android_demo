package com.zyl.logapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    TimeCounter counter;
    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);
    //private final org.apache.log4j.Logger log2 = org.apache.log4j.Logger.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Disposable disposable = new RxPermissions(this).request(//动态获取权限获取设备DivceID需要权限申请
//                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//
//                    }
//                });
        counter = new TimeCounter("MainActivity");

        //   TextView txt = (TextView) findViewById(R.id.txt);
        //  float f = doDemo();
        //   txt.setText("时长:" + f + "毫秒");

    }


    /**
     * 注意： 1.在使用android studio连接连接真机查看控制台日志时，根据手机不同，
     * 可能仅仅看到 error日志。这不是日志框架出了问题，这是手机的设置"默认只显示 error日志"，
     * 实际上真实的日志还是会写入到日志文件中的，请查看日志文件。
     * 2.在模拟器下可以看到所有的控制台输出日志。
     *
     * @return
     */
    private float doDemo() {
        counter.begin();


        for (int i = 0; i < 1; i++) {
            //LogUtil.debug(TAG, "debug 输出");

            logger.trace("trace Some log message. Details: {}", "trace 输出" + i);
            logger.debug("debug Some log message. Details: {}", "debug 输出" + i);
            logger.info("info Some log message. Details: {}", "info 输出" + i);
            logger.error("error Some log message. Details: {}", "error 输出" + i);
            logger.warn("warn Some log message. Details: {}", "warn 输出" + i);
        }

        counter.end();
        counter.print();
        return counter.getHowLong();
    }


    public void addLog(View view) {

        doDemo();
    }

    public void readLog(View view) {
        new Thread(() -> {
            try {
                //保存这里不需要权限,是 app 私有空间 建议这么保存
                //   /data/user/0/com.zyl.logapp/files/log  //设备文件位置 data/data/包名/files/log
                String LOG_DIR = getFilesDir().getAbsoluteFile().toString() + File.separator + "log";
                //保存在这里需要申请权限
                //  /storage/emulated/0/log                //设备文件位置  sdcard/log
                //     final String LOG_DIR1 = Environment.getExternalStorageDirectory() + File.separator + "log";
                Log.d("LOG", "## 日志路径：" + LOG_DIR);
                File dir = new File(LOG_DIR);
                if (!dir.exists())
                    dir.mkdir();

                Log.d("LOG", "## 日志文件夹是否存在" + dir.exists());

                if (dir.listFiles() != null) {
                    Log.d("LOG", "## 日志文件夹是否存在" + dir.listFiles().length);


                    for (int i = 0; i < dir.listFiles().length; i++) {
                        File f = dir.listFiles()[i];
                        Log.e("LOG", "## 日志文件:" + f.getPath() + ", size=" + f.length());

                        BufferedReader in = new BufferedReader(new FileReader(f.getPath()));
                        String str;
                        while ((str = in.readLine()) != null) {
                            Log.d("LOG", "## 日志文件内容:" + str);
                        }
                        //读完删除该日志文件
                        f.delete();
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}