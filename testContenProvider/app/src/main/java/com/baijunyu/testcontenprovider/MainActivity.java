package com.baijunyu.testcontenprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "com.baijunyu.testcontenprovider.BookProvider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",6);
        contentValues.put("name","程序设计的艺术");
        getContentResolver().insert(BOOK_CONTENT_URI,contentValues);
        Cursor cursor = getContentResolver().query(BOOK_CONTENT_URI, new String[]{"_id","name"}, null, null, null, null);

        while (cursor.moveToNext()){
            Book book = new Book();
            book.set_id(cursor.getInt(0));
            book.setName(cursor.getString(1));
            Log.i("query","1111111111111111111"+book.getName()+"::"+book.get_id());
        }
        cursor.close();
    }
}
