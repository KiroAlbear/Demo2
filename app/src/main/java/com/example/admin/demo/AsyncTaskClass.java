package com.example.admin.demo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.demo.IScreenshotProvider;
import com.example.admin.demo.ScreenshotDemo;

public class AsyncTaskClass extends AsyncTask<Void,String,Void>{

    private ServiceConnection serviceConnection;
    private IScreenshotProvider aslProvider = null;
    private Context context;
    private ImageView imgScreen;

    public AsyncTaskClass(ServiceConnection serviceConnection, IScreenshotProvider aslProvider , Context context,ImageView imageView) {
        this.serviceConnection=serviceConnection;
        this.aslProvider=aslProvider;
        this.imgScreen=imageView;
        this.context=context;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try{
          //  Toast.makeText(context,"Do In BackGround",Toast.LENGTH_SHORT).show();
            if (aslProvider == null){}
              //  Toast.makeText(context, "Message 1 ", Toast.LENGTH_SHORT).show();
            else if (!aslProvider.isAvailable()){}
               // Toast.makeText(context,"Message 2 ", Toast.LENGTH_SHORT).show();
            else {
                String file = aslProvider.takeScreenshot();
                if (file == null){}
                //    Toast.makeText(context, "Message 3 ", Toast.LENGTH_SHORT).show();
                else {
                  //  Toast.makeText(context, "Message 4 ", Toast.LENGTH_SHORT).show();
                    Bitmap screen = BitmapFactory.decodeFile(file);
                    imgScreen.setImageBitmap(screen);
                }

            }

        }
        catch (Resources.NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // squelch
        }
        return null;
    }



}
