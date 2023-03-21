package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.Clock;
import com.example.clock.ennty.User;
import com.example.clock.util.ToastUtil;

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_id;
    private EditText et_phone;
    private EditText et_classroom;
    private EditText et_new_password;

    //private MyDBHelper mHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);

        et_name = findViewById(R.id.et_name);
        et_id = findViewById(R.id.et_id);
        et_phone = findViewById(R.id.et_phone);
        et_classroom = findViewById(R.id.et_classroom);
        et_new_password = findViewById(R.id.et_new_password);

        findViewById(R.id.btn_confirm).setOnClickListener(this);

    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // 获得数据库帮助器的实例
//        mHelper = MyDBHelper.getInstance(this);
//        // 打开数据库帮助器的读写连接
//        mHelper.openWriteLink();
//        mHelper.openReadLink();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        // 关闭数据库连接
//        mHelper.closeLink();
//    }

    @Override
    public void onClick(View view) {
        String id = et_id.getText().toString();
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String classroom = et_classroom.getText().toString();
        String password = et_new_password.getText().toString();

        User user = null;
        Clock clock = null;
        switch (view.getId()) {
            case R.id.btn_confirm:
                // 以下声明一个用户信息对象，并填写它的各字段值
                user = new User(
                        Integer.parseInt(id),
                        name,
                        phone,
                        classroom,
                        password);
                clock = new Clock(
                        user.name,
                        0,
                        0,
                        null,
                        null
                );
                MyDBHelper mHelper = MyDBHelper.getInstance(this);
                mHelper.openReadLink();
                mHelper.openWriteLink();

                if (mHelper.insert(user) > 0 && mHelper.insertClock(clock) > 0) {
                    ToastUtil.show(this, "添加成功");
                }
                mHelper.closeLink();
                finish();
                break;
        }
    }
}