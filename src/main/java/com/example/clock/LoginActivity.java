package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.User;
import com.example.clock.util.ToastUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_name;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);

        findViewById(R.id.btn_zhuce).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_teacher_login).setOnClickListener(this);
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

        String name = et_name.getText().toString();
        String password = et_password.getText().toString();

        User user = null;
        switch(view.getId()){

            case R.id.btn_zhuce:
                Intent intent = new Intent(LoginActivity.this , ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                MyDBHelper mHelper = MyDBHelper.getInstance(this);
                mHelper.openReadLink();
                mHelper.openWriteLink();
                user = mHelper.queryByName(name);
                mHelper.closeLink();

                if(user == null)
                {
                    ToastUtil.show(this, "用户名错误");
                }
                else if(!user.getPassword().equals(password))
                {
                    ToastUtil.show(this, "密码错误");
                }
                else {
                    intent = new Intent(LoginActivity.this, ClockActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }
                break;
            case R.id.btn_teacher_login:
                 intent = new Intent(LoginActivity.this , TeacherLoginActivity.class);
                 startActivity(intent);
                break;
        }
    }


    // 当密码输入框获取焦点之后，根据输入的电话号码，查询出对应的密码，自动填入
//    private void reload() {
//        User info = mHelper.queryTop();
//        if (info != null  ) {
//            et_name.setText(info.name);
//            et_password.setText(info.password);
//        }
//    }
}