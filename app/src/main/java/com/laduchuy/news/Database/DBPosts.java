package com.laduchuy.news.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.laduchuy.news.ClassObject.Post;

import java.util.ArrayList;

public class DBPosts extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "data_posts";
    private static final String TABLE_NAME = "posts";

    private static final String POST_ID = "post_id";
    private static final String POST_TITLE = "post_title";
    private static final String POST_DESC = "post_desc";
    private static final String POST_THUMB = "post_thumb";
    private static final String POST_CONTENT = "post_content";
    private static final String CATEGORY_ID = "category_id";

    private Context context;


    public DBPosts(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                POST_ID + " integer primary key, " +
                POST_TITLE + " TEXT," +
                POST_DESC + " TEXT, " +
                POST_THUMB + " TEXT," +
                POST_CONTENT + " TEXT," +
                CATEGORY_ID + " INTEGER" + ")";

        sqLiteDatabase.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly" + sqlQuery, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public long addPost(Post post){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("POST_ID",post.getPost_id());
        contentValues.put("POST_TITLE",post.getPost_title());
        contentValues.put("POST_DESC",post.getPost_desc());
        contentValues.put("POST_CONTENT",post.getPost_content());
        contentValues.put("CATEGORY_ID",post.getCategory_id());

        long ok = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return ok;
    }

    public ArrayList<Post> getAllPosts(){
        ArrayList<Post> getAllPosts = new ArrayList<>();

        String sql = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Post post = new Post();
                post.setPost_id(cursor.getInt(0));
                post.setPost_title(cursor.getString(1));
                post.setPost_desc(cursor.getString(2));
                post.setPost_content(cursor.getString(3));
                post.setCategory_id(cursor.getInt(4));

                getAllPosts.add(post);

            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return getAllPosts;
    }

}
