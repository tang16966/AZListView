package com.trs.azlist2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

public class ContactIndexView extends View {
    //宽高
    private int height;
    private int width;
    //背景画笔
    private int bgColor;
    private Paint bgPaint;
    //字体画笔
    private int foneSize;
    private int fontColor;
    private Paint fontPaint;
    //选中的字体
    private int selectPosition;
    private int selectFontColor;
    //背景的弧度
    private float bgRadian;

    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public ContactIndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        fontColor = Color.WHITE;
        selectFontColor = Color.YELLOW;
        selectPosition = 0;
        foneSize = 30;
        bgColor = Color.parseColor("#33000000");
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(foneSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画半透明背景
        bgPaint.setColor(bgColor);
        //画背景
        bgRadian = width/(float)2.0;
        Rect bouns = new Rect();
        canvas.drawRoundRect(0,0,width,height,bgRadian,bgRadian,bgPaint);
        //算出每个字母的位置
        for (int i = 0; i < letters.length; i++) {
            String s = letters[i];
            //会把测量结果给bouns
            fontPaint.getTextBounds(s,0,1,bouns);
            //测量得到的宽高
            int mWidth = bouns.width();
            int mHeight = bouns.height();
            //设置颜色
            if (selectPosition == i+1){
                fontPaint.setColor(selectFontColor);
            }else {
                fontPaint.setColor(fontColor);
            }
            canvas.drawText(s,width/2-mWidth/2,(i+1)*(height/letters.length)- mHeight /2,fontPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPosition(y/(height/26)+1);
                break;
            case MotionEvent.ACTION_MOVE:
                setPosition(y/(height/26)+1);
                break;
        }
        return true;
    }

    public void setPosition(int position){
        if (position > 25)
            selectPosition = 25;
        else if (position < 0)
            selectPosition = 0;
        else
            selectPosition = position;
        invalidate();
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getFontColor() {
        return fontColor;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public int getSelectFontColor() {
        return selectFontColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public void setSelectFontColor(int selectFontColor) {
        this.selectFontColor = selectFontColor;
    }
}
