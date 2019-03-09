package com.example.fruitrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    Button start;
    Button stop;
    RadioButton pth1;
    RadioButton pth2;
    RadioButton pth3;
    DataOutputStream os=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.btn_start);
        stop=findViewById(R.id.btn_stop);
        pth1=findViewById(R.id.rbtn_pth1);
        pth2=findViewById(R.id.rbtn_pth2);
        pth3=findViewById(R.id.rbtn_pth3);

//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            private String inpath;
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                System.out.println(checkedId);
//                System.out.println(R.id.rbtn_pth1);
//                switch(checkedId){
//                    case R.id.rbtn_pth1:
//                        inpath="path1";
//                    case R.id.rbtn_pth2:
//                        inpath="path2";
//                    case R.id.rbtn_pth3:
//                        inpath="path3";
//                }
//            }
//            public RadioGroup.OnCheckedChangeListener accept(String str)
//            {
//                this.inpath=str;
//                return this;
//            }
//
//        }.accept(path));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(false);
                stop.setEnabled(true);
                pth1.setEnabled(false);
                pth2.setEnabled(false);
                pth3.setEnabled(false);
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
                            if(pth1.isChecked())
                                os.writeUTF("start path1" );
                            if(pth2.isChecked())
                                os.writeUTF("start path2");
                            if(pth3.isChecked())
                                os.writeUTF("start path3");
                            os.close();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                start.setEnabled(true);
                pth1.setEnabled(true);
                pth2.setEnabled(true);
                pth3.setEnabled(true);
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
                            os.writeUTF("stop");
                            os.close();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


    }



}
