package com.trs.azlist;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trs.azlist2.AZListAdapter;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class MyAdapter extends AZListAdapter<User> {

    public MyAdapter(Context context, List<User> data) {
        super(context, data);
    }

    @Override
    protected void setView(int position,View view) {
        TextView tvTxt;
        tvTxt = view.findViewById(R.id.tv_txt);
        tvTxt.setText(data.get(position).getName());
    }

    @Override
    public View getView( ViewGroup viewGroup) {
        View view = View.inflate(context,R.layout.item_view,viewGroup);
        return view;
    }

    @Override
    public String getField(int position) {
        return data.get(position).getName();
    }

    @Override
    public void sort(List<User> data) {
        //中文排序
        Collections.sort(data, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return Collator.getInstance(Locale.CHINESE).compare(user.getName(),t1.getName());
            }
        });
    }


}
