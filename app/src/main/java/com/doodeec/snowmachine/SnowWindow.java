package com.doodeec.snowmachine;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 2.12.2014.
 *
 * SnowWindow
 */
public class SnowWindow extends View {

    private static final int frequency = 1000 / 50;

    private List<SnowFlake> mFlakes;
    private Handler mHandler = new Handler();

    private long pastTime = 0;
    private int sizeX, sizeY;

    private final Runnable mAnimatorRunnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < mFlakes.size(); i++) {
                if (!mFlakes.get(i).moveFlake(pastTime, sizeY)) {
                    mFlakes.set(i, new SnowFlake((float) Math.random() * sizeX, -20, mFlakes.get(i).distance));
                }
            }

            pastTime = System.currentTimeMillis();
            invalidate();

            mHandler.postDelayed(mAnimatorRunnable, frequency);
        }
    };

    public SnowWindow(Context context) {
        this(context, null, 0);
    }

    public SnowWindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFlakes(List<SnowFlake> flakes) {
        mFlakes = flakes;

        invalidate();
    }

    public void setScreenSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (SnowFlake flake: mFlakes) {
            canvas.drawCircle(flake.x, flake.y, flake.radius, flake.paint);
        }
        super.onDraw(canvas);
    }

    public void stopSnowing() {
        mHandler.removeCallbacks(mAnimatorRunnable);
    }

    public void resumeSnowing() {
        mHandler.postDelayed(mAnimatorRunnable, frequency);
    }
}
