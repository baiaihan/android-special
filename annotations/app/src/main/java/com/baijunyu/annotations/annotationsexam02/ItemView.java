package com.baijunyu.annotations.annotationsexam02;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijunyu.annotations.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * item项对应的view，使用@EViewGroup注解
 */

@EViewGroup(R.layout.item)
public class ItemView extends RelativeLayout {
    @ViewById
    TextView tvTitle;

    @ViewById
    TextView tvDetail;

    @ViewById
    ImageView imgIcon;

    public ItemView(Context context) {
        super(context);
    }

    public void bind(Item item) {
        tvTitle.setText(item.getTitle());
        tvDetail.setText(item.getDetail());
        imgIcon.setImageResource(item.getImageId());
    }
}
