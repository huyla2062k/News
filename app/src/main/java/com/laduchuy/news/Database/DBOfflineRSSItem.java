package com.laduchuy.news.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.laduchuy.news.ClassObject.OfflineRSSItem;

import java.util.ArrayList;
import java.util.List;


public class DBOfflineRSSItem {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBOfflineRSSItem(Context context) {
        helper = new DBHelper(context);
    }

    public long Insert(OfflineRSSItem itemsRss) {

        db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.Column_Title, itemsRss.getTitle());
        contentValues.put(DBHelper.Column_DESCRIPTION, itemsRss.getDescription());
        contentValues.put(DBHelper.Column_CONTENT, itemsRss.getContent());
        contentValues.put(DBHelper.Column_URLIMG, itemsRss.getUrlImg());

        long ok = db.insert(DBHelper.TABLE_NAME, null, contentValues);
        db.close();
        return ok;
    }


    public List<OfflineRSSItem> getAlLOffLineItemRss()
    {
        List<OfflineRSSItem> list = new ArrayList();
        String sql = "SELECT * FROM " + DBHelper.TABLE_NAME;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        for(cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext())
        {
            Log.d("DB", "title: " + cursor.getString(0));
            list.add(new OfflineRSSItem(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }

        return list;
    }

    public void delete(OfflineRSSItem itemsRss) {
        db = helper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME, DBHelper.Column_Title + " = ?",   new String[] { String.valueOf(itemsRss.getTitle()) });
        db.close();
    }

    public void update(OfflineRSSItem itemsRss)
    {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.Column_Title,itemsRss.getTitle());
        values.put(DBHelper.Column_DESCRIPTION,itemsRss.getDescription());
        values.put(DBHelper.Column_CONTENT,itemsRss.getContent());
        values.put(DBHelper.Column_URLIMG,itemsRss.getUrlImg());
        db.update(DBHelper.TABLE_NAME,values,DBHelper.Column_Title + " = ?", new String[]{String.valueOf(itemsRss.getTitle())});
        db.close();
    }
}
