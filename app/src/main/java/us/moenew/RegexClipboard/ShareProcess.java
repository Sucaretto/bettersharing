package us.moenew.RegexClipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShareProcess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(intent.getAction()) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent.getStringExtra(Intent.EXTRA_TEXT)); // 处理发送来的文字
            }
        }
        finish();
    }

    void handleSendText(String sharedText) {
        if (sharedText != null) {
            SQLiteDatabase db = new DbController(this).getWritableDatabase();
            Cursor cursor = db.query("Data", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                String rx_replace = null;
                do {
                    String rx_find = cursor.getString((cursor.getColumnIndex("Rx_find")));
                    if (Pattern.compile(rx_find).matcher(sharedText).find()) {//根据规则查找内容
                        rx_replace = cursor.getString((cursor.getColumnIndex("Rx_replace")));
                        break;
                    }
                } while (cursor.moveToNext());
                cursor.close();
                CatString(sharedText, rx_replace);
            }
        } else {
            Toast.makeText(this, "分享内容为空！", Toast.LENGTH_SHORT).show();
            Log.d("echo", "分享内容为空");
        }
    }

    void CatString(String sharedText, String regex) {//内容处理
        String str;
        if (regex != null) {
            Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(sharedText);//开启多行模式匹配
            str = matcher.find() ? matcher.group() : sharedText;
        } else {
            Log.d("echo", "没有匹配规则");
            str = sharedText;
        }

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);//获取剪贴板管理器：
        ClipData mClipData = ClipData.newPlainText("Label", str);// 创建普通字符型ClipData
        clipboardManager.setPrimaryClip(mClipData);// 将内容放到剪贴板里

        Log.d("echo", str);

    }
}
