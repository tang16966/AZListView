package com.trs.azlist2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AZListView extends FrameLayout {
    public AZListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(),R.layout.layout_az_view,this);
    }
}
