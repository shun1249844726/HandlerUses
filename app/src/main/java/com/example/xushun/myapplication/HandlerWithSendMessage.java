package com.example.xushun.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author xushun
 *         create at 2018/1/26 15:18
 */
public class HandlerWithSendMessage extends AppCompatActivity {
    private Button downloadBtnh;
    private TextView textView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("handler thread is :"+Thread.currentThread().getId());
            switch (msg.what) {
                case 1:
                    textView.setText("下载完成！"+msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadBtnh = findViewById(R.id.btn_download);
        textView = findViewById(R.id.tv_textview);

        downloadBtnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Main thread is :" + Thread.currentThread().getId());
                DownloadThread downloadThread = new DownloadThread();
                downloadThread.start();
            }
        });
    }

    class DownloadThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("download thread is :"+Thread.currentThread().getId());
            System.out.println("开始下载");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("下载结束");
            Message msg = new Message();
            msg.what = 1;
            msg.obj = "垃圾！";
            handler.sendMessage(msg);

        }
    }
}
