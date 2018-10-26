package com.baijunyu.annotations.annotationsexam02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * listview的适配器，增加了update，append，delete方法，数据更新的时候调用。
 *
 * 注意：内部的datas不要直接使用外面的引用，容易出问题，不要养成坏习惯。
 */


@EBean
public class ItemListAdapter extends BaseAdapter
{

    @RootContext
    Context context;

    private ArrayList<Item> datas;
    //在类创建时期做一些操作
    @AfterInject
    void init()
    {
        this.datas = new ArrayList<Item>();
    }

    @Override
    public int getCount()
    {
        return this.datas.size();
    }

    @Override
    public Item getItem(int position)
    {
        return this.datas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemView view = null;

        if (convertView == null)
        {
            view = ItemView_.build(context);
        }
        else
        {
            view = (ItemView) convertView;
        }

        view.bind(getItem(position));

        return view;
    }

    public void update(List<Item> items)
    {
        this.datas.clear();
        this.datas.addAll(items);

        notifyDataSetChanged();
    }

    public void append(List<Item> items)
    {
        this.datas.addAll(items);

        notifyDataSetChanged();
    }

    public void delete(Item item)
    {
        this.datas.remove(item);

        notifyDataSetChanged();
    }

}
