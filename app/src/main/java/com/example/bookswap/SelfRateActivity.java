package com.example.bookswap;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

/**
 * activity for seeing the rating for self
 */
public class SelfRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_rate);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        CommentPageAdapter adapter = new CommentPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommentOwnerFragment(), "Owner");
        adapter.addFragment(new CommentBorrowerFragment(), "Borrower");
        viewPager.setAdapter(adapter);
    }
}
