package tech.android.tcmp13.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStartedService(View view) {

        //To start a started service just call start service with any kind of intent
        startService(new Intent(this, StartedService.class));
    }

    public void startIntentService(View view) {

        startService(new Intent(this, ApplePieIntentService.class));
    }

    public void startBoundService(View view) {

        bindService(new Intent(this, BoundService.class), this, 0);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        ((BoundService.MyBinder) iBinder).getService().showWeAreConnected();
        bound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

        bound = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
    }
}
