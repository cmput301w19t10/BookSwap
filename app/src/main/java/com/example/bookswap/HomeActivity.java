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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.*;
import android.os.Handler;



import java.util.Timer;

import static com.example.bookswap.Notifications.CHANNEL_2_ID;
import static com.example.bookswap.Notifications.CHANNEL_1_ID;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Home inteface after login
 */
public class HomeActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private boolean newNotification;

    private String userName;
    private DataBaseUtil u;

    /**
     * create all fragments and get current user namd, set notification
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        userName = MyUser.getInstance().getName();

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

    /**
     * check notification and check if user does not logged in
     */
    @Override
    protected void onStart() {
        super.onStart();
        checkNotification();
        timer();
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            this.finish();

        }

    }

    public void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10 seconds
                checkNotification();
                checkNotification2();
                handler.postDelayed(this, 10000);
            }
        }, 10000);  //the time is in miliseconds
    }

    /**
     * set up adapter for fragments
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnerFragment(), "Owner");
        adapter.addFragment(new BorrowerFragment(), "Borrower");
        adapter.addFragment(new SelfProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

    public void GoToAvailable(View view) {
        //Do something in response to button
        Intent intentAva = new Intent(this, OAvailableActivity.class);
        startActivity(intentAva);
    }

    public void checkNotification() {
        if (MyUser.getInstance().getName() != null) {
            u = new DataBaseUtil(MyUser.getInstance().getName());
            u.checkNotification("Borrow", new DataBaseUtil.getStatus() {
                @Override
                public void getStatus(String value) {
                    if (value.equals("True")) {
                        //newNotification = true;
                        sendOnChannel1();
                        u.changeNotificationStatus("Borrow", "False");
//                    reddot.setVisibility(View.INVISIBLE);
//                    //notificationcall();
//                    u.changeNotificationStatus("Borrower","False");

                    }
                }
            });
        }
    }

    public void checkNotification2() {
        u = new DataBaseUtil(MyUser.getInstance().getName());
        u.checkNotification("Request", new DataBaseUtil.getStatus() {
            @Override
            public void getStatus(String value) {
                if (value.equals("True")) {
                    sendOnChannel2();
                    u.changeNotificationStatus("Request", "False");
                }
            }
        });
    }

    public void sendOnChannel1() {
//        NotificationManagerCompat notificationManager;
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentText("Check it out!")
                .setContentTitle("Your book has been requested")
                .build();
        Log.d("notificationtag", "builded");
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentText("Check it out!")
                .setContentTitle("Your request has been accepted")
                .build();
        notificationManager.notify(2, notification);
    }

    /**
     * if there is a user logged in, could get out of app by pressing back button
     * would not return to login interface
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    /**
     * create a menu for logout and review, edit self profile
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    /**
     * set actions for press different item on menu
     * @param item item on menu
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                FirebaseAuth.getInstance().signOut();
                Log.d("USERTAG", MyUser.getInstance().getName());
                MyUser.destroy();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            }
            case R.id.profile: {
                return true;
            }
            case R.id.edit: {
                u = new DataBaseUtil(MyUser.getInstance().getName());
                u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                });

                return true;
            }
            case R.id.review: {
                startActivity(new Intent(HomeActivity.this, SelfRateActivity.class));
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
