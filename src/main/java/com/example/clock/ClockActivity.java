package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.Clock;
import com.example.clock.ennty.Time;
import com.example.clock.ennty.User;
import com.example.clock.util.DateUtil;
import com.example.clock.util.ToastUtil;

import java.util.List;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_time;
    private TextView tv_user;
    private TextView tv_day;
    private TextView tv_most_day;
    private EditText et_key_word;
    private EditText et_summary;
    private String username;
    private String time;
    private int day;
    private int most_day;
    private int flag = 1;
    Clock clock = null;
    Time time1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        tv_time = findViewById(R.id.tv_time);
        tv_user = findViewById(R.id.tv_user);
        tv_day = findViewById(R.id.tv_day);
        tv_most_day = findViewById(R.id.tv_most_day);
        et_key_word = findViewById(R.id.et_key_word);
        et_summary = findViewById(R.id.et_summary);

        findViewById(R.id.btn_clock).setOnClickListener(this);
        findViewById(R.id.btn_book).setOnClickListener(this);


        Intent intent =getIntent();
        username = intent.getStringExtra("username");
        tv_user.setText(username);

        time = String.format("%s",DateUtil.getCurrentDate());
        tv_time.setText(time);

        MyDBHelper mHelper = MyDBHelper.getInstance(this);
        mHelper.openReadLink();
        mHelper.openWriteLink();
        clock = mHelper.queryByUserName(username);

        day = clock.day;
        tv_day.setText(day+"");
        most_day = clock.most_day;
        tv_most_day.setText(most_day+"");

        mHelper.closeLink();
    }

    @Override
    public void onClick(View view) {
        String  key_word = et_key_word.getText().toString();
        String  summary = et_summary.getText().toString();

        switch (view.getId()) {
            case R.id.btn_clock:
                MyDBHelper mHelper = MyDBHelper.getInstance(this);
                mHelper.openReadLink();
                mHelper.openWriteLink();

                List<Time> list = mHelper.queryAllTime(username);
                int i =  mHelper.queryAllTimeNum(username);

//                System.out.println(list);
//                System.out.println(i);
                for(  int j = 0;  j<i; j++)
                {
//                    System.out.println("else3");
//                    System.out.println(i);
                    if(list.get(j).time.equals(time))
                    {
                        flag = 0;
                    }
                }

                if (flag == 1)
                {
                    clock = new Clock(
                            username,
                            day + 1,
                            most_day + 1,
                            summary,
                            key_word);
                    time1 = new Time(
                            time,
                            username
                    );
                    if (mHelper.updateClock(clock) > 0 && mHelper.insertTime(time1) > 0) {
                        ToastUtil.show(this, "打卡成功");
                    }
                }
                else if (flag == 0)
                {
                    ToastUtil.show(this, "打卡已存在");
                }
//                if (mHelper.updateClock(clock) > 0 && mHelper.insertTime(time1) > 0) {
//                    ToastUtil.show(this, "打卡成功");
//                }
                mHelper.closeLink();
                Intent intent = new Intent(ClockActivity.this , ClockActivity2.class);
                intent.putExtra("username",username);
                intent.putExtra("time",time);
                startActivity(intent);
                break;
            case R.id.btn_book:
                intent = new Intent(ClockActivity.this , BookActivity.class);
                startActivity(intent);
                break;
        }
    }

}