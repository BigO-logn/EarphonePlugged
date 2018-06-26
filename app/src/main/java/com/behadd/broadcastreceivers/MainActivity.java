package com.behadd.broadcastreceivers;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.behadd.broadcastreceivers.com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.firebase.analytics.FirebaseAnalytics;

import android.app.Activity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private EarphoneReceiver meraReceiver;
    private FirebaseAnalytics mFirebaseAnalytics;

    int check=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String myApiKey = bundle.getString("key");
            //Toast.makeText(this, "Value is: "+myApiKey, Toast.LENGTH_LONG).show();
            Log.d("XXXX", "Value is: "+myApiKey);
            //Log.d("BLAAAAAAH", "HEllo check");

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("XXXX", "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.d("XXXX", "Failed to load meta-data, NullPointer: " + e.getMessage());
        }


         String id ="123", name="tester";
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        //bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        meraReceiver = new EarphoneReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(meraReceiver, filter);

        String name1=" landscape";
        String text="It is beautiful";
        Bundle params = new Bundle();
        params.putString("image_name", name1);
        params.putString("full_text", text);
        mFirebaseAnalytics.logEvent("share_image", params);

        findViewById(R.id.btnReadMoreAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeepLink.class));
            }
        });
        /*campaignTrackingReceiver= new CampaignTrackingReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_PACKAGE_FIRST_LAUNCH);
        registerReceiver(campaignTrackingReceiver, intentFilter);
*/

        findViewById(R.id.btnYouTube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
        });

        findViewById(R.id.btnSendBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("meraBroadcast");
                intent.putExtra("secretMessage","Ye raaz ki baat hai");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        Intent intent = getIntent();
        check = intent.getIntExtra("Check", 10);
        Log.d("CASE: ONRESUME ", "Intent se mili| check= "+String.valueOf(check));
        if(check==1){
            Intent toReceiver = new Intent(this, EarphoneReceiver.class);
            toReceiver.putExtra("control", 1);
            meraReceiver.onReceive(this, toReceiver);

            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
            startActivity(launchIntent);
            finish();   //Working?
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
//        unregisterReceiver(meraReceiver);
        super.onPause();
    }

}