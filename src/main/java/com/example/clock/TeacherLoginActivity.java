package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.Clock;
import com.example.clock.ennty.Time;
import com.example.clock.util.ToastUtil;

import java.util.Collection;
import java.util.List;

public class TeacherLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        findViewById(R.id.btn_teacher_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_teacher_login)
        {
            Intent intent = new Intent(TeacherLoginActivity.this , TeacherMainActivity.class);
            startActivity(intent);
            ToastUtil.show(this, "登录成功");

            MyDBHelper mHelper = MyDBHelper.getInstance(this);
            mHelper.openReadLink();
            mHelper.openWriteLink();

            List<Time> list = mHelper.queryAllTime("123");
            int i =  mHelper.queryAllTimeNum("123");
            List<Clock> clocks = mHelper.queryClockAll();
            Clock clock = mHelper.queryByUserName("123");
            Time time = mHelper.queryTimeByName("123");

            System.out.println(clock);
            System.out.println(clocks);
            System.out.println(time);
            System.out.println(list);
            System.out.println(i);

            mHelper.closeLink();
        }
    }
}