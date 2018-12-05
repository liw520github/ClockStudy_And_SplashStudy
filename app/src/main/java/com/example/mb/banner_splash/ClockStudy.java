package com.example.mb.banner_splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;


public class ClockStudy extends View {
    private int mWidth, mHeight;
    private int mHour, mMinute, mSecond;
private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        getTime();
        //重新绘制
        invalidate();
        handler.sendEmptyMessageDelayed(1, 1000);//每1000毫秒一请求
    }
};


    public ClockStudy(Context context) {
        this(context, null);
    }

    public ClockStudy(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockStudy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getTime();
        handler.sendEmptyMessageDelayed(1,1000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        mWidth = getWidth();
        mHeight = getHeight();
        //圆盘
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paint);
        //圆心
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 4, paint);
        //刻度
        paint.setStrokeWidth(5);
        paint.setTextSize(mWidth / 22);
        for (int i = 0; i < 12; i++) {
            canvas.save();
            canvas.rotate(360 / 12 * (i + 1), mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + mWidth / 15, paint);
            canvas.drawText((i + 1) + "", mWidth / 2 - mWidth / 50, mHeight / 2 - mWidth / 2 + mWidth / 8, paint);
            canvas.restore();
        }
        for (int i = 0; i < 60; i++) {
            canvas.save();
            canvas.rotate(360 / 60 * (i + 1), mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + mWidth / 20, paint);
            canvas.restore();
        }
        //绘制时针
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(8);
        canvas.save();
        canvas.rotate(360 / 12 * mHour + mMinute * 0.5f, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2 - mHeight / 150, mWidth / 2, mHeight / 2 - mHeight / 6, paint);
        canvas.restore();
        //绘制分针
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        canvas.save();
        canvas.rotate(360 / 60 * mMinute + mSecond * 0.1f, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2 - mHeight / 150, mWidth / 2, mHeight / 2 - mHeight / 4 - mHeight / 40, paint);
        canvas.restore();
        //绘制秒针
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.save();
        canvas.rotate(360 / 60 * mSecond, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2 - mHeight / 150, mWidth / 2, mHeight / 2 - mHeight / 4 - mHeight / 20, paint);
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //获取时间
    public void getTime(){
        Calendar calendar =Calendar.getInstance();
        mHour =calendar.get(Calendar.HOUR);
        mMinute =calendar.get(Calendar.MINUTE);
        mSecond =calendar.get(Calendar.SECOND);
    }
}
