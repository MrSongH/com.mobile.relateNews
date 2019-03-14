package com.example.hjh.comshkmobile;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjh.comshkmobile.recommendNews.recommendNews;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    TextView text1, text2, text3, text4, text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recommendNews recommendNews = new recommendNews();

        settingLayout();
        /* Layout 설정 */

        recommendNews.calcNewsContents();
        int titleSize = recommendNews.titleList.size();
        for (int i = 0; i < titleSize; i++) {
            String newsTitle = recommendNews.titleList.get(i);
            String newsContents = recommendNews.contentList.get(i);
            settingText(i, newsTitle, newsContents);
        }

    }

    private void settingText(int i, String title, String contents) {
        switch (i) {
            case 0:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 1:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 2:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 3:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 4:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 5:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
            case 6:
                text1.setText(title + "\n" + contents.substring(0, 40));
                break;
        }
    }

    private void settingLayout() {
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        text1 = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        text6 = (TextView) findViewById(R.id.textView6);
        text7 = (TextView) findViewById(R.id.textView7);
        return;
    }
}
