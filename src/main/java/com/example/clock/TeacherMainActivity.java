package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.clock.database.MyDBHelper;
import com.example.clock.ennty.Clock;
import com.example.clock.ennty.User;

import java.util.List;

public class TeacherMainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Clock> resulList;
    private MyAdapter myAdapter;
    private List<User> resulList_user;
    private ListView listView;
    private EditText et_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        listView = findViewById(R.id.listview);
        et_word = findViewById(R.id.et_word);
        findViewById(R.id.btn_find).setOnClickListener(this);;
        init();
    }
    @Override
    public void onClick(View view) {
        String  word = et_word.getText().toString();
        if(view.getId() == R.id.btn_find)
        {
            Intent intent = new Intent(TeacherMainActivity.this , TeacherUserActivity.class);
            startActivity(intent);
        }
    }
    private void init()
    {
        if(resulList != null || resulList_user != null)
        {
            resulList.clear();
            resulList_user.clear();
        }
        MyDBHelper mHelper = MyDBHelper.getInstance(this);
        mHelper.openReadLink();
        mHelper.openWriteLink();
        resulList = mHelper.queryClockAll();
        resulList_user = mHelper.queryAll();
        //System.out.println(resulList);
        mHelper.closeLink();

        myAdapter = new MyAdapter(TeacherMainActivity.this , resulList,resulList_user);
        listView.setAdapter(myAdapter);

    }


}