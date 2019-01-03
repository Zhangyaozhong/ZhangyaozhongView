package com.bwie.android.zhangyaozhongview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    //画笔
    private Paint paint;
    private int circleX;
    private int circleY;
    //    圆的半径
    private int mRaduis = 100;
    private int mColor = Color.BLUE;

    /**
     * new 一个控件时调用
     *
     * @param context
     */
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 自定义view中包含的属性和样式
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /*public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    /**
     * 初始化对象的方法
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
//        对自定义属性初始化
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mRaduis = typedArray.getDimensionPixelSize(R.styleable.MyView_radius, 100);
        mColor = typedArray.getColor(R.styleable.MyView_color, Color.BLUE);
//        回收资源
        if (typedArray != null) {
            typedArray.recycle();
        }
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(mColor);
//        抗锯齿
        paint.setAntiAlias(true);
        //实心
        paint.setStyle(Paint.Style.FILL);
//        空心
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在onMeasure 一定调用
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 布局、摆放
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleX, circleY, mRaduis, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                获取距离父控件X轴的坐标
                circleX = (int) event.getX();
//                获取距离屏幕的X轴坐标
//                event.getRawX()
                circleY = (int) event.getY();
                //重绘
                invalidate();

                break;
            case MotionEvent.ACTION_MOVE:
                circleX = (int) event.getX();
                circleY = (int) event.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        //返回true，证明消费了这个事件（三个时间都运行了，包括按下，滑动，抬起），false的化，只走down事件
        return true;
    }
}
