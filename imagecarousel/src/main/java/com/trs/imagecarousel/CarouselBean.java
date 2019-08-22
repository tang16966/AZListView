package com.trs.imagecarousel;

import android.content.Context;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

public class CarouselBean {
    private String url;
    private FilletImageView imageView;
    private Context context;

    public CarouselBean(String url, Context context){
        this.url = url;
        this.context = context;
        imageView = new FilletImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(url).into(imageView);
    }

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Context getContext() {
        return context;
    }
}
