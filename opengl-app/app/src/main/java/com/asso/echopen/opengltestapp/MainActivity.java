package com.asso.echopen.opengltestapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import com.asso.echopen.opengltestapp.MultiTextureRenderer;

import java.util.Random;

public class MainActivity extends Activity {

    private Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new GLSurfaceView(this);
        if (detectOpenGLES20())
        {
            // Tell the surface view we want to create an OpenGL ES 2.0-compatible
            // context, and set an OpenGL ES 2.0-compatible renderer.
            mGLSurfaceView.setEGLContextClientVersion(2);
            mGLSurfaceView.setRenderer(new MultiTextureRenderer(this));
        }
        else
        {
            Log.e("MultiTexture", "OpenGL ES 2.0 not supported on device.  Exiting...");
            finish();

        }
        setContentView(mGLSurfaceView);
    }

    private boolean detectOpenGLES20()
    {
        ActivityManager am =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }

    @Override
    protected void onResume()
    {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();

        byte[] data = new byte[512*512*3];
        for ( int y = 0; y < 512; y++ )
            for ( int x = 0; x < 512; x++ )
            {
                int pixel = 150;
                data[(y * 512 + x) * 3 + 0] = (byte)((pixel >> 0) & 0xFF);
                data[(y * 512 + x) * 3 + 1] = (byte)((pixel >> 0) & 0xFF);
                data[(y * 512 + x) * 3 + 2] = (byte)((pixel >> 0) & 0xFF);
            }

        MultiTextureRenderer.buffer.clear();
        MultiTextureRenderer.buffer.put(data);

            /*int x = rnd.nextInt(512*512*3-1);
            data[x] = (byte) rnd.nextInt(255);*/
            for ( int y = 0; y < 512; y++ )
                for ( int x = 0; x < 512; x++ )
                {
                    int pixel = 255;
                    data[(y * 512 + x) * 3 + 0] = (byte)((pixel >> 0) & 0xFF);
                    data[(y * 512 + x) * 3 + 1] = (byte)((pixel >> 0) & 0xFF);
                    data[(y * 512 + x) * 3 + 2] = (byte)((pixel >> 0) & 0xFF);
                }
            MultiTextureRenderer.buffer.clear();
            MultiTextureRenderer.buffer.put(data);
    }

    @Override
    protected void onPause()
    {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mGLSurfaceView.onPause();
    }

    private GLSurfaceView mGLSurfaceView;
}
