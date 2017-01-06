package tech.android.tcmp13.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tcmp13-t on 1/4/2017.
 */
public class ApplePieIntentService extends IntentService {

    private Handler mainUiThreadHandler;

    /**
     * Default constructor for intent service must call the String param constructor with the name
     * of this service class
     */
    public ApplePieIntentService() {

        super(ApplePieIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Thread thread = Thread.currentThread();
        Log.d("service_test", "onCreate()" + thread.toString());
        //We run on the main UI thread, so any handler created with the default constructor (of the Handler class)
        //will provide an access to load tasks (Runnable) onto the thread it has access to
        mainUiThreadHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Thread thread = Thread.currentThread();
        Log.d("service_test", "onHandleIntent()" + thread.toString());

        //To make a toast from the worker thread use handler
        notifyTheUserWithMessage("started putting ingredients");

        //Put the ingredients together
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyTheUserWithMessage("ingredients done, now in the oven");

        //READY TO EAT
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyTheUserWithMessage("YUUM YUUM");
    }

    private void notifyTheUserWithMessage(final String message) {

        mainUiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ApplePieIntentService.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
