package com.baijunyu.testaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * AIDL支持的数据类型：基本数据类型、String和charSequence、list（只支持ArrayList，其元素必须被aidl支持）、map（只支持hashmap，其元素必须被aidl支持）、parcelable、aidl
 * 1.服务端回调监听的（监听要用aidl接口）
 * 2.监听的注册于反注册（RemoteCallbackList-特殊的遍历方式），原理跨进程的本质，同一个binder
 * 3.对调用服务端耗时操作的处理，onServiceConnected和onServiceDisconnected都不可调动服务端耗时操作，服务端也不可开线程
 * 4.运行在binder线程池中，访问UI需要用handler切换到UI线程
 * 5.binder意外死亡的处理方式，deathRecipient监听或者onServiceDisconnected，区别在于一个运行在binder线程池的线程，一个运行在UI线程
 * 6.调用远程服务的权限鉴定：一、onbind中进行验证 二、服务端的onTransact方法中  （方式permission、验证包名等）
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.i(TAG, "新书来了");
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            myHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                mRemoteBookManager = bookManager;
//                List<Book> bookList = bookManager.getBookList();
//                Log.i(TAG, "11111111111111" + bookList.getClass().getCanonicalName());
//                Log.i(TAG, "22222222222222" + bookList.toString());
//                bookManager.addBook(new Book(3, "java"));
//                List<Book> bookList1 = bookManager.getBookList();
//                Log.i(TAG, "33333333333333" + bookList1.getClass().getCanonicalName());
//                Log.i(TAG, "44444444444444" + bookList1.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBookManager = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {

        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }

    public void onButtonClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(mRemoteBookManager!=null){
                    try {
                        List<Book> bookList1 = mRemoteBookManager.getBookList();
                        Log.i(TAG, "55555555555555555" + bookList1.getClass().getCanonicalName());
                        Log.i(TAG, "66666666666666666" + bookList1.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
