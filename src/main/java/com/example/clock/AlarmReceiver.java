package com.example.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.clock.AlarmActivity;

//广播接收类
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlarmActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}