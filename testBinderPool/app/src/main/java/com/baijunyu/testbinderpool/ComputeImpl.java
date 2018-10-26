package com.baijunyu.testbinderpool;

import android.os.RemoteException;

/**
 * Created by 95190 on 2018/9/12.
 */

public class ComputeImpl extends ICompute.Stub{
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
