package com.asso.echopen.opengltestapp;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by mehdibenchoufi on 12/09/16.
 */
public class OpenGLView extends GLSurfaceView {

    private GLSurfaceView mSurfaceView;

    public OpenGLView(Context context) {
        this(context, null);
    }

    public OpenGLView(Context context, AttributeSet attrs) {
         this(context, attrs, 0);
    }

    public OpenGLView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        Utils utils = new Utils();

        if (utils.checkHasGLES20(context)) {
            mSurfaceView = new GLSurfaceView(context);
            mSurfaceView.setEGLContextClientVersion(2);
            mSurfaceView.setPreserveEGLContextOnPause(true);
            mSurfaceView.setRenderer(new GLES20Renderer());
        } else {
            Log.d("TAGGY", "Your device does not support Open GL 2");
        }
    }
}
