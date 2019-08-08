package com.trs.azlist2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.FrameLayout;

public class AZListView extends FrameLayout {
    private ContactIndexView contactIndexView;
    private MyListView listView;
    private AZListAdapter adapter;

    public AZListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(),R.layout.layout_az_view,this);
        init();
    }

    private void init() {
        contactIndexView = findViewById(R.id.contact_index_view);
        listView = findViewById(R.id.list_view);
        //隐藏滚动条
        listView.setVerticalScrollBarEnabled(false);
        listView.setOnScrollSelect(new MyListView.OnScrollSelect() {
            @Override
            public void select(String s) {
                contactIndexView.setSelect(s);
            }
        });
        contactIndexView.setOnScollSelect(new ContactIndexView.OnScollSelect() {
            @Override
            public void select(String letter) {
                listView.setSelect(letter);
            }
        });
    }

    public ContactIndexView getContactIndexView() {
        return contactIndexView;
    }

    public void setAdapter(AZListAdapter adapter) {
        this.adapter = adapter;
        listView.setAdapter(adapter);
    }

}
