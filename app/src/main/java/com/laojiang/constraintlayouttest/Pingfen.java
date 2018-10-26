package com.laojiang.constraintlayouttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 2018/10/25.
 */

public class Pingfen extends View {

    private Context context;
    private Paint paint;
    private Path path;
    private RectF rect;
    private RectF rect2;
    private List<RectF> listLine = new ArrayList<>();
    private int i;
    private int d = 300;
    private Paint paint2;

    private int speed = 20;
    private Paint paint3;
    private RectF b1;
    private RectF b2;
    private int with;
    private RectF b4;

    public Pingfen(Context context) {
        this(context, null);
    }

    public Pingfen(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pingfen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(2);


        path = new Path();
        int[] ss = new int[]{1, 2, 3, 4};
        Random random = new Random(3);
        with = getScreenWidthPixels();
        i = 0;
        b1 = new RectF(with, 20, with, 30);
        b2 = new RectF(with + 50, 20, with + 50, 30);
        b4 = new RectF(with + 150, 20, with + 150, 30);
        rect = new RectF(with, 20, with + 50, 30);
        rect2 = new RectF(with + 50, 20, with + 100, 30);
        RectF rect3 = new RectF(with + 100, 20, with + 150, 30);
        RectF rect4 = new RectF(with + 150, 20, with + 200, 30);
//            RectF rect5 = new RectF(700, 20, 750, 30);
//            RectF rect6 = new RectF(800, 20, 850, 30);
//            RectF rect7 = new RectF(900, 20, 950, 30);
        listLine.add(rect);
        listLine.add(rect2);
        listLine.add(rect3);
        listLine.add(rect4);
//            listLine.add(rect5);
//            listLine.add(rect6);
//            listLine.add(rect7);


        initTime();
    }

    private void initTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i++;
            }
        }, 0, 15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RectF rectF : listLine) {
            canvas.drawRoundRect(rectF, 40, 40, paint);
        }
        int right = 0;
        if (i >= with - 300 && i <= with - 250) {
            right = 300 + i;
        } else if (i > with - 250) {
            right = with + 50;
        }

        int right2 = 0;
        if (i >= with - 250 && i <= with - 200) {
            right2 = 300 + i;
        } else if (i > with - 200) {
            right2 = with + 100;
        }
        int right4 = 0;
        if (i >= with - 150 && i <= with - 100) {
            right4 = 300 + i;
        } else if (i > with - 100) {
            right4 = with + 200;
        }
        if (i > with - 300) {
            b1.set(with, 20, right, 30);

            Log.i("从什么时候开始==", i + "");
            canvas.drawRoundRect(b1, 40, 40, paint2);
        }
        if (i > with - 250) {
            b2.set(with + 50, 20, right2, 30);
            canvas.drawRoundRect(b2, 40, 40, paint2);
        }

        if (i > with - 150) {

            b4.set(with + 150, 20, right4, 30);
            canvas.drawRoundRect(b4, 40, 40, paint2);
        }

        setScrollX(i);
        postInvalidateDelayed(15);
        Log.i("画画==", i + "");
    }


    private int getScreenWidthPixels() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
