package com.example.fruitrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    TextView textview;
    DataOutputStream os=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=findViewById(R.id.textView2);
    }


    public void myClick(View view) //按钮按下事件的内容
    {
        String str = "1";
        textview.setText(str);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //电脑wifi ip地址
                    String host = "172.20.10.2";  //要连接的服务端IP地址
                    int port = 9000;   //要连接的服务端对应的监听端口
                    //与服务端建立连接
                    Socket client = new Socket(host, port);
                    os = new DataOutputStream(client.getOutputStream());
                    os.writeUTF("hello");
                    os.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
