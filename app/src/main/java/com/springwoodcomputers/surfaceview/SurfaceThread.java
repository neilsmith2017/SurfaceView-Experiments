package com.springwoodcomputers.surfaceview;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {

    private static final String TAG = "SurfaceThread";

    private static int DESIRED_FPS = 60;

    private boolean running;
    private MainSurfaceView mainSurfaceView;
    private SurfaceHolder surfaceHolder;

    public SurfaceThread(MainSurfaceView mainSurfaceView, SurfaceHolder surfaceHolder) {
        this.mainSurfaceView = mainSurfaceView;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        int targetTime = 1000 / DESIRED_FPS;

        while (running) {
            long startTime = System.nanoTime();
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                mainSurfaceView.draw(canvas);
            } catch (Exception e) {
                //
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            long timeNow = System.nanoTime();
            long waitTime = (timeNow - startTime) / 1000000;
            if (waitTime < targetTime) {
                try {
                    Log.d(TAG, "run: sleeping " + (targetTime - waitTime) + " millis");
                    sleep(targetTime - waitTime);
                } catch (InterruptedException e) {
                    //
                }
            } else {
                Log.d(TAG, "run: Not sleeping");
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}