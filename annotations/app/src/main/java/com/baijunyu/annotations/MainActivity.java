package com.baijunyu.annotations;

import android.Manifest;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.baijunyu.annotations.annotationsexam02.Item;
import com.baijunyu.annotations.annotationsexam02.ItemListAdapter;
import com.baijunyu.annotations.annotationsexam03.TestBasic;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EActivity(R.layout.activity_main)
@Fullscreen

public class MainActivity extends AppCompatActivity {
    @App
    MainApplication application;
    @ViewById(R.id.listview)
    ListView listView;
    @Bean
    ItemListAdapter adapter;

    //一定要在这里进行view的一些设置，不要在oncreate()中设置，因为oncreate()在执行时 view还没有注入

    @AfterViews
    void init() {
        Log.i("annotations", "-----------annotations------------onCreate");
        Toast.makeText(application, "annotations test!", Toast.LENGTH_SHORT).show();
        listView.setAdapter(adapter);
        initData();
        //test
        setAlpha(235, 1.31245674f);
        setStringName("1234567890");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        setHashMap(stringStringHashMap);
        int[] ints = new int[1];
        setArrary(ints);
        int[] int2 = new int[2];
        setArrary2(int2);
        List<Object> list = new ArrayList<Object>();
        setList(list);
        //test
        setTitle(R.string.app_name);

       // pickPhone("12345678910");
        new TestBasic();
        new MyFragment_();
    }


    //---------------------------------数值约束注解------------------------------------------------

    /**
     * 约束int类型\float类型数值范围
     *
     * @param alpha
     */
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha, @FloatRange(from = 1.0, to = 2.0) float press) {

    }

    /**
     * @Size 用来约束 集合、数组或者字符串的长度
     * 四种用法：
     * - 限定最小值 (such as @Size(min=2))
     * - 限定最大值 (such as @Size(max=2))
     * - 必须为某个值 (such as @Size(2))
     * - 必须是某个值的倍数 (such as @Size(multiple=2))
     */
    public void setStringName(@Size(max = 10)String name){

    }
    public void setHashMap(@Size(min = 100)Map<String,String> map){

    }
    public void setArrary(@Size(1) int[] location){

    }
    public void setArrary2(@Size(multiple=2) int[] location){

    }
    public void setList(@Size(min=2) List<Object> list){

    }

    //---------------------------------线程注解------------------------------------------
    /**
     * 线程注解用来表明某个方法运行在特定线程。所有线程标签如下：
     - @MainThread
     - @UiThread
     - @WorkerThread
     - @BinderThread
     - @AnyThread
     */
    @WorkerThread
    public void setTitle(@StringRes int id){

    }
    //-------------------------------权限注解---------------------------------------------

    /**
     * 使用@RequiresPermission注解表明某方法的执行需要某（些）权限。
     * 单个权限
     * 多个权限
     */
    @RequiresPermission(Manifest.permission.ANSWER_PHONE_CALLS)
    public void pickPhone(@Size(11) String  phoneNum) throws IOException{

    }
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static final void copyFile(String dest, String source) {

    }




    private void initData() {
        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < 20; i++) {
            items.add(new Item("标题" + i, "正文" + i + "\n长按删除", R.drawable.ic_launcher));
        }

        adapter.update(items);
    }

    /**
     * 名字必须是这个
     */
    @ItemClick
    void listviewItemClicked(Item item) {
        Toast.makeText(this, "点击了" + item.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 名字必须是这个
     */
    @ItemLongClick
    void listviewItemLongClicked(Item item) {
        adapter.delete(item);
    }

    @ItemSelect
    public void listviewItemSelected(boolean selected, int position) {
        Toast.makeText(this, "选择了" + position, Toast.LENGTH_SHORT).show();
    }

}
