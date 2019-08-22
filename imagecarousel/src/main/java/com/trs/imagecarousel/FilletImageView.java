package com.trs.imagecarousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.ImageView;

import static com.trs.imagecarousel.Config.padding;

@SuppressLint("AppCompatCustomView")
public class FilletImageView extends ImageView {

    private float[] rids = {20.0f,20.0f,20.0f,20.0f,20.0f,20.0f,20.0f,20.0f};

    public FilletImageView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (Config.displayType == DisplayType.FILLET){
            setPadding(padding,padding,padding,padding);
            Path path = new Path();
            path.addRoundRect(new RectF(padding,padding,getWidth()-padding,getHeight()-padding)
                    ,rids,Path.Direction.CW);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
