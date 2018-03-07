package com.wynne.knowledge.tree.guide;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/7
 */

public class NotificationActivity extends AppCompatActivity {
    private NotificationManager manager;
    public static final int START_NOTIFACTION = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
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
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon_custom)
                .setContentTitle(getResources().getString(R.string.guide))
                .setContentText(getResources().getString(R.string.start_notification));


        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(START_NOTIFACTION, builder.build());

        //   setPriority(NotificationCompat.PRIORITY_DEFAULT);  设置优先级
    }


}
