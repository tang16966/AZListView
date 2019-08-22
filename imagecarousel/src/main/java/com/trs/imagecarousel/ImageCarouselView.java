package com.trs.imagecarousel;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;


public class ImageCarouselView extends LinearLayout {
    private ViewPager viewPage;
    private ImageStateView stateMake;

    private static final String TAG = "ImageCarouselView";
    private List<CarouselBean> carouselBeans;

    public ImageCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.image_carousel_view, this);
        initLayout();
        initViewPage();
        initViewPagerScroll();
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            BannerScroller mScroller = new BannerScroller(viewPage.getContext());
            mScroller.setDuration(500);
            mField.set(viewPage, mScroller);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private void initViewPage() {
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position1, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0.0){
                    if (position1 == 0){
                        //当滑到第一张图时显示最后一张图并将postion跳至"D"位置
                        viewPage.setCurrentItem(carouselBeans.size()-2,false);
                        position = carouselBeans.size()-2;
                    }
                    //当滑到最后一张图时显示第一张图并将position跳至"A"位置
                    else if (position1 == carouselBeans.size()-1) {
                        viewPage.setCurrentItem(1,false);
                        position = 1;
                    }else {
                        position = position1;
                    }
                    stateMake.setPosition(position);
                }
            }

            @Override
            public void onPageSelected(int position1) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initLayout() {
        viewPage = findViewById(R.id.view_page);
        stateMake = findViewById(R.id.state_make);
    }

    public ImageCarouselView setImage(List<String> data) {
        if (data == null || data.size() == 0){
            Log.e(TAG, "未设置图片" );
            return this;
        }
        carouselBeans = new ArrayList<>(data.size());
        for (int i = 0; i <= data.size()+1 ; i++) {
            if (i==0){
                carouselBeans.add(0,new CarouselBean(data.get(data.size()-1),getContext()));
            }else if (i == data.size()+1){
                carouselBeans.add(new CarouselBean(data.get(0),getContext()));
            }else {
                CarouselBean carouselBean = new CarouselBean(data.get(i - 1), getContext());
                final int finalI = i;
                carouselBean.getImageView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onClick(finalI -1);
                    }
                });
                carouselBeans.add(carouselBean);
            }
        }
        viewPage.setAdapter(new ImageAdapter(getContext(), carouselBeans));
        stateMake.setSize(data.size());
        //设置事件
        return this;
    }

    //设置下面的高度
    public ImageCarouselView setStateHeight(int dp) {
        stateMake.setHeight(dp);
        return this;
    }

    //设置显示模式
    public ImageCarouselView setDisplayType(DisplayType displayType) {
        Config.displayType = displayType;
        return this;
    }

    //设置边距，FILLET才有用
    public ImageCarouselView setPadding(int padding) {
        Config.padding = px2dip(padding);
        return this;
    }
    private int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }

    //设置圆的大小
    public ImageCarouselView setStateMakerSize(int dp) {
        stateMake.setRadius(dp);
        return this;
    }

    //设置字体
    public ImageCarouselView setStateTitles(List<String> titles) {
        stateMake.setTitles(titles);
        return this;
    }

    //设置背景颜色
    public ImageCarouselView setStateBackgroudColor(int color) {
        stateMake.setBackgroundColor(color);
        return this;
    }

    //设置监听
    public void setOnItemClick(OnItemClick onItemClick) {
       this.onItemClick = onItemClick;
    }

    private OnItemClick onItemClick;
    public interface OnItemClick{
        void onClick(int id);
    }



    //设置点的颜色，选中和未选中
    public ImageCarouselView setStateMakerColor(int maker, int unmaker) {
        stateMake.setMakeColor(maker);
        stateMake.setUnMakeColor(unmaker);
        return this;
    }

    private Thread thread;
    private boolean isClose = false;
    private int position = 1;
    private Handler handler = new Handler();

    //自动轮播
    public void autoPaly(int millis) {
        if (millis < 2000){
            millis = 2000;
        }
        final int mill = millis;
        viewPage.setCurrentItem(1,false);
        thread = new Thread() {
            @Override
            public void run() {
                while (!isClose) {
                    try {
                        sleep(mill);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (position < carouselBeans.size() - 1) {
                        position++;
                    } else
                        position = 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            viewPage.setCurrentItem(position);
                        }
                    });

                }
            }
        };
        thread.start();
    }

    //销毁自动轮播
    public void onDestroy() {
        isClose = true;
        thread.interrupt();
    }


}
