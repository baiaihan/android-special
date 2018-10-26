package com.baijunyu.annotations.exam01;

import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

/**
 * Created by 95190 on 2018/6/20.
 */

public class TestRes {


    public void setString(@StringRes int id) {
        System.out.println("---------------------");
    }

    public void setColor(@ColorRes int id) {
        System.out.println("---------------------");
    }

    public void setIds(@IdRes int id) {
        System.out.println("---------------------");
    }
}
