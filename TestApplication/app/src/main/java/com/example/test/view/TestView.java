package com.example.test.view;

import android.content.Context;
import android.view.View;

/**
 * Created by wangyan on 2016/1/6.
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
