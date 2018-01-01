package com.springwoodcomputers.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GraphicObject {

    private static final String TAG = "GraphicObject";

    private float x;
    private float y;

    private int screenWidth;
    private int screenHeight;

    private int objectWidth = 50;
    private int objectHeight = 50;

    private int velocityX = 30;
    private int velocityY = 30;

    private Paint paint;

    public GraphicObject(MainSurfaceView mainSurfaceView) {
        screenWidth = mainSurfaceView.getWidth();
        screenHeight = mainSurfaceView.getHeight();

        x = screenWidth / 2;
        y = screenHeight / 2;

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Log.d(TAG, "GraphicObject: screenWidth=" + screenWidth + "  screenHeight=" + screenHeight);
    }

    public void update() {
        if ((velocityX > 0 && ((velocityX + x) > screenWidth)) ||
                (velocityX < 0 && ((velocityX + x) < 0))) {
            velocityX = velocityX * -1;
        }
        x = x + velocityX;

        if ((velocityY > 0 && ((velocityY + y) > screenHeight)) ||
                (velocityY < 0 && ((velocityY + y) < 0))) {
            velocityY = velocityY * -1;
        }
        y = y + velocityY;
    }

    public void draw(Canvas canvas) {
        Log.d(TAG, "draw: x=" + x + "  y=" + y);
        canvas.drawCircle(x,y,objectWidth/2, paint);
    }
}
