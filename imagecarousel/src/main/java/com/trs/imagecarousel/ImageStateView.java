package com.trs.imagecarousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;


public class ImageStateView extends View {
    private int mWidth;
    private int mHeight;
    private int height;
    private Paint paint;
    //背景颜色
    private int backgroundColor;
    //选中和未选中颜色
    private int makeColor;
    private int unMakeColor;
    //文字颜色
    private int fontColor;
    //数组大小
    private int size;
    //第几个
    private int position;
    //圆点距离
    private int distance;
    //圆点大小
    private int radius;
    //文字大小
    private int fontSize;
    //文字集合
    private List<String> titles;



    public ImageStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundColor = Color.parseColor("#66cccccc");
        makeColor = Color.YELLOW;
        unMakeColor = Color.WHITE;
        fontColor = Color.WHITE;
        distance = 50;
        size = 0;
        position = 1;
        radius = 10;
        height = 50;
        fontSize = height/2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Config.displayType == DisplayType.NORM){
            paint.setColor(backgroundColor);
            canvas.drawRect(0,mHeight-height,mWidth,mHeight,paint);
            //画点
            for (int i = 1; i <= size; i++) {
                if (i == size - position + 1){
                    paint.setColor(makeColor);
                }else {
                    paint.setColor(unMakeColor);
                }
                canvas.drawCircle(mWidth-distance * i,mHeight-height/2,radius,paint);
            }
            //画文字
            //计算阴影里居中
            if (titles != null && titles.size() > 0){
                String title = titles.get(position-1);
                paint.setColor(fontColor);
                paint.setTextSize(fontSize);
                Rect rect = new Rect();
                paint.getTextBounds(title,0,title.length(),rect);
                //超出省略
                title = TextUtils.ellipsize(title, new TextPaint(paint),mWidth-distance*(size+2) ,
                        TextUtils.TruncateAt.END).toString();
                //y居中
                float baseLineY = Math.abs(paint.ascent() + paint.descent()) ;
                canvas.drawText(title,distance-20,mHeight-baseLineY,paint);
            }
        }else {
            for (int i = 1; i <= size; i++) {
                if (i == size - position + 1){
                    paint.setColor(makeColor);
                }else {
                    paint.setColor(unMakeColor);
                }
                canvas.drawCircle(mWidth-distance * i-Config.padding,mHeight-Config.padding*2,radius,paint);
            }
        }
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    private int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }

    public void setHeight(int height) {
        this.height = px2dip(height);
        this.fontSize = this.height/2;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setMakeColor(int makeColor) {
        this.makeColor = makeColor;
    }

    public void setUnMakeColor(int unMakeColor) {
        this.unMakeColor = unMakeColor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    //position默认往1开始
    public void setPosition(int position) {
        if (position > size){
            this.position = size;
        }
        this.position = position;
        invalidate();
    }
}
