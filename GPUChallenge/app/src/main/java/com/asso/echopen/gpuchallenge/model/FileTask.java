package com.asso.echopen.gpuchallenge.model;

import android.app.Activity;
import android.util.Log;

import com.asso.echopen.gpuchallenge.preproc.ScanConversion;
import com.asso.echopen.gpuchallenge.ui.MainActionController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class refreshes the main UI to display data from a local file
 */
public class FileTask extends AbstractDataTask {

    private final Data data;

    public static int counter = 0;

    public FileTask(Activity activity, MainActionController mainActionController, ScanConversion scanConversion, InputStream inputStream) {
        super(activity, mainActionController, scanConversion);
        InputStreamReader isReader = new InputStreamReader(inputStream);
        data = new Data(isReader);
        scanconversion = new ScanConversion(data);
        ScanConversion.compute_tables();
    }

    @Override
    protected Void doInBackground(Void... Voids) {
        while (true) {
            //For fun : scanconversion.randomize();
            scanconversion.setData(data);
            try {
                refreshUI(scanconversion);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//
//            }
        }
    }
}
