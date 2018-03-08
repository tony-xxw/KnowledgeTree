package com.wynne.knowledge.tree.guide;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/7
 */

public class NotificationActivity extends AppCompatActivity {
    private NotificationManagerCompat manager;
    public static final int START_NOTIFICATION = 0;
    public static final String BROADCAST_NOTIFICATION = "BroadCast";

    private PendingIntent pendingIntent;
    private Intent intent;


    private static final String KEY_TEXT_REPLY = "key_text_reply";
    RemoteInput mRemoteInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        //启动Activity

        intent = new Intent(this, NotificationPendActivity.class);
        //栈中只存在一个实例
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        //启动Broadcast

        intent = new Intent(this, MyBroadCast.class);
        intent.setAction(MyBroadCast.BROADCAST_TEST);
        intent.putExtra(BROADCAST_NOTIFICATION, BROADCAST_NOTIFICATION);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        mRemoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(getString(R.string.reply))
                .build();


//        pendingIntent = PendingIntent.getBroadcast(this)


        Log.d("XXW", "");

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startNotification();
                break;
            case R.id.btn_update:
                updateNotification();
                break;
            case R.id.btn_delete:
                deleteNotification();
                break;
            default:
                break;
        }
    }

    private void deleteNotification() {

    }

    private void updateNotification() {

    }

    private void startNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_custom)
                .setContentTitle(getResources().getString(R.string.guide))
                .setContentText(getResources().getString(R.string.start_notification));


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_custom)
                .setContentTitle(getResources().getString(R.string.guide))
                .setContentText(getResources().getString(R.string.start_notification))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.start_notification)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //点击跳转
                .setContentIntent(pendingIntent)
                //点击则删除
                .setAutoCancel(true);

        NotificationCompat.Builder broadcast = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_custom)
                .setContentTitle(getResources().getString(R.string.guide))
                .setContentText(getResources().getString(R.string.start_notification))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.start_notification)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //点击跳转
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.icon_custom, getString(R.string.start_notification), pendingIntent);


        //8.0以上发布通知之前,需要向系统注册应用渠道,通过NotificationChannel 向CreateNotificationChannel提交实例

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = getString(R.string.start_notification);
            String description = getString(R.string.guide);
            int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.createNotificationChannel(channel);
        }*/


        manager = NotificationManagerCompat.from(this);

        manager.notify(START_NOTIFICATION, broadcast.build());

        //   setPriority(NotificationCompat.PRIORITY_DEFAULT);  设置优先级
    }


    /**
     * 内部静态广播必须为静态
     */
    public static class MyBroadCast extends BroadcastReceiver {


        public static final String BROADCAST_TEST = "broadcast";


        @Override
        public void onReceive(Context context, Intent intent) {
            if (!TextUtils.isEmpty(intent.getAction())) {
                if (intent.getAction().equals(BROADCAST_TEST)) {
                    Log.d("XXW", "Notification 广播应用----" + intent.getStringExtra(NotificationActivity.BROADCAST_NOTIFICATION));
                }
            }
        }
    }
}
