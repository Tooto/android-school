package ru.handh.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class BindindServiceActivity extends AppCompatActivity {

    final String LOG_TAG = "LES";

    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    @Nullable
    BindingService.MyBinder myBinder;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindind_service);

        intent = new Intent(this, BindingService.class);

        sConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
                myBinder = (BindingService.MyBinder) binder;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickStop(View v) {
        stopService(intent);
    }

    public void onClickBind(View v) {
        bindService(intent, sConn, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View v) {
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    public void onClickTest(View v) {
        if (myBinder != null) {
            myBinder.getService().test("igor");
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
