package us.moenew.bettersharing;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) handleSendText(intent); // 处理发送来的文字
        }
        finish();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);//分享的文本

        if (sharedText != null) {
            String Pixiv = ".*pixiv";
            String Net_song = "^分享.*的单曲";
            String Net_lrc = "^分享歌词";
            //boolean b= Pattern.compile("id=[0-9]*").matcher(sharedText).matches();
            //Matcher app = Pattern.compile("id=[0-9]*").matcher(sharedText);
            //Pattern p = Pattern.compile(Pixiv);
            //Matcher m = p.matcher(sharedText);
            //boolean isMatch = Pattern.matches(Net_lrc, sharedText);
            //m.find();
            //Log.d("echo", "" + isMatch);
            //Log.d("echo", "" + Pattern.compile(Pixiv).matcher(sharedText).find());
            if (Pattern.compile(Pixiv).matcher(sharedText).find()) {//内容分析
                CatString(sharedText, "id=\\d*");

            } else if (Pattern.compile(Net_song).matcher(sharedText).find()) {
                CatString(sharedText, "http.*\\/song\\/\\d*");

            } else if (Pattern.compile(Net_lrc).matcher(sharedText).find()) {
                CatString(sharedText, "^.*。");

            } else {
                CatString(sharedText, null);
            }
        } else {
            Toast.makeText(this, "分享内容为空！", Toast.LENGTH_SHORT).show();
        }
    }

    void CatString(String sharedText, String regex) {//内容处理
        String str;
        if (regex != null) {
            Matcher m = Pattern.compile(regex, Pattern.MULTILINE).matcher(sharedText);//开启多行模式匹配

            if (m.find()) {
                str = m.group();//所有匹配成功的内容
            } else {
                str = sharedText;//规则无法匹配到内容
            }

        } else {
            str = sharedText;//无适用规则
        }

        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);//获取剪贴板管理器：
        ClipData mClipData = ClipData.newPlainText("Label", str);// 创建普通字符型ClipData
        cm.setPrimaryClip(mClipData);// 将内容放到剪贴板里

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Log.d("echo", "|" + str);

    }
}
