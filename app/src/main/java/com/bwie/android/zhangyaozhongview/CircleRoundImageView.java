package com.bwie.android.zhangyaozhongview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class CircleRoundImageView extends android.support.v7.widget.AppCompatImageView {
    public CircleRoundImageView(Context context) {
        this(context, null);
    }

    public CircleRoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        if (bitmap != null) {
//            对bitmip进行圆角转化
            Bitmap roundBitmip = getRoundBitmip(bitmap);
            canvas.drawBitmap(roundBitmip, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆角的Bitmip
     *
     * @return
     */
    private Bitmap getRoundBitmip(Bitmap bitmap) {
//        宽高缩放比
        float widthScale = (float)getMeasuredWidth() / bitmap.getWidth();
        float heightScale = (float)getMeasuredHeight() / bitmap.getHeight();

//        矩阵变化类
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);

//        对bitmip进行变化
        Bitmap nbit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        //最终输出的对象
        //创建一个色彩是8888的bitmap对象
        Bitmap target = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//        创建画布
        Canvas canvas = new Canvas(target);
//        创建画笔
        Paint paint = new Paint();
//        创建一个圆角图形
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(rectF, 40, 40, paint);

//        设置画笔的xfermode
//        SRC_IN  取两层绘制交集。显示下层。
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

//        画原始的bitmip
        canvas.drawBitmap(nbit, 0, 0, paint);
        return target;


    }
}
