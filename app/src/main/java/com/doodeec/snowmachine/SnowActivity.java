package com.doodeec.snowmachine;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class SnowActivity extends ActionBarActivity {

    private SnowWindow snowWindow;
    private SnowWindow snowWindow2;
    private SnowWindow snowWindow3;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);

        snowWindow = (SnowWindow) findViewById(R.id.snow_window);
        snowWindow2 = (SnowWindow) findViewById(R.id.snow_window_2);
        snowWindow3 = (SnowWindow) findViewById(R.id.snow_window_3);

        int sizeX = getScreenWidth();
        int sizeY = getScreenHeight();

        List<SnowFlake> mFlakes = new ArrayList<>();
        List<SnowFlake> mFlakes2 = new ArrayList<>();
        List<SnowFlake> mFlakes3 = new ArrayList<>();
        for (int k = 0; k < 100; k++) {
            //big flakes
            mFlakes.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (7 + Math.random() * 2)));
            //medium flakes
            mFlakes2.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (3 + Math.random() * 2)));
            //tiny flakes
            mFlakes3.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (1 + Math.random() * 2)));
        }

        snowWindow.setFlakes(mFlakes);
        snowWindow2.setFlakes(mFlakes2);
        snowWindow3.setFlakes(mFlakes3);
        snowWindow.setScreenSize(sizeX, sizeY);
        snowWindow2.setScreenSize(sizeX, sizeY);
        snowWindow3.setScreenSize(sizeX, sizeY);
    }

    @Override
    protected void onPause() {
        snowWindow.stopSnowing();
        snowWindow2.stopSnowing();
        snowWindow3.stopSnowing();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        snowWindow.resumeSnowing();
        snowWindow2.resumeSnowing();
        snowWindow3.resumeSnowing();
    }

    private int getScreenWidth() {
        if (displayMetrics.widthPixels == 0) {
            getDefaultDisplay().getRealMetrics(displayMetrics);
        }

        return displayMetrics.widthPixels;
    }

    private int getScreenHeight() {
        if (displayMetrics.heightPixels == 0) {
            getDefaultDisplay().getRealMetrics(displayMetrics);
        }

        return displayMetrics.heightPixels;
    }

    private Display getDefaultDisplay() {
        return ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }
}
