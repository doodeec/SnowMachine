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
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);

        snowWindow = (SnowWindow) findViewById(R.id.snow_window);

        int sizeX = getScreenWidth();
        int sizeY = getScreenHeight();

        List<SnowFlake> mFlakes = new ArrayList<>();
        for (int k = 0; k < 100; k++) {
            //big flakes
            mFlakes.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (3 + Math.random() * 2)));
            //medium flakes
            mFlakes.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (1 + Math.random() * 2)));
            //tiny flakes
            mFlakes.add(new SnowFlake((float) Math.random() * sizeX, (float) Math.random() * sizeY, (float) (1 + Math.random() * 2)));
        }

        snowWindow.setFlakes(mFlakes);
        snowWindow.setScreenSize(sizeX, sizeY);
    }

    @Override
    protected void onPause() {
        snowWindow.stopSnowing();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        snowWindow.resumeSnowing();
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
