package com.trs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trs.azlist.R;

public class HomeAdaper extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context context;

    public HomeAdaper(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = View.inflate(context, R.layout.item1, null);
                break;
            case 1:
                view = View.inflate(context, R.layout.item2, null);
                break;
            default:
                view = View.inflate(context, R.layout.item3, null);
                break;
        }

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        switch (position){
            case 1:
                TextView view = holder.findViewById(R.id.tv_txt);
                view.setText("5555555555");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
