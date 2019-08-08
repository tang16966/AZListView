package com.trs.azlist2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.promeg.pinyinhelper.Pinyin;

public class MyListView extends ListView {


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        AZListAdapter adapter = (AZListAdapter) getAdapter();
        String field = adapter.getField(getFirstVisiblePosition());
        if (onScrollSelect == null){
            return;
        }
        onScrollSelect.select(Pinyin.toPinyin(field,",").substring(0,1));
    }

    public void setSelect(String letter){
        AZListAdapter adapter = (AZListAdapter) getAdapter();
        for (int i = 0; i < adapter.getCount() ; i++) {
            String field = adapter.getField(i);
            String let = Pinyin.toPinyin(field, ",").substring(0, 1);
            if (let.equals(letter)){
                setSelection(i);
                return;
            }
        }


    }

    private OnScrollSelect onScrollSelect;

    public void setOnScrollSelect(OnScrollSelect onScrollSelect) {
        this.onScrollSelect = onScrollSelect;
    }

    public interface OnScrollSelect{
        void select(String s);
    }

}
