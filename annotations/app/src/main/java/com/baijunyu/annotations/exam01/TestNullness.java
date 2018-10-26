package com.baijunyu.annotations.exam01;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 95190 on 2018/6/20.
 */

public class TestNullness {
    public void test1() {
        testo(null);
        testc(null);

    }

    //@NonNull指明一个参数，字段或者方法的返回值不可以为null；
    @CheckResult
    public String testo(@Nullable String a) {
        System.out.println("--------------" + a);
        return a;
    }
    @CheckResult
    public String testc(@NonNull String b) {
        System.out.println("--------------" + b);
        return b;
    }
}
