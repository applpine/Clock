package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.Clock;

public class ClockActivity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_time;
    private TextView tv_user;
    private TextView tv_day;
    private TextView tv_most_day;
    private TextView tv_key_word;
    private TextView tv_summary;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock2);
        tv_time = findViewById(R.id.tv_time);
        tv_user = findViewById(R.id.tv_user);
        tv_day = findViewById(R.id.tv_day);
        tv_most_day = findViewById(R.id.tv_most_day);
        tv_key_word = findViewById(R.id.tv_key_word);
        tv_summary = findViewById(R.id.tv_summary);

        findViewById(R.id.btn_back).setOnClickListener(this);

        Intent intent =getIntent();
        username = intent.getStringExtra("username");
        String time = intent.getStringExtra("time");
        tv_user.setText(username);
        tv_time.setText(time);

        MyDBHelper mHelper = MyDBHelper.getInstance(this);
        mHelper.openReadLink();
        mHelper.openWriteLink();
        Clock clock = mHelper.queryByUserName(username);

        int day = clock.day;
        tv_day.setText(day+"");
        int most_day = clock.most_day;
        tv_most_day.setText(most_day+"");
        String key_word = clock.key_word;
        tv_key_word.setText(key_word);
        String summary = clock.summmary;
        tv_summary.setText(summary);

        mHelper.closeLink();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back)
        {
            finish();
        }
    }
}