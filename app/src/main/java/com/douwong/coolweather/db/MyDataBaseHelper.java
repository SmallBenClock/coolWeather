package com.douwong.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zds .
 * on 2016/10/27  08:50
 * 描述:
 * 包名: com.douwong.coolweather.db
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {


    String Creat_Book = "create table book (id integer primary key autoincrement,author text,price real,pages integer ,name text)";

    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Creat_Book);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
