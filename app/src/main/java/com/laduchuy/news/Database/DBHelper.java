package com.laduchuy.news.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME ="DOCBAOONLINE.db";
    public static String TABLE_NAME = "OfflineItemRSS";
    public static String Column_Title ="title";
    public static String Column_DESCRIPTION ="description";
    public static String Column_CONTENT ="content";
    public static String Column_URLIMG ="urlImg";
    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + Column_Title + " TEXT, "
            + Column_DESCRIPTION + " TEXT, "
            + Column_CONTENT + " TEXT, "
            + Column_URLIMG + " TEXT "
            + ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

}
