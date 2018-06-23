package com.example.admin.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.admin.ScreenshotService;


public class ScreenshotDemo extends AppCompatActivity {


    private ImageView imgScreen;


    private ServiceConnection aslServiceConn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aslProvider = IScreenshotProvider.Stub.asInterface(service);
        }
    };
    private IScreenshotProvider aslProvider = null;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screenshot_demo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        imgScreen = (ImageView) findViewById(R.id.imgScreen);
        Button btn = (Button) findViewById(R.id.btnTakeScreenshot);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AsyncTaskClass(aslServiceConn, aslProvider, getApplicationContext(), imgScreen).execute();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });

        // connect to ASL service
        Intent intent = new Intent(ScreenshotService.class.getName());
//        Intent intent = new Intent();
        intent.setClass(this, ScreenshotService.class);
        intent.addCategory(Intent.ACTION_DEFAULT);
        bindService(intent, aslServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        unbindService(aslServiceConn);
        super.onDestroy();
    }



}
