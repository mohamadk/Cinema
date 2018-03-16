package com.mkhaleghy.cinema.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mkhaleghy.cinema.R;

/**
 * Created by mk on 3/8/2018.
 */

public class RampImageView extends AppCompatImageView {
    int rampHeight=0;
    Paint paint=new Paint();
    private int rampColor;
    private float rampStartPercent;
    private float rampDy;

    public RampImageView(Context context) {
        super(context);
        init(null);
    }

    public RampImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RampImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta=getContext().obtainStyledAttributes(attrs, R.styleable.RampImageView);

            rampHeight=ta.getDimensionPixelSize(R.styleable.RampImageView_riv_rampHeight,0);
            rampStartPercent=ta.getFloat(R.styleable.RampImageView_riv_rampStartPercent,0);
            rampColor=ta.getColor(R.styleable.RampImageView_riv_rampColor,Color.WHITE);
        }

        paint.setColor(rampColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTriangle(0, (int) (getHeight()-getHeight()*rampStartPercent+rampDy),getWidth(),rampHeight,false,paint,canvas);
        canvas.drawRect(0,(int) (getHeight()-getHeight()*rampStartPercent+rampDy),getWidth(),getHeight(),paint);
    }
    private void drawTriangle(int x, int y, int width, int height, boolean inverted, Paint paint, Canvas canvas){

        Point p1 = new Point(x,y);
        int pointX = x + width;
        int pointY = inverted?  y + height : y - height;

        Point p2 = new Point(pointX,pointY);
        Point p3 = new Point(x+width,y);


        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(p1.x,p1.y);
        path.lineTo(p2.x,p2.y);
        path.lineTo(p3.x,p3.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    public void setRampDy(float rampDy) {
        this.rampDy = rampDy;
        invalidate();
    }
}
