package com.behadd.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class EarphoneReceiver extends BroadcastReceiver {
    private int control=0;
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.hasExtra("control")){
            control= intent.getIntExtra("control", 0);
            Log.d("CASE: intentMili ", "control= "+String.valueOf(control));
        }

        int improvise = intent.getIntExtra("state",-1);
        if(improvise==0){
            control=0;
            Log.d("CASE: IMPROVISE ", "control= "+String.valueOf(control));
        }

        if(control==0) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    Log.d("CASE: ", "0");
                    break;
                case 1:
                    Log.d("CASE: ", "1");
                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("Check", 1);
                    context.startActivity(i);
                    break;
                default:
                    Log.d("CASE: ", "default");
                    Toast.makeText(context, "Default", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
