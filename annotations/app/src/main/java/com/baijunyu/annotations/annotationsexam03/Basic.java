package com.baijunyu.annotations.annotationsexam03;

import android.support.annotation.CallSuper;
import android.util.Log;

/**
 * Created by 95190 on 2018/6/25.
 */

public class Basic {
    @CallSuper
    public void onCreate() {
        Log.i("basic", "_---------------父类方法--------------_------");
    }
}
