package com.wynne.knowledge.tree.guide.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.v4.app.NotificationCompat;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

import static android.app.Notification.VISIBILITY_PRIVATE;

/**
 * @author Wynne
 * @date 2018/3/7
 */

public class NotificationActivity extends BaseActivity {
    private NotificationManagerCompat manager;
    private NotificationCompat.Builder builder;
    public static final int START_NOTIFICATION = 0;
    public static final int PROGRESS_NOTIFICATION = 0;
    public static final String BROADCAST_NOTIFICATION = "BroadCast";

    private PendingIntent pendingIntent;
    private PendingIntent replyPending;
    private Intent intent;
    private Bitmap bitmap;
    private Bitmap largeBitmap;
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    RemoteInput mRemoteInput;

    public String CHANNEL = "1";


    @Override
    public void initView() {
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


        Log.d("XXW", "");

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_custom);
        largeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_large);
    }

    @Override
    public int getLayoutId() {
        return R.layout.notification_layout;
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
            case R.id.btn_progress:
                progressNotification();
                break;
            case R.id.btn_expandable:
                expandableNotification();
                break;
            case R.id.btn_group:
                groupNotification();
                break;
            default:
                break;
        }
    }

    private void groupNotification() {
        int SUMMARY_ID = 1;
        int notifications = 2;
        int notifications1 = 3;
        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL)
                .setContentTitle("群组Demo1")
                .setContentText("群组Notification测试")
                .setSmallIcon(R.drawable.icon_guide)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                .build();

        Notification notification1 = new NotificationCompat.Builder(this, CHANNEL)
                .setContentTitle("群组Demo2")
                .setContentText("群组Notification测试 第二版")
                .setSmallIcon(R.drawable.icon_guide)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                .build();
        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL)
                .setContentTitle("群组Demo3")
                .setContentText("群组Notification测试  第三版")
                .setSmallIcon(R.drawable.icon_guide)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Alex Faarborg  Check this out")
                        .addLine("Jeff Chang    Launch Party")
                        .setBigContentTitle("2 new messages")
                        //概要信息
                        .setSummaryText("janedoe@example.com")
                )
                //是否设置概要
                .setGroupSummary(true)
                .build();

        NotificationManagerCompat compat = NotificationManagerCompat.from(this);

        compat.notify(notifications, notification);
        compat.notify(notifications1, notification1);
        compat.notify(SUMMARY_ID, notification2);

    }

    private void expandableNotification() {
        Notification notification = new NotificationCompat.Builder(this, KEY_TEXT_REPLY)
                .setContentTitle("expandable title")
                .setContentText("expandable content")
                .setSmallIcon(R.drawable.icon_guide)
                .setLargeIcon(largeBitmap)
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(largeBitmap)
                //                        .bigLargeIcon(null))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("To make the image appear as a" +
                                " thumbnail only while the notification" +
                                " is collapsed (as shown in figure 1)" +
                                "To make the image appear as a thumbnail " +
                                "only while the notification is collapsed" +
                                " (as shown in figure 1)" + "To make the image appear as a" +
                                " thumbnail only while the notification" +
                                " is collapsed (as shown in figure 1)" +
                                "To make the image appear as a thumbnail " +
                                "only while the notification is collapsed" +
                                " (as shown in figure 1)")
                )
                .build();
        manager = NotificationManagerCompat.from(this);
        manager.notify(PROGRESS_NOTIFICATION, notification);

    }

    private void progressNotification() {
        manager = NotificationManagerCompat.from(this);
        builder = new NotificationCompat.Builder(this)
                .setContentText(getString(R.string.progress_notification))
                .setContentTitle(getString(R.string.progress_title_notification))
                .setSmallIcon(R.drawable.icon_train)
                .setVisibility(VISIBILITY_PRIVATE)
                .setPriority(NotificationCompat.PRIORITY_LOW);
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 1;


        while (PROGRESS_CURRENT != PROGRESS_MAX) {
            PROGRESS_CURRENT++;
            builder.setProgress(PROGRESS_CURRENT, PROGRESS_MAX, false);
            manager.notify(PROGRESS_NOTIFICATION, builder.build());
        }

        if (PROGRESS_CURRENT == PROGRESS_MAX) {
            builder.setContentText("Download Complete");
            builder.setProgress(0, 0, false);
            manager.notify(PROGRESS_NOTIFICATION, builder.build());
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


        //添加Button Action
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
