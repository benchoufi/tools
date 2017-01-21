package com.asso.echopen.gpuchallenge;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.asso.echopen.gpuchallenge.model.BitmapDisplayer;
import com.asso.echopen.gpuchallenge.model.BitmapDisplayerFactory;
import com.asso.echopen.gpuchallenge.ui.MainActionController;
import com.asso.echopen.gpuchallenge.utils.Config;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    private MainActionController mainActionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.getInstance(this);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vMiddle);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        initActionController();
        BitmapDisplayerFactory bitmapDisplayerFactory = new BitmapDisplayerFactory();
        fetchData(bitmapDisplayerFactory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public void initActionController() {
        Activity activity = this;
        mainActionController = new MainActionController(activity);
    }

    public void fetchData(BitmapDisplayerFactory bitmapDisplayerFactory) {
        try {
            BitmapDisplayer bitmapDisplayer = bitmapDisplayerFactory.populateBitmap(
                    this, mainActionController);

                AssetManager assetManager = getResources().getAssets();
                InputStream inputStream = assetManager.open("data/raw_data/data_phantom.csv");
                bitmapDisplayer.readDataFromFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // To be dumped if any necessity
        return super.onOptionsItemSelected(item);
    }
}
