package com.example.bookswap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

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



}
