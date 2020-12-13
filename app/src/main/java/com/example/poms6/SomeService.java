package com.example.poms6;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;


import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SomeService extends Service {
    Context context;
    Thread thread;
    boolean running=true;
    String text;
    Intent mIntent;
    Bundle mBundle;

    public void onCreate() {
        super.onCreate();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = getApplicationContext();
        Toast.makeText(this, "Служба запущена",Toast.LENGTH_SHORT).show();
        mIntent = new Intent(this, ActivityService.class);
        mBundle = new Bundle();
        startWork();
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onDestroy() {
        Toast.makeText(this, "Служба остановлена",Toast.LENGTH_SHORT).show();
        running=false;
        super.onDestroy();
    }

    private void startWork()  {
        final Handler handler=new Handler();
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(context);
                for(int timer=1;timer<512;timer++){
                    if(!running){
                        break;
                    }
                    writeToFile(Integer.toString(timer));

                    text=Integer.toString(timer);
                    handler.post(doUpdateGUI);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private Runnable doUpdateGUI = new Runnable() {
        public void run() {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    };

    private void writeToFile(String message) {
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadsDir,"Result.txt");
        try {
            FileWriter out = new FileWriter(myFile, true);
            out.write(message+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








}
