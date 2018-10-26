package com.baijunyu.annotations;

import android.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by 95190 on 2018/6/25.
 */
@EFragment(R.layout.fragment_car)
public class MyFragment extends Fragment {
    @ViewById(R.id.tv)
    TextView myTextView;
    @App
    MainApplication application;
//AfterInject在构造方法执行完成后执行
    @AfterInject
    void calledAfterInjection() {
        Log.i("fragment","------------1-------------");
    }

    @AfterViews
    void calledAfterViewInjection() {
        Log.i("fragment","------------2-------------");
    }
}
