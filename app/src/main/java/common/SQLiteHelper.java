package common;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FUSHUAI on 2017/9/19.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "myTest.db";
    private final static int VERSION = 1;
    private SQLiteDatabase db;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /* 初始化数据  */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table account(_id integer primary key autoincrement,UserName varchar(20),Phone varchar(20),Password varchar(50) ) ");
    }

    /*  升级操作 */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
