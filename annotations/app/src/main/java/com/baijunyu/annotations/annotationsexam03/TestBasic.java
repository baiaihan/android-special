package com.baijunyu.annotations.annotationsexam03;

import android.util.Log;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

/**
 * Created by 95190 on 2018/6/25.
 */
@EBean
public class TestBasic extends Basic {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @AfterInject
    public void init(){
        Log.i("test","------------AfterInject-------------");
    }
}
