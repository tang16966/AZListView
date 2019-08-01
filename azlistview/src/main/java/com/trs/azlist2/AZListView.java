package com.trs.azlist2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AZListView extends FrameLayout {
    private ContactIndexView contactIndexView;

    public AZListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(),R.layout.layout_az_view,this);
        contactIndexView = findViewById(R.id.contact_index_view);

    }
}
