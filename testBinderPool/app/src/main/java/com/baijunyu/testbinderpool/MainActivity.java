package com.baijunyu.testbinderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ISecurityCenter security;
    ICompute compute;
    BinderPool bindPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //新开线程，进行连接，因为连接有可能是耗时的，会阻塞UIThread，不信自己去试
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                //连接工作都在这一步
                bindPool = BinderPool.getsInstance(MainActivity.this);

            }
        }).start();
    }

    public void dowork(View view) {
        doworks();
    }
    private void doworks() {
        //根据唯一标识，获取需要的Binder
        IBinder securityBinder = bindPool.queryBinderByCode(BinderPool.BINDER_SECURITY_CENTER);

        security = ISecurityCenter.Stub.asInterface(securityBinder);
        System.out.println("do work......"+(security==null));
        if(security==null){
            return;
        }
        try {
            String msg = "bind pool";
            String password = security.encrypt(msg);
            System.out.println("encypt:" + password + ",decypt:" + security.decrypt(password));

        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        IBinder computeBinder = bindPool.queryBinderByCode(BinderPool.BINDER_COMPUTE);
        compute = ICompute.Stub.asInterface(computeBinder);
        System.out.println("compute......"+(compute==null));
        if(compute==null){
            return;
        }
        try {
            System.out.println(" compute:" + compute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

