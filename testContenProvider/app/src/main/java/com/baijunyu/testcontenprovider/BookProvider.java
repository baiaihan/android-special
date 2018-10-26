package com.baijunyu.testcontenprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Android中自带的ContentProvider包括：
 * 1.Browser：存储如浏览器的信息。
 * 2.CallLog：存储通话记录等信息。
 * 3.Contacts Provider：存储联系人(通讯录)等信息。
 * 4.MediaStore：存储媒体文件的信息。
 * 5.Settings：存储设备的设置和首选项信息。
 * 6.日历.
 *
 * 除了支持固定的CRUD操作，还支持自定义调用，通过call方法
 */

public class BookProvider extends ContentProvider {

    public static final String TAG = "BookProvider";
    //urimatcher
    public static final String AUTHORITY = "com.baijunyu.testcontenprovider.BookProvider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");
    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private SQLiteDatabase mDb = null;
    private Context mContext = null;

    //运行在UI线程中，不能做耗时操作
    @Override
    public boolean onCreate() {
        mContext = getContext();
        initProviderData();
        return true;
    }

    //CURD运行在binder线程池中
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Log.i(TAG, "11111111111111111111111111111111");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported uri:" + uri);
        }
        return mDb.query(table, strings, s, strings1, null, null, s1, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported uri:" + uri);
        }
        mDb.insert(table, null, contentValues);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported uri:" + uri);
        }
        int count = mDb.delete(table, s, strings);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported uri:" + uri);
        }
        int row = mDb.update(table, contentValues, s, strings);
        if(row>0){
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    /**
     * 根据uri获取要操作的数据库表名称
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }

    /**
     * 初始化数据库数据
     */
    public void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3,'android');");
        mDb.execSQL("insert into book values(4,'java');");
        mDb.execSQL("insert into book values(5,'ios');");
        mDb.execSQL("insert into user values(1,'xiaoming',1);");
        mDb.execSQL("insert into user values(2,'zhangsan',0);");
    }
}
