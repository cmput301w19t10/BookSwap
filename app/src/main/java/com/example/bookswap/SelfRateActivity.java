package com.example.bookswap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * activity for seeing the rating for self
 */
public class SelfRateActivity extends AppCompatActivity {

    private User user;
    SectionsPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_rate);

        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("user");

        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        adapter.getItem(0).setArguments(bundle);
        adapter.getItem(1).setArguments(bundle);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommentOwnerFragment(), "Owner");
        adapter.addFragment(new CommentBorrowerFragment(), "Borrower");
        viewPager.setAdapter(adapter);
    }
}
