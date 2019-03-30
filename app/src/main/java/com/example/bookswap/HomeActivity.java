package com.example.bookswap;

import android.app.Notification;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.*;
import android.os.Handler;



import java.util.Timer;

import static com.example.bookswap.Notifications.CHANNEL_2_ID;
import static com.example.bookswap.Notifications.CHANNEL_1_ID;

/**
 * Home inteface after login
 */
public class HomeActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


//        Notifications notifications = new Notifications();
//        timer.schedule(new notifications.checkNotification(), 0, 5000);
        notificationManager = NotificationManagerCompat.from(this);

//            u.checkRequestNotification(new DataBaseUtil.getStatus() {
//                @Override
//                public void getStatus(String value) {
//                    if (value.equals("False")) {
//                        sendOnChannel1();
////                    reddot.setVisibility(View.INVISIBLE);
////                    //notificationcall();
////                    u.changeNotificationStatus("Borrower","False");
//
//                    }
//                }
//            });



//        final DataBaseUtil u;

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNotification();
        timer();

    }
    public void timer(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10 seconds
                checkNotification();
                handler.postDelayed(this,10000);}
                }, 10000);  //the time is in miliseconds

    }

    /**
     * set up adapter for fragments
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnerFragment(), "Owner");
        adapter.addFragment(new BorrowerFragment(), "Borrower");
        adapter.addFragment(new SelfProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

    public void GoToAvailable(View view){
        //Do something in response to button
        Intent intentAva = new Intent(this, OAvailableActivity.class);
        startActivity(intentAva);
    }

    public void checkNotification(){
        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        u.checkRequestNotification("Borrower",new DataBaseUtil.getStatus() {
            @Override
            public void getStatus(String value) {
                if (value.equals("False")) {
                    sendOnChannel1();
//                    reddot.setVisibility(View.INVISIBLE);
//                    //notificationcall();
//                    u.changeNotificationStatus("Borrower","False");

                }
            }
        });
    }
    public void sendOnChannel1(){
//        NotificationManagerCompat notificationManager;
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentText("Check it out!")
                .setContentTitle("Your book has been requested")
                .build();
        Log.d("notificationtag","builded");
        notificationManager.notify(1,notification);
    }
    public void sendOnChannel2(){
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentText("Check it out!")
                .setContentTitle("Your request has been accepted")
                .build();
        notificationManager.notify(2,notification);
    }

}
