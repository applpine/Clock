package com.example.clock.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

import com.example.clock.ennty.Clock;
import com.example.clock.ennty.Time;
import com.example.clock.ennty.User;

import java.util.ArrayList;
import java.util.List;

public  class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user1.db";
    private static final int DB_VERSION = 2;
    private static MyDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static MyDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new MyDBHelper(context);
        }
        return mHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }

        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE IF NOT EXISTS userInfor (" +
                " id INTEGER  NOT NULL," +
                " name VARCHAR NOT NULL," +
                " phone INTEGER NOT NULL," +
                " classroom VARCHAR NOT NULL," +
                " password VARCHAR NOT NULL);";
        db.execSQL(sql);

        sql = " CREATE TABLE IF NOT EXISTS timeInfor (" +
                " id INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " username VARCHAR NOT NULL," +
                " time VARCHAR );";
        db.execSQL(sql);

        sql = " CREATE TABLE IF NOT EXISTS clockInfor (" +
                " id INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " username VARCHAR NOT NULL," +
                " day INTEGER default 0," +
                " most_day INTEGER default 0," +
                " summmary VARCHAR ," +
                " key_word VARCHAR );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.id);
        values.put("name", user.name);
        values.put("phone", user.phone);
        values.put("classroom", user.classroom);
        values.put("password", user.password);
        // 执行插入记录动作，该语句返回插入记录的行号
        // 如果第三个参数values 为Null或者元素个数为0， 由于insert()方法要求必须添加一条除了主键之外其它字段为Null值的记录，
        // 为了满足SQL语法的需要， insert语句必须给定一个字段名 ，如：insert into person(name) values(NULL)，
        // 倘若不给定字段名 ， insert语句就成了这样： insert into person() values()，显然这不满足标准SQL的语法。
        // 如果第三个参数values 不为Null并且元素的个数大于0 ，可以把第二个参数设置为null 。
        //return mWDB.insert(TABLE_NAME, null, values);
        long i = 0;
        i = mWDB.insert("userInfor", null, values);
//        try {
//            mWDB.beginTransaction();
//             i = mWDB.insert("userInfor", null, values);
//            mWDB.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            mWDB.endTransaction();
//        }
        // return i > 0 ? true : false;
        return i;
    }

    public long insertClock(Clock clock) {
        ContentValues values = new ContentValues();
        values.put("username", clock.username);
        values.put("day", clock.day);
        values.put("most_day", clock.most_day);
        values.put("summmary", clock.summmary);
        values.put("key_word", clock.key_word);

        long i = 0;
        i = mWDB.insert("clockInfor", null, values);

        return i;
    }
    public long insertTime(Time time) {
        ContentValues values = new ContentValues();
        values.put("username", time.username);
        values.put("time", time.time);

        long i = 0;
        i = mWDB.insert("timeInfor", null, values);
        return i;
    }
    public long deleteByName(String name) {
        //删除所有
        //mWDB.delete(TABLE_NAME, "1=1", null);
        return mWDB.delete("userInfor", "name=?", new String[]{name});
    }

    public long deleteByUserName(String name) {
        return mWDB.delete("clockInfor", "username=?", new String[]{name});
    }

    public long updateClock(Clock clock) {
        ContentValues values = new ContentValues();
        values.put("username", clock.username);
        values.put("day", clock.day);
        values.put("most_day", clock.most_day);
        values.put("summmary", clock.summmary);
        values.put("key_word", clock.key_word);

        return mWDB.update("clockInfor", values, "username=?", new String[]{clock.username});
    }

    public long update(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.id);
        values.put("name", user.name);
        values.put("phone", user.phone);
        values.put("classroom", user.classroom);
        values.put("password", user.password);

        return mWDB.update("userInfor", values, "name=?", new String[]{user.name});
    }

    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("userInfor", null, null, null, null, null, null);
        // 循环取出游标指向的每条记录
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.phone = cursor.getString(2);
            user.classroom = cursor.getString(3);
            user.password = cursor.getString(4);
            list.add(user);
        }
        return list;
    }


    public List<Time> queryAllTime(String name) {
        List<Time> list = new ArrayList<>();
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("timeInfor", null, "username=?", new String[]{name}, null, null, null);
        // 循环取出游标指向的每条记录
        while (cursor.moveToNext()) {
            Time time = new Time();
            time.id = cursor.getInt(0);
            time.username = cursor.getString(1);
            time.time = cursor.getString(2);
            list.add(time);
        }
        return list;
    }
    public int queryAllTimeNum(String name) {
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("timeInfor", null, "username=?", new String[]{name}, null, null, null);
        // 循环取出游标指向的每条记录
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
        }
        return count;
    }
    public Clock queryByUserName(String name) {
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("clockInfor", null, "username=?", new String[]{name}, null, null, null);
        // 循环取出游标指向的每条记录
        Clock clock = new Clock();
        while (cursor.moveToNext()) {
            clock.id = cursor.getInt(0);
            clock.username = cursor.getString(1);
            clock.day = cursor.getInt(2);
            clock.most_day = cursor.getInt(3);
            clock.summmary = cursor.getString(4);
            clock.key_word = cursor.getString(5);
        }
        return clock;
    }
    public Time queryTimeByName(String username) {
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("timeInfor", null, "username=?", new String[]{username}, null, null, null);
        // 循环取出游标指向的每条记录
        Time time = new Time();
        while (cursor.moveToNext()) {
            time.id = cursor.getInt(0);
            time.username = cursor.getString(1);
            time.time = cursor.getString(2);
        }
        return time;
    }
    public List<Clock> queryClockAll() {
        List<Clock> list = new ArrayList<>();
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("clockInfor", null, null, null, null, null, null);
        // 循环取出游标指向的每条记录
        while (cursor.moveToNext()) {
            Clock clock = new Clock();
            clock.id = cursor.getInt(0);
            clock.username = cursor.getString(1);
            clock.day = cursor.getInt(2);
            clock.most_day = cursor.getInt(3);
            clock.summmary = cursor.getString(4);
            clock.key_word = cursor.getString(5);

            list.add(clock);
        }
        return list;
    }

    public User queryByName(String name) {
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mRDB.query("userInfor", null, "name=?", new String[]{name}, null, null, null);
        // 循环取出游标指向的每条记录
        User user = new User();
        while (cursor.moveToNext()) {
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.phone = cursor.getString(2);
            user.classroom = cursor.getString(3);
            user.password = cursor.getString(4);
        }
        return user;
    }

}