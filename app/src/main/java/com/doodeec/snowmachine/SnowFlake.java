package com.doodeec.snowmachine;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

/**
 * Created by Dusan Doodeec Bartos on 2.12.2014.
 *
 * SnowFlake class
 */
public class SnowFlake {
    public static final float GRAVITY = 0.5f;

    public float x;
    public float y;
    public float distance;
    public float opacity;
    public float radius;
    public float velocity;
    public Paint paint = new Paint();

    // reusing, not waiting for GC to throw unused local variables
    private float moveDistance;

    public SnowFlake(float x, float y, float distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;

        this.radius = 3 * distance;
        this.velocity = (float) (distance * (0.5 + Math.random()) * (0.5 + Math.random()) * (0.5 + Math.random()));
        this.opacity = 1 - this.distance / 10;
        this.paint.setColor(Color.WHITE);

        updateGradient();
    }

    public void updateGradient() {
        this.paint.setShader(new RadialGradient(this.x, this.y, this.radius, Color.WHITE, Color.TRANSPARENT, Shader.TileMode.CLAMP));
    }

    public boolean moveFlake(long pastTime, int sizeY) {
        moveDistance = pastTime != 0 ? velocity * GRAVITY * (float) (System.currentTimeMillis() - pastTime) / 10 : velocity * GRAVITY;

        if (y + moveDistance < sizeY + 20) {
            y += moveDistance;
            updateGradient();
            return true;
        } else {
            return false;
        }
    }
}
