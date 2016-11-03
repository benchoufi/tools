package com.asso.echopen.opengltestapp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import java.nio.ByteBuffer;


/**
 * Created by mehdibenchoufi on 12/09/16.
 */
public abstract class GLRenderer implements Renderer {

        private boolean mFirstDraw;
        private boolean mSurfaceCreated;
        private int mWidth;
        private int mHeight;
        private long mLastTime;
        private int mFPS;

        public GLRenderer() {
            mFirstDraw = true;
            mSurfaceCreated = false;
            mWidth = -1;
            mHeight = -1;
            mLastTime = System.currentTimeMillis();
            mFPS = 0;
        }

        @Override
        public void onSurfaceCreated(GL10 notUsed,
                                     EGLConfig config) {
                Log.d("TAGGY", "Surface created.");
            byte[] data = new byte[512*512];
            for (int i = 0; i < 512*512; i++) {
                data[i] = 100;
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(512*512);
            buffer.put(data);
            buffer.position(0);

            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 512, 512, 0,
                    GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buffer);
            mSurfaceCreated = true;
            mWidth = -1;
            mHeight = -1;
        }

        @Override
        public void onSurfaceChanged(GL10 notUsed, int width,
                                     int height) {
            if (!mSurfaceCreated && width == mWidth
                    && height == mHeight) {
                    Log.d("TAGGY",
                            "Surface changed but already handled.");
                return;
            }
                // Android honeycomb has an option to keep the
                // context.
                String msg = "Surface changed width:" + width
                        + " height:" + height;
                if (mSurfaceCreated) {
                    msg += " context lost.";
                } else {
                    msg += ".";
                }
                Log.d("TAGGY", msg);

            mWidth = width;
            mHeight = height;

            onCreate(mWidth, mHeight, mSurfaceCreated);
            mSurfaceCreated = false;
        }

        @Override
        public void onDrawFrame(GL10 notUsed) {
            onDrawFrame(mFirstDraw);
             mFPS++;
                long currentTime = System.currentTimeMillis();
                if (currentTime - mLastTime >= 1000) {
                    mFPS = 0;
                    mLastTime = currentTime;
                }

            if (mFirstDraw) {
                mFirstDraw = false;
            }
        }

        public int getFPS() {
            return mFPS;
        }

        public abstract void onCreate(int width, int height,
                                      boolean contextLost);

        public abstract void onDrawFrame(boolean firstDraw);
}
