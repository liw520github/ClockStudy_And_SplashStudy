package com.example.mb.banner_splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class MyClockView extends View {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //todo 获取时间
            getTime();
            //todo 重新绘制
            invalidate();
            //todo 发送请求
            handler.sendEmptyMessageDelayed(1, 1000);//每1000毫秒一请求
        }
    };
    private int Hours, Minutes, Seconds;//todo 定义时、 分、秒
    private int Width, Height;

    public MyClockView(Context context) {
        super(context);

    }

    public MyClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getTime();//todo 获取时间方法
        handler.sendEmptyMessageDelayed(1, 1000);//todo 发起请求
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();//todo 定义画笔
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeWidth(5);
        paint.setTextSize(30);

        Width = getWidth();
        Height = getHeight();//获取 界面 宽 高
        // todo 绘制表盘

        paint.setStyle(Paint.Style.STROKE);//只是绘制外圈，不填充
        canvas.drawCircle(Width / 2, Height / 2, Width / 2 - 10, paint);

        //todo 绘制圆心
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(Width / 2, Height / 2, 4, paint);

        //todo 绘制刻度
        for (int i = 0; i < 12; i++) {//todo 绘制小时刻度
            canvas.save();//保存
            canvas.rotate(360 / 12 * (i + 1), Width / 2, Height / 2);//旋转 （角度,X中心,Y中心）
            canvas.drawLine(Width / 2, Height / 2 - Width / 2 + 10, Width / 2, Height / 2 - Width / 2 + 50, paint);//刻度
            canvas.drawText((i + 1) + "", Width / 2 - 15, Height / 2 - Width / 2 + 80, paint);//数字
            canvas.restore();//恢复
        }

        paint.setStrokeWidth(2);//重新 设置 画笔
        for (int i = 0; i < 60; i++) {//todo 绘制分钟刻度
            canvas.save();//保存
            canvas.rotate(360 / 60 * (i + 1), Width / 2, Height / 2);//旋转 （角度,X中心,Y中心）
            canvas.drawLine(Width / 2, Height / 2 - Width / 2 + 10, Width / 2, Height / 2 - Width / 2 + 30, paint);//刻度
            canvas.restore();//恢复
        }

        // TODO 绘制时针
        canvas.save();
        canvas.rotate(360 / 12 * Hours + Minutes * 0.5f, Width / 2, Height / 2);
        paint.setStrokeWidth(8);
        canvas.drawLine(Width / 2, Height / 2, Width / 2, Height / 2 - Height / 7, paint);
        canvas.restore();
        // TODO 绘制分针
        canvas.save();
        canvas.rotate(360 / 60 * Minutes + Seconds * 0.1f, Width / 2, Height / 2);
        paint.setStrokeWidth(5);
        canvas.drawLine(Width / 2, Height / 2, Width / 2, Height / 2 - Height / 5, paint);
        canvas.restore();
        // TODO 绘制秒针
        canvas.save();
        canvas.rotate(360 / 60 * Seconds, Width / 2, Height / 2);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);//设置分针颜色
        canvas.drawLine(Width / 2, Height / 2, Width / 2, Height / 2 - Height / 4, paint);
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

    public void getTime() {//todo 获取实时时间
        Calendar calendar = Calendar.getInstance();
        Hours = calendar.get(Calendar.HOUR);
        Minutes = calendar.get(Calendar.MINUTE);
        Seconds = calendar.get(Calendar.SECOND);
    }
}
