package com.trs.azlist2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.List;

public abstract class AZListAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> data;

    public AZListAdapter(Context context, List<T> data) {
        sort(data);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.header_title, null);
            LinearLayout item_view = view.findViewById(R.id.item_view);
            item_view.addView(getView(null));
        }
        TextView tvHeader;
        tvHeader = view.findViewById(R.id.tv_header);
        setView(i,view);

        if (i>0){
            String str1 = Pinyin.toPinyin(getField(i-1), ",");
            String str2 = Pinyin.toPinyin(getField(i), ",");
            tvHeader.setText(str2.substring(0,1));
            tvHeader.setVisibility(View.VISIBLE);
            if (str1.substring(0,1).equals(str2.substring(0,1))){
                tvHeader.setVisibility(View.GONE);
            }
        }else {
            tvHeader.setText(Pinyin.toPinyin(getField(i), ",").substring(0,1));
            tvHeader.setVisibility(View.VISIBLE);
        }
        return view;
    }

    protected abstract void setView(int position, View view);

    public abstract View getView(ViewGroup viewGroup);

    //获取字符串
    public abstract String getField(int position);

    //排序
    public abstract void sort(List<T> data);




}
