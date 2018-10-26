package com.baijunyu.testipcsocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by 95190 on 2018/9/11.
 */

public class TcpServerService extends Service {
    private boolean mIsServiceDestoryed = false;
    private String[] mDefindMessages = new String[]{"你好啊，哈哈", "请问你叫什么名字啊", "今天北京天气不错啊", "你知道吗我是可以同时和多个人聊天", "给你讲个笑话"};

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable {
        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                responserClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responserClient(Socket client) throws IOException {
        //用于接收客户端的消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.print("欢迎来到聊天室");
        while (!mIsServiceDestoryed) {
            String s = in.readLine();
            if (s == null) {
                //客户端断开连接
                break;
            }
            int i = new Random().nextInt(mDefindMessages.length);
            String mDefindMessage = mDefindMessages[i];
            out.print(mDefindMessage);
        }
        in.close();
        out.close();
        client.close();
    }
}
