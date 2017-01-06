package tech.android.tcmp13.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tcmp13-t on 1/4/2017.
 */
public class StartedService extends Service {

    @Override
    public void onCreate() {

        Thread thread = Thread.currentThread();
        Log.d("service_test", "onCreate()" + thread.toString());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = Thread.currentThread();
        Log.d("service_test", "onStartCommand()" + thread.toString());
        //Handler can execute commands on different threads, should be created from the main UI Thread
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }, 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        //To make the service a started and not a bound, return null
        return null;
    }

    @Override
    public void onDestroy() {

        Thread thread = Thread.currentThread();
        Log.d("service_test", "onDestroy()" + thread.toString());
        super.onDestroy();
    }
}
