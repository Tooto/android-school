package ru.handh.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Igor Glushkov on 17.07.18.
 */
public class StartService extends Service {

    public static Intent createStartIntent(Context context) {
        return new Intent(context, StartService.class);
    }

    @Override
    public void onCreate() {
        Log.d("LES", "onCreate " + this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LES", "onStartCommand " + startId);
        someTask(startId);
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("LES", "onDestroy " + this);
        super.onDestroy();
    }

    void someTask(final int startId) {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    Log.d("LES", "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(StartServiceActivity.ACTION);
                intent.putExtra("test", "hello");
                sendBroadcast(intent);
                //рассказать почему так
                //LocalBroadcastManager.getInstance(StartService.this).sendBroadcast(intent);
                stopSelf(startId);
            }
        }).start();
    }
}
