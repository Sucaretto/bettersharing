package us.moenew.RegexClipboard;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DataCopy(this);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("echo", "数据复制失败了");
        }

    }

    public void DataCopy(Context context) throws IOException {
        String dbName = "Regex.db";
        File dbPath = getDatabasePath(dbName);

        if (dbPath.exists()) {
            // 数据库已经存在，无需复制
            return;
        }

        InputStream asFile = context.getAssets().open(dbName);//打开assets目录的文件
        OutputStream dbFile = new FileOutputStream(dbPath);//打开应用目录的文件

        byte[] buffer = new byte[1024];
        int length;
        while ((length = asFile.read(buffer)) > 0) {
            dbFile.write(buffer, 0, length);
        }
        dbFile.flush();
        dbFile.close();
        asFile.close();

    }

}