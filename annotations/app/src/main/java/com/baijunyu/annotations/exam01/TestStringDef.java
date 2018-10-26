package com.baijunyu.annotations.exam01;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 95190 on 2018/6/15.
 */

public class TestStringDef {
    /**
     * 声明一些必要的 int 常量
     */
    public static final String SUNDAY = "星期天";
    public static final  String MONDAY = "星期一";

    /**
     * 声明一个注解为 WeekDays
     */
    @StringDef({SUNDAY, MONDAY})//使用 @StringDef 修饰 WeekDays,参数设置为待枚举的集合
    @Retention(RetentionPolicy.SOURCE)//使用 @Retention(RetentionPolicy.SOURCE) 指定注解仅存在与源码中,不加入到 class 文件中
    public @interface WeekDays {

    }

    /**
     * 成员变量的写法
     */
    @WeekDays
    String currentDay = MONDAY;


    public void setCurrentDay(@WeekDays String currentDay) {
        this.currentDay = currentDay;

    }

    /**
     * “@WeekDays”返回值为限制取值
     *
     * @return
     */
    @WeekDays
    public String getCurrentDay() {
        return this.currentDay;
    }

    public void test2() {
        setCurrentDay(SUNDAY);

        String currentDay = getCurrentDay();
        System.out.println("--------------------------" + currentDay);
    }
}
