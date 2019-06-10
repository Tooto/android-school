package ru.handh.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Igor Glushkov on 19.07.18.
 */
public class BindingService extends Service {

    final String LOG_TAG = "LES";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        startForeground(10, createNotification());
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        return new MyBinder();
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "MyService onRebind");
    }

    public boolean onUnbind(Intent intent) {

        //рассказать
        //Return true if you would like to have the service's onRebind(Intent) method later called when new clients bind to it.

        Log.d(LOG_TAG, "MyService onUnbind");
        return super.onUnbind(intent);
        //return true;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "chanelId");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        builder.setColor(Color.BLUE);
        builder.setContentTitle("Foreground service");
        builder.setOngoing(true);
        builder.setPriority(NotificationCompat.PRIORITY_MIN);
        builder.setCategory(NotificationCompat.CATEGORY_SERVICE);

//        val notifyIntent = StreamDetailActivity.createStartIntent(context, stream.id, stream)
//        val notifyPendingIntent = PendingIntent.getActivity(context, 0, notifyIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT)
//        builder.setContentIntent(notifyPendingIntent)

        return builder.build();
    }

//    private String createNotificationChannel(String channelI, String channelName) {
//         chan = NotificationChannel(channelId,
//                channelName, NotificationManager.IMPORTANCE_NONE)
//        chan.lightColor = Color.BLUE
//        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
//        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        service.createNotificationChannel(chan)
//        return channelId
//    }


    ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// //////
    ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// //////
    ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// ////// //////

    public void test(String str) {
        Log.d(LOG_TAG, "hello from service: " + str);
    }

    class MyBinder extends Binder {
        BindingService getService() {
            return BindingService.this;
        }
    }


}
