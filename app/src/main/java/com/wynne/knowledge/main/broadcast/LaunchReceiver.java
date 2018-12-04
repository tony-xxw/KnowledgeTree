package com.wynne.knowledge.main.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * @author Wynne
 * @date 2018/7/5
 */

public class LaunchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "自动启动", Toast.LENGTH_LONG).show();
//            Intent mainIntent = new Intent(context, MainActivity.class);
//            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mainIntent);
        }
    }
}
