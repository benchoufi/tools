package com.asso.echopen.opengltestapp;

import android.opengl.GLES20;

import java.nio.ByteBuffer;

/**
 * Created by mehdibenchoufi on 12/09/16.
 */
public class GLES20Renderer extends GLRenderer {

    @Override
    public void onCreate(int width, int height,
                         boolean contextLost) {
        byte[] data = new byte[512*512];
        for (int i = 0; i < 512*512; i++) {
            data[i] = 100;
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(512*512);
        buffer.put(data);
        buffer.position(0);

        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 512, 512, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buffer);
        //GLES20.glClearColor(0f, 0f, 0f, 1f);
    }

    @Override
    public void onDrawFrame(boolean firstDraw) {
        //onDrawFrame(firstDraw);
        /*GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT
                | GLES20.GL_DEPTH_BUFFER_BIT);

        byte[] data = new byte[512*512];
        for (int i = 0; i < 512*512; i++) {
            data[i] = 100;
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(512*512);
        buffer.put(data);
        buffer.position(0);

        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 512, 512, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buffer);*/
    }
}