package us.moenew.bettersharing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String Table_name = "Data";
//        DbController dbHelper = new DbController(this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("APP_name", "Pixiv分享图片");
//        values.put("Rx_find", ".*pixiv");
//        values.put("Rx_replace", "id=\\d*");
//        db.insert(Table_name,null,values);
//        values.clear();
//
//        values.put("APP_name", "网易云分享歌曲");
//        values.put("Rx_find", "^分享.*的单曲");
//        values.put("Rx_replace", "http.*\\/song\\/\\d*");
//        db.insert(Table_name,null,values);
//        values.clear();
//
//        values.put("APP_name", "网易云分享歌词");
//        values.put("Rx_find", "^分享歌词");
//        values.put("Rx_replace", "^.*。");
//        db.insert(Table_name,null,values);
        //values.clear();

    }
}