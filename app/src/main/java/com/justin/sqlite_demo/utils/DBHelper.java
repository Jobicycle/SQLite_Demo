package com.justin.sqlite_demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.justin.sqlite_demo.model.Student;


public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE + "("
                + Student.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Student.KEY_NAME + " TEXT, "
                + Student.KEY_AGE + " INTEGER, "
                + Student.KEY_EMAIL + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);
        onCreate(db);
    }
}