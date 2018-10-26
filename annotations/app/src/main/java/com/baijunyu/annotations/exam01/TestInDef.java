package com.baijunyu.annotations.exam01;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 95190 on 2018/6/15.
 */

public class TestInDef {
    /**
     * 声明一些必要的 int 常量
     */
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;

    /**
     * 声明一个注解为 WeekDays
     */
    @IntDef({SUNDAY, MONDAY})//使用 @IntDef 修饰 WeekDays,参数设置为待枚举的集合
    @Retention(RetentionPolicy.SOURCE)
//使用 @Retention(RetentionPolicy.SOURCE) 指定注解仅存在与源码中,不加入到 class 文件中
    public @interface WeekDays {

    }

    /**
     * 制定整型值作为标志位
     */
    @IntDef(flag = true, value = {SUNDAY, MONDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flavour {

    }

    /**
     * 成员变量的写法
     */
    @WeekDays
    int currentDay = MONDAY;
    @Flavour
    int currentDayf = MONDAY;


    public void setCurrentDay(@WeekDays int currentDay) {
        this.currentDay = currentDay;

    }

    public void setCurrentDayF(@Flavour int currentDay) {
        this.currentDayf = currentDay;

    }

    /**
     * “@WeekDays”返回值为限制取值
     *
     * @return
     */
    @WeekDays
    public int getCurrentDay() {
        return this.currentDay;
    }
    @Flavour
    public int getCurrentDayf() {
        return this.currentDay;
    }

    public void test1() {
        setCurrentDay(SUNDAY);
        setCurrentDayF(SUNDAY&MONDAY);

        int currentDay = getCurrentDay();
        System.out.println("--------------------------" + (SUNDAY&MONDAY));

        int currentDayf = getCurrentDayf();
        System.out.println("--------------------------" + currentDayf);
    }
}
