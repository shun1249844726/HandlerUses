package com.example.xushun.myapplication;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * handler 使用方法一
 * 使用 handler.post(Runnable)
 *
 * @author 徐顺
 * @date 2016/10/31
 */
public class HandlerWithRunnable extends AppCompatActivity {

    private Button downloadBtnh;
    private TextView textView;
    private  Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadBtnh = findViewById(R.id.btn_download);
        textView = findViewById(R.id.tv_textview);

        downloadBtnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Main thread is :"+Thread.currentThread().getId());
                DownloadThread mThread = new DownloadThread();
                mThread.start();
            }
        });
    }
    class  DownloadThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("download thread is :"+Thread.currentThread().getId());
            System.out.println("开始下载文件");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("文件下载成功！");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable thread is "+Thread.currentThread().getId());
                    System.out.println("通知界面更新");
                    textView.setText("下载成功");
                }
            };

            mHandler.post(runnable);
        }
    }

}
