package com.baijunyu.annotations.annotationsexam02;

import org.androidannotations.annotations.EBean;

/**
 * Created by 95190 on 2018/6/25.
 */
//单例类需要如下声明
//注意：在单例类里面不可以注入view和事件绑定，因为单例的生命周期比Activity和Service的要长，以免发生内存溢出。
@EBean(scope = EBean.Scope.Singleton)
public class SingleTest {
}
