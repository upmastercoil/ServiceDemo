package tech.android.tcmp13.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by tcmp13-t on 1/4/2017.
 */
public class BoundService extends Service {

    private Binder myBinder;

    @Override
    public void onCreate() {

        super.onCreate();
        myBinder = new MyBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }

    public class MyBinder extends Binder {

        BoundService getService() {

            return BoundService.this;
        }
    }

    public void showWeAreConnected() {

        Toast.makeText(this, "I AM THE SERVICE", Toast.LENGTH_SHORT).show();
    }
}
