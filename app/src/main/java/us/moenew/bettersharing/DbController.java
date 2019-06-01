package us.moenew.bettersharing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Regex.db";
    private static final int DATABASE_VERSION = 1;

    public DbController(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not  exists Data"
                        + "("
                        + "id integer primary key autoincrement,"
                        + "APP_name text,"
                        + "Rx_find text,"
                        + "Rx_replace text"
                        + ")"
        );
        Log.d("echo", "数据库创建成功！");
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }

}
