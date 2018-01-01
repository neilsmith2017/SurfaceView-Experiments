package com.springwoodcomputers.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainSurfaceView";

    private SurfaceHolder surfaceHolder;
    private SurfaceThread surfaceThread;
    private GraphicObject graphicObject;

    public MainSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        graphicObject.update();
        graphicObject.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceCreated: ");
        this.surfaceHolder = surfaceHolder;
        graphicObject = new GraphicObject(this);
        surfaceThread = new SurfaceThread(this, surfaceHolder);
        surfaceThread.setRunning(true);
        surfaceThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceDestroyed: ");
        boolean retry = true;
        while (retry) {
            surfaceThread.setRunning(false);
            try {
                surfaceThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}