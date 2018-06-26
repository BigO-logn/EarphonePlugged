package com.behadd.broadcastreceivers;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DeepLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);


       /* Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("android.intent.action.VIEW")){
            Uri data = intent.getData();
            Log.d("XXXX","action--------------"+action);
            Log.d("XXXXX","Data---------------"+data);    //https://1234.adgyde.com
        }*/

    }
}
