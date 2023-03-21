package com.example.clock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.clock.util.ToastUtil;

import java.util.Calendar;
public class BookActivity extends Activity implements View.OnClickListener {
    //private Button btn;
    private AlarmManager alarmManager;  //闹钟管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //获取闹钟管理器
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        findViewById(R.id.btn_setClock).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back)
            finish();
        else
            setClock(view);
    }
    public void setClock(View view) {
        //获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        //弹出闹钟框
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c = Calendar.getInstance();    //获取日期对象
                c.set(Calendar.HOUR_OF_DAY, hourOfDay); //设置闹钟小时数
                c.set(Calendar.MINUTE, minute); //设置闹钟分钟数
                Intent intent = new Intent(BookActivity.this, AlarmReceiver.class);
                //创建pendingIntent
                PendingIntent pendingIntent = PendingIntent.getBroadcast(BookActivity.this, 0X102, intent, 0);
                //设置闹钟
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                //Toast.makeText(BookActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT).show();
                ToastUtil.show(BookActivity.this, "闹钟设置成功");
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }


}
